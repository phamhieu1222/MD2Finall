import java.io.*;
import java.util.*;

public class ContactManager {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public boolean updateContact(String phone, Contact newInfo) {
        for (Contact c : contacts) {
            if (c.getPhoneNumber().equals(phone)) {
                c.setGroup(newInfo.getGroup());
                c.setName(newInfo.getName());
                c.setGender(newInfo.getGender());
                c.setAddress(newInfo.getAddress());
                c.setBirthday(newInfo.getBirthday());
                c.setEmail(newInfo.getEmail());
                return true;
            }
        }
        return false;
    }

    public boolean deleteContact(String phone) {
        for (Contact c : contacts) {
            if (c.getPhoneNumber().equals(phone)) {
                contacts.remove(c);
            }
        }
        return true;
    }
    public List<Contact> search(String keyword) {
        keyword = keyword.toLowerCase();
        List<Contact> result = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getPhoneNumber().contains(keyword) || c.getName().toLowerCase().contains(keyword)) {
                result.add(c);
            }
        }
        return result;
    }
    public void showAllContacts() {
        int count = 0;
        for (Contact c : contacts) {
            System.out.println(c.toCSV());
            count++;
            if (count % 5 == 0) {
                System.out.println("Press Enter to continue");
                new Scanner(System.in).nextLine();
            }
        }
    }

    public void readFromFile(String fileName) throws IOException {
        contacts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                contacts.add(Contact.fromCSV(line));
            }
        }
    }

    public void writeToFile(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact c : contacts) {
                bw.write(c.toCSV());
                bw.newLine();
            }
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
