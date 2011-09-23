package org.jpreferences;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

/**
 * A <code>PreferenceDialog</code> provides a graphical interface for users to
 * interact with preferences. The default behavior will display
 * {@link Preferences} nodes passed to the constructors.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class PreferenceDialog extends JDialog {

	/** The preference node tree the user interacts with to view preferences. */
	private JTree tree;
	/** The table displaying the preferences and their values. */
	private JTable editTable;
	/** The root preference nodes to be displayed in the {@link #tree}. */
	private Preferences[] preferences;

	/** Whether the search feature is enabled or disabled. */
	private boolean searchEnabled;
	/** Whether custom pages are allowed to be added. */
	private boolean customPagesEnabled;

	// TODO implement search for preferences and values

	/**
	 * Creates a <code>PreferenceDialog</code> calling
	 * {@link JDialog#JDialog(Dialog, boolean)}.
	 * 
	 * @param parent
	 *            the <code>Dialog</code> from which the dialog is displayed or
	 *            <code>null</code> if this dialog has no owner
	 * @param preferences
	 *            the root preference nodes
	 */
	public PreferenceDialog(Dialog parent, Preferences... preferences) {
		super(parent, true);
		setPreferences(preferences);
		initializeDialog();
	}

	/**
	 * Creates a <code>PreferenceDialog</code> calling
	 * {@link JDialog#JDialog(Window, boolean)}.
	 * 
	 * @param parent
	 *            the <code>Window</code> from which the dialog is displayed or
	 *            <code>null</code> if this dialog has no owner
	 * @param conf
	 *            the configuration
	 * @param preferences
	 *            the root preference nodes
	 */
	public PreferenceDialog(Window parent, Preferences... preferences) {
		super(parent, ModalityType.DOCUMENT_MODAL);
		setPreferences(preferences);
		initializeDialog();
	}

	/**
	 * Initializes the User Interface (UI) for this dialog. A left panel
	 * contains a tree hierarchy; when the user selects a node the
	 * {@link #editTable} displays the preferences for that node.
	 */
	protected void initializeDialog() {
		try {
			setIconImage(ImageIO.read(PreferenceDialog.class.getClassLoader()
					.getResourceAsStream("preferences.png")));
		} catch (IOException e) {
			// let system use default image
		}
		setLayout(new BorderLayout());
		setTitle("Preferences Dialog");
		setMinimumSize(new Dimension(400, 400));

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
		editTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				"deletePreference");
		editTable.getActionMap().put("deletePreference", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteRows(editTable.getSelectedRows());
			}

			private void deleteRows(int... rows) {
				TableModel model = editTable.getModel();
				if (model instanceof PreferenceTableModel) {
					PreferenceTableModel prefModel = (PreferenceTableModel) model;
					for (int i = 0; i < rows.length; i++) {
						prefModel.removeRow(rows[i]);
					}
				}
			}
		});
		editTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "addPreference");
		editTable.getActionMap().put("addPreference", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO show dialog
				// addRow("New Key", "New Value");
			}

			private void addRow(String key, Object value) {
				TableModel model = editTable.getModel();
				if (model instanceof PreferenceTableModel) {
					PreferenceTableModel prefModel = (PreferenceTableModel) model;
					prefModel.addRow(key, value);
				}
			}
		});

		//
		// TreePanel -- panel containing the tree hierarchy
		//
		JPanel treePanel = new JPanel(new BorderLayout());
		if (isSearchEnabled()) {
			JTextField searchField = new JTextField("Search...");
			treePanel.add(searchField, BorderLayout.NORTH);
		}
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(null);
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);

		tree = createTree(true, false, getPreferences());
		tree.setCellRenderer(renderer);
		treePanel.add(new JScrollPane(tree), BorderLayout.CENTER);

		// Buttons
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(closeAction);
		buttonPanel.add(closeButton);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setLeftComponent(treePanel);
		splitPane.setRightComponent(new JScrollPane(editTable));
		add(splitPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(getParent());
		pack();
	}

	/**
	 * Returns whether the search feature is enabled or disabled. This feature
	 * displays a search box and filters the preference nodes based on the
	 * search result.
	 * 
	 * @see #enableSearch(boolean)
	 * @return <code>true</code> if the feature is enabled, else
	 *         <code>false</code>
	 */
	public boolean isSearchEnabled() {
		return searchEnabled;
	}

	/**
	 * Enables or disables the search feature. This feature displays a search
	 * box and filters the preference nodes based on the search result.
	 * 
	 * @see #isSearchEnabled()
	 * @param enable
	 *            <code>true</code> to enable the feature, <code>false</code> to
	 *            disable
	 */
	public void enableSearch(boolean enable) {
		searchEnabled = enable;
	}

	/**
	 * Returns whether the custom pages feature is enabled or disabled. This
	 * feature enables/disables the ability to add custom preference pages.
	 * 
	 * @see #enableCustomPages(boolean)
	 * @return <code>true</code> if the feature is enabled, else
	 *         <code>false</code>
	 */
	public boolean isCustomPagesEnabled() {
		return customPagesEnabled;
	}

	/**
	 * Enables or disables the custom pages feature. This feature
	 * enables/disables the ability to add custom preference pages.
	 * 
	 * @see #isCustomPagesEnabled()
	 * @param enable
	 *            <code>true</code> to enable the feature, <code>false</code> to
	 *            disable
	 */
	public void enableCustomPages(boolean enable) {
		this.customPagesEnabled = enable;
	}

	/**
	 * Returns the root preference nodes to be displayed in the tree.
	 * 
	 * @return the root preferences nodes
	 */
	public Preferences[] getPreferences() {
		return preferences;
	}

	/**
	 * Sets the root preference nodes to be displayed in the tree.
	 * 
	 * @param preferences
	 *            the root preferences nodes
	 */
	public void setPreferences(Preferences... preferences) {
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
					PreferenceTreeNode node = (PreferenceTreeNode) e.getPath()
							.getLastPathComponent();
					Preferences pref = node.getPrefObject();
					editTable.setModel(new PreferenceTableModel(pref));
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
	 * <code>preference</code> in the system preferences space.
	 * <p>
	 * This is equivalent to <code>createRootNode(preference, false)</code>
	 * 
	 * @param preference
	 *            the preferences object
	 * @return a new tree node based on the specified <code>preference</code>
	 */
	protected MutableTreeNode createSystemRootNode(Preferences preference) {
		return createRootNode(preference, false);
	}

	/**
	 * Creates and returns a <code>MutableTreeNode</code> for the
	 * <code>preference</code> in the user preferences space.
	 * <p>
	 * This is equivalent to <code>createRootNode(preference, true/code>
	 * 
	 * @param preference
	 *            the preferences object
	 * @return a new tree node based on the specified <code>preference</code>
	 */
	protected MutableTreeNode createUserRootNode(Preferences preference) {
		return createRootNode(preference, true);
	}

	/**
	 * Creates and returns a <code>MutableTreeNode</code> for the
	 * <code>preference</code> in the user preferences space.
	 * 
	 * @param preference
	 *            the preferences object
	 * @param defaultUser
	 *            <code>true</code> to fall back to
	 *            <code>Preferences.userRoot()</code> or <code>false</code> to
	 *            fall back to <code>Preferences.systemRoot()</code> if
	 *            <code>pref</code> is <code>null</code>
	 * @return a new tree node based on the specified <code>obj</code>
	 */
	protected MutableTreeNode createRootNode(Preferences preference,
			boolean defaultUser) {
		MutableTreeNode node = null;
		try {
			if (preference == null) {
				node = new PreferenceTreeNode(defaultUser ? Preferences.userRoot()
						: Preferences.systemRoot());
			} else {
				node = new PreferenceTreeNode(preference);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
			node = new DefaultMutableTreeNode(
					defaultUser ? "No User Preferences!"
							: "No System Preferences!");
		}
		return node;
	}

}