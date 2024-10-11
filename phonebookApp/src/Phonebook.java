import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Phonebook {
    private final List<Contact> contacts;

    public Phonebook() {
        this.contacts = new ArrayList<>();
    }

    public void insertContact(String name, String phone) {
        Contact newContact = new Contact(name, phone);
        contacts.add(newContact);
        JOptionPane.showMessageDialog(null, "Contact " + name  +  " added.");
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null; // Not found
    }

    public void deleteContact(String name) {
        Contact toRemove = null;
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(name)) {
                toRemove = contact;
                break;
            }
        }


        if (toRemove != null) {
            contacts.remove(toRemove);
            JOptionPane.showMessageDialog(null, "Contact " + name + " deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Contact " + name + " not found.");
        }
    }

    public void updateContact(String name, String newName, String newPhone) {
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(name)) {
                if (newName != null && !newName.isEmpty()) {
                    contact.name = newName;
                }
                if (newPhone != null && !newPhone.isEmpty()) {
                    contact.phone = newPhone;
                }
                JOptionPane.showMessageDialog(null, "Contact " + name + " updated.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Contact " + name + " not found.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void sortContacts() {
        contacts.sort(Comparator.comparing(c -> c.name));
        JOptionPane.showMessageDialog(null, "Contacts sorted.");
    }

    public String analyzeSearchEfficiency() {
        return "The search operation is O(n), where n is the number of contacts.";
    }
}
