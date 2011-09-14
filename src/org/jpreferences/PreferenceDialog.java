package org.jpreferences;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import org.jpreferences.utils.PrefUtils;

/**
 * Provides a graphical interface for users to interact with preference pages.
 * From these preference pages, preferences may be updated.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
@SuppressWarnings("serial")
public class PreferenceDialog extends JDialog {

	/**
	 * The interface object the user interacts with to view properties from
	 * different pages.
	 */
	private JTree tree;
	private JTable editTable;
	private Preferences preferences;

	/**
	 * Creates a <code>PreferenceDialog</code> calling {@link JDialog(Dialog,
	 * boolean)}.
	 * 
	 * @param parent
	 *            the <code>Dialog</code> from which the dialog is displayed or
	 *            null if this dialog has no owner
	 */
	public PreferenceDialog(Dialog parent, Preferences preferences) {
		super(parent, true);
		setPreferences(preferences);
		init();
	}

	/**
	 * Creates a <code>PreferenceDialog</code> calling {@link JDialog(Window,
	 * boolean)}.
	 * 
	 * @param parent
	 *            the <code>Window</code> from which the dialog is displayed or
	 *            null if this dialog has no owner
	 */
	public PreferenceDialog(Window parent, Preferences preferences) {
		super(parent, ModalityType.DOCUMENT_MODAL);
		setPreferences(preferences);
		init();
	}

	/**
	 * Initializes the User Interface (UI) for this dialog. A left panel
	 * contains a tree hierarchy of the {@link IPreferenceNode}s; when the user
	 * selects a <code>IPreferenceNode</code> the page panel (the right on the
	 * right) updates to show the {@link IPreferencePage} associated with the
	 * <code>IPreferenceNode</code>.
	 */
	private void init() {
		setLayout(new BorderLayout());
		setTitle("Preferences Dialog");
		setMinimumSize(new Dimension(400, 400));

		loadTestData(preferences);
		try {
			System.out.println(Arrays.toString(preferences.keys()));
		} catch (BackingStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ActionListener closeAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		};
		// bind Escape key to close the dialog
		KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(closeAction, escapeStroke,
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		//
		// Edit Table
		//
		editTable = new JTable();

		//
		// TreePanel -- panel containing the tree hierarchy
		//
		tree = createTree(true, false, getPreferences());
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(null);
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);

		tree.setCellRenderer(renderer);
		tree.setBorder(BorderFactory.createLoweredBevelBorder());

		// Buttons

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(closeAction);
		buttonPanel.add(closeButton);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setLeftComponent(new JScrollPane(tree));
		splitPane.setRightComponent(new JScrollPane(editTable));
		add(splitPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
	}

	private void loadTestData(Preferences p) {
		p.put("test String", "hello world");
		p.putBoolean("test Boolean", true);
		p.putDouble("test Double", 3.14159);
		p.putInt("test Integer", 7);
		try {
			preferences.sync();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the preferences
	 */
	public Preferences getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences
	 *            the preferences to set
	 */
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	/**
	 * Creates and returns a <code>JTree</code> containing tree nodes for each
	 * <code>Preferences</code> specified.
	 * 
	 * @param userPreferences
	 *            <code>true</code> to add the nodes for user preferences, or
	 *            <code>false</code> not to
	 * @param systemPreferences
	 *            <code>true</code> to add the nodes for system preferences, or
	 *            <code>false</code> not to
	 * @param preferences
	 *            the objects containing preferences to add to the tree
	 * @return the preference tree created
	 */
	protected JTree createTree(boolean userPreferences,
			boolean systemPreferences, Preferences... preferences) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Preferences");
		for (Preferences node : preferences) {
			if (userPreferences) {
				root.add(createUserRootNode(node));
			}
			if (systemPreferences) {
				root.add(createSystemRootNode(node));
			}
		}
		DefaultTreeModel model = new DefaultTreeModel(root);
		JTree tree = new JTree(model);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				try {
					PrefTreeNode node = (PrefTreeNode) e.getPath()
							.getLastPathComponent();
					Preferences pref = node.getPrefObject();
					editTable.setModel(new PrefTableModel(pref));
				} catch (ClassCastException ce) {
					// System.out.println("Node not PrefTreeNode!");
					editTable.setModel(new DefaultTableModel());
				}
			}
		});
		return tree;
	}

	/**
	 * Creates and returns a <code>MutableTreeNode</code> for the
	 * <code>obj</code> in the system preferences space.
	 * 
	 * @param preference
	 *            the object containing preferences (passed to
	 *            {@link Preferences#systemNodeForPackage(Class)}
	 * @return a new tree node based on the specified <code>obj</code>
	 */
	protected MutableTreeNode createSystemRootNode(Preferences preference) {
		MutableTreeNode node = null;
		try {
			if (preference == null) {
				node = new PrefTreeNode(Preferences.systemRoot());
			} else {
				node = new PrefTreeNode(preference);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
			node = new DefaultMutableTreeNode("No System Preferences!");
		}
		return node;
	}

	/**
	 * Creates and returns a <code>MutableTreeNode</code> for the
	 * <code>obj</code> in the user preferences space.
	 * 
	 * @param preference
	 *            the object containing preferences (passed to
	 *            {@link Preferences#userNodeForPackage(Class)}
	 * @return a new tree node based on the specified <code>obj</code>
	 */
	protected MutableTreeNode createUserRootNode(Preferences preference) {
		MutableTreeNode node = null;
		try {
			if (preference == null) {
				node = new PrefTreeNode(Preferences.userRoot());
			} else {
				node = new PrefTreeNode(preference);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
			node = new DefaultMutableTreeNode("No User Preferences!");
		}
		return node;
	}

}