package org.jpreferences.ui;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.jpreferences.ICurrentPageListener;
import org.jpreferences.IPreferenceManager;
import org.jpreferences.model.IPreferenceNode;

/**
 * Provides a graphical interface for users to interact with preference pages.
 * From these preference pages, preferences may be updated.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
@SuppressWarnings("serial")
public class PreferenceDialog extends JDialog implements ICurrentPageListener {

	/**
	 * Buttons available on the <code>PreferencePage</code>.
	 * <p>
	 * TODO implement feature to add new Buttons (ex: addButton(JButton))
	 * </p>
	 * 
	 * @author Erich Schroeter
	 * @version 1.0
	 * @created 02-May-2011 6:21:06 PM
	 */
	public enum Buttons {
		/**
		 * save the changed preferences
		 */
		OK,
		/**
		 * do not save the changed preferences
		 */
		CANCEL, DEFAULTS
	}

	/**
	 * The object managing the <code>IPreferenceNode</code>'s which in turn
	 * represent a corresponding <code>IPreferencePage</code>.
	 */
	private IPreferenceManager manager;
	/**
	 * The interface object the user interacts with to view properties from
	 * different pages.
	 */
	private JTree tree;
	/**
	 * The <code>IPreferencePage</code> represented by the selected
	 * <code>IPreferenceNode</code>. This value changes as the selected
	 * <code>IPreferenceNode</code> changes.
	 */
	private IPreferencePage currentPage;
	/**
	 * The <code>JPanel</code> that displays the current
	 * <code>PreferencePage</code>.
	 */
	private JPanel pagePanel;

	private static final GridBagConstraints pagePanelConstraints = new GridBagConstraints(
			0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
			GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0);
	protected Buttons buttons;

	/**
	 * Creates a <code>PreferenceDialog</code> calling {@link JDialog(Dialog,
	 * boolean)}.
	 * 
	 * @param parent
	 *            the <code>Dialog</code> from which the dialog is displayed or
	 *            null if this dialog has no owner
	 * @param manager
	 *            the <code>PreferenceManager</code> that managing the
	 *            preferences
	 */
	public PreferenceDialog(Dialog parent, IPreferenceManager manager) {
		super(parent, true);
		this.manager = manager;
		init();
	}

	/**
	 * Creates a <code>PreferenceDialog</code> calling {@link JDialog(Window,
	 * boolean)}.
	 * 
	 * @param parent
	 *            the <code>Window</code> from which the dialog is displayed or
	 *            null if this dialog has no owner
	 * @param manager
	 *            the <code>PreferenceManager</code> that managing the
	 *            preferences
	 */
	public PreferenceDialog(Window parent, IPreferenceManager manager) {
		super(parent);
		this.manager = manager;
		init();
	}

	/**
	 * Creates a <code>PreferenceDialog</code> to display the preferences being
	 * managed by <code>manager</code>.
	 * 
	 * @param parent
	 *            the parent dialog to use for modality
	 * @param manager
	 *            the manager of the preferences
	 * @return the <code>PreferenceDialog</code> that was created and to be
	 *         shown
	 */
	public static PreferenceDialog showDialog(Dialog parent,
			IPreferenceManager manager) {
		PreferenceDialog dialog = new PreferenceDialog(parent, manager);
		return dialog;
	}

	/**
	 * Creates a <code>PreferenceDialog</code> to display the preferences being
	 * managed by <code>manager</code>.
	 * 
	 * @param parent
	 *            the parent window to use for modality
	 * @param manager
	 *            the manager of the preferences
	 * @return the <code>PreferenceDialog</code> that was created and to be
	 *         shown
	 */
	public static PreferenceDialog showDialog(Window parent,
			IPreferenceManager manager) {
		PreferenceDialog dialog = new PreferenceDialog(parent, manager);
		return dialog;
	}

	/**
	 * Initializes the User Interface (UI) for this dialog. A left panel
	 * contains a tree hierarchy of the {@link IPreferenceNode}s; when the user
	 * selects a <code>IPreferenceNode</code> the page panel (the right on the
	 * right) updates to show the {@link IPreferencePage} associated with the
	 * <code>IPreferenceNode</code>.
	 */
	private void init() {
		setLayout(new GridBagLayout());
		setTitle("Preferences Dialog");
		setMinimumSize(new Dimension(400, 400));
		// add a window listener to save the preferences when the dialog closes
		addWindowListener(dialogWindowListener());
		KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(cancelActionListener(),
				escapeStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		tree = new JTree(manager.getRoot());
		manager.addCurrentPageListener(this);
		currentPage = manager.getCurrentPage();

		GridBagConstraints c;

		//
		// TreePanel -- panel containing the tree hierarchy
		//
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(null);
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);

		tree.setCellRenderer(renderer);
		tree.setBorder(BorderFactory.createLoweredBevelBorder());
		tree.addTreeSelectionListener(pageTreeSelectionListener());

		c = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		JPanel treePanel = new JPanel(new GridBagLayout());
		treePanel.add(tree, c);

		//
		// PagePanel
		//
		pagePanel = new JPanel(new GridBagLayout());
		pagePanel.add(currentPage.getContents(), pagePanelConstraints);

		// Buttons (i.e. OK, Cancel, Defaults)
		JPanel buttonPanel = generateButtons(new Buttons[] { Buttons.OK,
				Buttons.CANCEL, Buttons.DEFAULTS });
		c = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0);
		pagePanel.add(buttonPanel, c);
		JButton okButton = (JButton) buttonPanel.getComponent(0);
		okButton.addActionListener(okActionListener());
		JButton cancelButton = (JButton) buttonPanel.getComponent(1);
		cancelButton.addActionListener(cancelActionListener());
		JButton defaultButton = (JButton) buttonPanel.getComponent(2);
		defaultButton.addActionListener(defaultActionListener());

		//
		// add both of the main panels to this JDialog
		//
		c = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(treePanel, c);
		c = new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(new JScrollPane(pagePanel), c);

		pack();
		setLocationRelativeTo(getParent());
	}

	/**
	 * Handles displaying the given page.
	 * <p>
	 * This function removes the <i>currentPage</i> <code>Component</code> from
	 * <i>pagePanel</i> and adds the given <code>IPreferencePage</code> to
	 * <i>pagePanel</i>. After adding the newly selected page to the panel, it
	 * then calls <code>JPanel.updateUI()<code>.
	 * </p>
	 * 
	 * @param page
	 *            the new page to display
	 */
	public void changePageTo(IPreferencePage page) {
		manager.setCurrentPage(page);
	}

	/**
	 * 
	 * @param path
	 */
	public void openPage(String path) {
		manager.setCurrentPage(path);
	}

	/**
	 * Returns the <code>IPreferenceNode</code> selected in the
	 * <code>JTree</code>.
	 * <p>
	 * Uses <code>TreePath.getSelectionPath().getLastPathComponent()</code> to
	 * retrieve the <code>TreeNode</code> and casts the result to
	 * <code>IPreferenceNode</code>.
	 * </p>
	 * 
	 * @return The selected node
	 */
	public IPreferenceNode getSelectedNode() {
		return (IPreferenceNode) tree.getSelectionPath().getLastPathComponent();
	}

	/**
	 * Generates a <code>JPanel</code> object with the specified buttons. A
	 * <code>FlowLayout</code> is used to layout the buttons. Instances of
	 * <code>JButton</code> are created for each
	 * <code>Buttons<code> specified in the array.
	 * 
	 * @return <code>JPanel</code> with specified buttons.
	 * 
	 * @param buttons
	 */
	protected JPanel generateButtons(Buttons[] buttons) {
		JPanel panel = new JPanel(new FlowLayout());
		List<Buttons> list = Arrays.asList(buttons);

		if (list.contains(Buttons.OK)) {
			JButton button = new JButton("OK");
			button.setName(button.getText().toLowerCase());
			button.setToolTipText("Save the changes made on this page");
			panel.add(button);
		}
		if (list.contains(Buttons.CANCEL)) {
			JButton button = new JButton("Cancel");
			button.setName(button.getText().toLowerCase());
			button.setToolTipText("Ignore the intended changes made on this page");
			panel.add(button);
		}
		if (list.contains(Buttons.DEFAULTS)) {
			JButton button = new JButton("Defaults");
			button.setName(button.getText().toLowerCase());
			button.setToolTipText("Restores the default values for this page");
			panel.add(button);
		}

		return panel;
	}

	/**
	 * @return an <code>TreeSelectionListener</code> which handles casting the
	 *         selected path's <code>Component</code> to a
	 *         <code>PreferencePage</code> and passing it to
	 *         <code>{@link PreferenceDialog#changePageTo(IPreferencePage)</code>
	 */
	private TreeSelectionListener pageTreeSelectionListener() {
		return new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				IPreferenceNode node = (IPreferenceNode) e.getPath()
						.getLastPathComponent();
				changePageTo(node.getPage());
			}
		};
	}

	/**
	 * @return an <code>WindowListener</code> which handles calling the current
	 *         page's <code>performCancel()</code> function when the window is
	 *         closing.
	 */
	private WindowListener dialogWindowListener() {
		return new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				currentPage.performCancel();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				currentPage.performCancel();
			}
		};
	}

	private ActionListener okActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage.performOk();
				setVisible(false);
				dispose();
			}
		};
	}

	private ActionListener cancelActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage.performCancel();
				setVisible(false);
				dispose();
			}
		};
	}

	private ActionListener defaultActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage.performDefault();
			}
		};
	}

	//
	// ICurrentPageListener members
	//

	@Override
	public void currentPageChanged(IPreferencePage current) {
		pagePanel.remove(currentPage.getContents());
		currentPage = current;
		pagePanel.add(currentPage.getContents(), pagePanelConstraints);
		pack();
		update(getGraphics());
	}

}