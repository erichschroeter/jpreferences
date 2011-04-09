package org.prefs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class PreferenceDialog extends JDialog implements
		IPreferencePageContainer {

	private static final long serialVersionUID = 2693914066017357963L;

	/**
	 * The object handling the storage of the preferences.
	 * 
	 * <p>
	 * The <code>PreferenceDialog</code> keeps it's own reference to a
	 * <code>IPreferencePersistentStorage</code> in order to allow for the
	 * dialog to be reused for multiple separate stores.
	 * </p>
	 */
	private IPreferencePersistentStorage store;

	/**
	 * The object managing the <code>IPreferenceNode</code>'s which in turn
	 * represent a corresponding <code>IPreferencePage</code>.
	 */
	private PreferenceManager manager;

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
	private PreferencePage currentPage;

	/**
	 * The <code>JPanel</code> that displays the current
	 * <code>PreferencePage</code>.
	 */
	private JPanel pagePanel;

	/**
	 * Buttons available on the <code>PreferencePage</code>.
	 * <p>
	 * TODO implement feature to add new Buttons (ex: addButton(JButton))
	 * </p>
	 */
	public enum Buttons {
		OK, // save the changed preferences
		CANCEL, // do not save the changed preferences
		DEFAULTS
		// reset the preferences to default values
	}

	/**
	 * Array of <code>Button</code> enum values to be passed to
	 * <code>generateButtons(Buttons[])</code>.
	 */
	protected Buttons[] buttons;

	/**
	 * Calls
	 * <code>PreferenceDialog(Window, PreferenceManager, ModalityType)</code>
	 * passing the <i>parent</i> and <i>manager</i> arguments and
	 * <code>ModalityType.APPLICATION_MODAL</code>.
	 * 
	 * @param parent
	 *            the <code>Window</code> from which the dialog is displayed or
	 *            null if this dialog has no owner
	 * @param manager
	 *            the <code>PreferenceManager</code> that managing the
	 *            preferences
	 */
	public PreferenceDialog(Window parent, PreferenceManager manager) {
		this(parent, manager, ModalityType.APPLICATION_MODAL);
	}

	/**
	 * Creates a <code>PreferenceDialog</code> setting the parent as the
	 * specified parent.
	 * <p>
	 * The <code>PreferenceManager</code> is used to set the dialog's
	 * <code>IPreferencePersistentStorage</code> attribute. The
	 * <code>IPreferencePersistentStorage</code> may be changed in order to
	 * reuse this <code>PreferenceDialog</code> instance for other
	 * <code>IPreferencePersistentStorage</code>'s.
	 * </p>
	 * 
	 * @see PreferenceDialog#store
	 * @param parent
	 *            the <code>Window</code> from which the dialog is displayed or
	 *            null if this dialog has no owner
	 * @param manager
	 *            the <code>PreferenceManager</code> that managing the
	 *            preferences
	 * @param modality
	 *            specifies whether dialog blocks input to other windows when
	 *            shown. <code>null</code> value and unsupported modality types
	 *            are equivalent to MODELESS
	 */
	public PreferenceDialog(Window parent, PreferenceManager manager,
			ModalityType modality) {
		super(parent, modality);
		this.manager = manager;
		this.store = manager.getStore();

		tree = new JTree(manager.getRoot());
		currentPage = this.manager.getRoot().getPage();

		init();

		// position the dialog box relative to the parent
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2
				- getHeight() / 2);
	}

	/**
	 * Initializes the User Interface (UI) for this dialog. A left panel
	 * contains a tree hierarchy of the <code>IPreferenceNode</code>'s; when the
	 * user selects a <code>IPreferenceNode</code> the page panel (the right on
	 * the right) updates to show the <code>IPreferencePage</code> corresponding
	 * to the <code>IPreferenceNode</code>.
	 */
	private void init() {
		setTitle("Preferences Dialog");
		setMinimumSize(new Dimension(400, 400));
		// add a window listener to save the preferences when the dialog closes
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				currentPage.performCancel();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		GridBagConstraints c;
		c = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);

		//
		// TreePanel -- panel containing the tree heirarchy
		//
		JPanel treePanel = new JPanel(new GridBagLayout());
		treePanel.add(tree, c);

		tree.setRootVisible(false);
		tree.setBorder(BorderFactory.createLoweredBevelBorder());
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				PreferenceNode node = (PreferenceNode) e.getPath()
						.getLastPathComponent();
				PreferencePage page = node.getPage();
				changePageTo(page);
			}
		});
		//
		// TreePanel end
		//

		//
		// PagePanel -- container for all the PreferencePage's
		//
		pagePanel = new JPanel(new BorderLayout());
		pagePanel.add(currentPage, BorderLayout.CENTER);

		// Buttons (i.e. OK, Cancel, Defaults)
		JPanel buttonPanel = generateButtons(new Buttons[] { Buttons.OK,
				Buttons.CANCEL, Buttons.DEFAULTS });
		pagePanel.add(buttonPanel, BorderLayout.SOUTH);
		JButton okButton = (JButton) buttonPanel.getComponent(0);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage.performOk();
				closeWindow();
				try {
					getPreferenceStore().save();
				} catch (IOException error) {
					
				}
			}
		});
		JButton cancelButton = (JButton) buttonPanel.getComponent(1);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage.performCancel();
				closeWindow();
			}
		});
		JButton defaultButton = (JButton) buttonPanel.getComponent(2);
		defaultButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage.performDefault();
			}
		});
		//
		// PagePanel end
		//

		//
		// add both of the main panels to this JDialog
		//
		setLayout(new GridBagLayout());

		c = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(treePanel, c);
		c = new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(new JScrollPane(pagePanel), c);
		pack();
	}
	
	public void closeWindow() {
		setVisible(false);
	}

	/**
	 * Handles displaying the given page.
	 * 
	 * <p>
	 * This function removes the <i>currentPage</i> <code>Component</code> from
	 * <i>pagePanel</i> and adds the given <code>IPreferencePage</code> to
	 * <i>pagePanel</i>. After adding the newly selected page to the panel, it
	 * then calls <code>JPanel.updateUI()<code>.
	 * </p>
	 * 
	 * @param page
	 */
	private void changePageTo(IPreferencePage page) {
		if (currentPage.okToLeave()) {
			pagePanel.remove(currentPage);
			currentPage = (PreferencePage) page;
			pagePanel.add(currentPage);
			pagePanel.updateUI();
		}
	}

	/**
	 * Returns the <code>IPreferenceNode</code> selected in the
	 * <code>JTree</code>.
	 * 
	 * <p>
	 * Uses <code>TreePath.getSelectionPath().getLastPathComponent()</code> to
	 * retrieve the <code>TreeNode</code> and casts the result to
	 * <code>IPreferenceNode</code>.
	 * </p>
	 * 
	 * @return The selected node
	 */
	public IPreferenceNode getSelectedNode() {
		TreePath path = tree.getSelectionPath();
		return (IPreferenceNode) path.getLastPathComponent();
	}

	/**
	 * Generates a <code>JPanel</code> object with the specified buttons. A
	 * <code>FlowLayout</code> is used to layout the buttons. Instances of
	 * <code>JButton</code> are created for each
	 * <code>Buttons<code> specified in the array.
	 * 
	 * @return <code>JPanel</code> with specified buttons.
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

	//
	// IPreferencePageContainer members
	//

	@Override
	public IPreferencePersistentStorage getPreferenceStore() {
		return store;
	}

	@Override
	public void setPreferenceStore(IPreferencePersistentStorage store) {
		this.store = store;
	}

	@Override
	public void updateButtons() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMessage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTitle() {
		// TODO Auto-generated method stub

	}

	@Override
	public Window getWindow() {
		return this;
	}

}
