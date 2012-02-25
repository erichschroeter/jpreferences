package usr.erichschroeter.jpreferences.pages;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import usr.erichschroeter.jpreferences.page.CustomPage;

/**
 * The <code>ProfilesPage</code> is a preference page that allows users to view,
 * edit, and delete profiles.
 * 
 * @author Erich Schroeter
 */
public class ProfilesPage extends CustomPage<JPanel> {

	/** A reference to the profiles to be displayed in the table. */
	private List<String[]> profiles;
	/** The table displaying the profiles. */
	private JTable profilesTable;

	/**
	 * Constructs a default <code>ProfilesPage</code>.
	 * <p>
	 * This is equivalent to <code>super("Profiles")</code>.
	 */
	public ProfilesPage(List<String[]> profiles) {
		super("Profiles");
		setProfiles(profiles);
	}

	/**
	 * Returns the profiles being displayed and interacted with.
	 * 
	 * @return the profiles
	 */
	public List<String[]> getProfiles() {
		return profiles;
	}

	/**
	 * Sets the profiles to be displayed and interacted with.
	 * 
	 * @param profiles
	 *            the profiles to set
	 */
	public void setProfiles(List<String[]> profiles) {
		// create array for profiles and skip the header
		String[][] p = new String[profiles.size() - 1][];
		for (int i = 0; i < profiles.size() - 1; i++) {
			p[i] = profiles.get(i + 1);
		}
		this.profiles = profiles;
		profilesTable.setModel(new DefaultTableModel(p, profiles.get(0)));
	}

	@Override
	protected void initializePage(JPanel page) {
		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
		JButton editButton = new JButton("Edit");

		profilesTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(profilesTable);

		GroupLayout layout = new GroupLayout(page);
		page.setLayout(layout);

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane).addGroup(
						layout.createSequentialGroup().addComponent(addButton)
								.addComponent(editButton).addComponent(
										deleteButton)));
		layout.linkSize(SwingConstants.HORIZONTAL, addButton, editButton,
				deleteButton);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(
				scrollPane).addGroup(
				layout.createParallelGroup(Alignment.BASELINE).addComponent(
						addButton).addComponent(editButton).addComponent(
						deleteButton)));
	}

}
