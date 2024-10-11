import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PhonebookApp extends JFrame {
    private final Phonebook phonebook;
    private final JTextArea displayArea;

    public PhonebookApp() {
        phonebook = new Phonebook();
        setTitle("Phonebook Application");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background color to aqua
        getContentPane().setBackground(new Color(0, 255, 255)); // Aqua background

        // Add a title panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Namibian Phonebook"), SwingConstants.CENTER);
        titlePanel.setBackground(new Color(70, 130, 180));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setForeground(Color.WHITE);
        add(titlePanel, BorderLayout.NORTH);

        // Set up the display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(240, 248, 255));
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Button panel with improved styling
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Insert Contact Button
        JButton insertButton = new JButton("Insert Contact");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Contact Name:");
                String phone = JOptionPane.showInputDialog("Enter Phone Number:");
                if (name != null && phone != null) {
                    phonebook.insertContact(name, phone);
                }
            }
        });
        buttonPanel.add(insertButton);

        //Search Contact Button
        JButton searchButton = new JButton("Search Contact");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Contact Name to Search:");
                Contact contact = phonebook.searchContact(name);
                if (contact != null) {
                    JOptionPane.showMessageDialog(null, "Found: " + contact);
                } else {
                    JOptionPane.showMessageDialog(null, "Contact not found.");
                }
            }
        });
        buttonPanel.add(searchButton);

        // Display All Contacts Button
        JButton displayButton = new JButton("Display All Contacts");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(""); // Clear the area
                List<Contact> contacts = phonebook.getContacts();
                if (contacts.isEmpty()) {
                    displayArea.setText("Phonebook is empty.");
                } else {
                    for (Contact contact : contacts) {
                        displayArea.append(contact.toString() + "\n");
                    }
                }
            }
        });
        buttonPanel.add(displayButton);

        // Delete Contact Button
        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Contact Name to Delete:");
                if (name != null) {
                    phonebook.deleteContact(name);
                }
            }
        });
        buttonPanel.add(deleteButton);

        // Update Contact Button
        JButton updateButton = new JButton("Update Contact");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Contact Name to Update:");
                if (name != null) {
                    String newName = JOptionPane.showInputDialog("Enter New Name (Leave empty to keep the same):");
                    String newPhone = JOptionPane.showInputDialog("Enter New Phone (Leave empty to keep the same):");
                    phonebook.updateContact(name, newName, newPhone);
                }
            }
        });
        buttonPanel.add(updateButton);

        // Sort Contacts Button
        JButton sortButton = new JButton("Sort Contacts");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                phonebook.sortContacts();
            }
        });
        buttonPanel.add(sortButton);

        // Analyze Efficiency Button
        JButton efficiencyButton = new JButton("Analyze Efficiency");
        efficiencyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, phonebook.analyzeSearchEfficiency());
            }
        });
        buttonPanel.add(efficiencyButton);

        // Add button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PhonebookApp app = new PhonebookApp();
            app.setVisible(true);
        });
    }
}
