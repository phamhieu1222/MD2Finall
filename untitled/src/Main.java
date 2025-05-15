import java.io.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactManager manager = new ContactManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("---- Contacts Management Program ----");
            System.out.println("1. Watch List Contacts");
            System.out.println("2. Add Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Search Contact");
            System.out.println("6. Read file");
            System.out.println("7. Write file");
            System.out.println("8. Exit");
            System.out.print("Press number: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewContacts();
                    break;
                case "2":
                    addContact();
                    break;
                case "3":
                    updateContact();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    searchContact();
                    break;
                case "6":
                    readFromFile();
                    break;
                case "7":
                    writeToFile();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Incorrect choice!");
            }
        }
    }
    public static boolean isValidPhone(String phone) {
        return phone.matches("^0[0-9]{9}$");
    }
    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private static void viewContacts() {
        manager.showAllContacts();
    }

    private static void addContact() {
        Contact contact = inputContact();
        if (contact != null) {
            manager.addContact(contact);
            System.out.println("Add successfully!");
        }
    }

    private static void updateContact() {
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        Contact updated = inputContact();
        if (updated != null && manager.updateContact(phone, updated)) {
            System.out.println("Update successfully!");
        } else {
            System.out.println("Phone Number is not found");
        }
    }

    private static void deleteContact() {
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Are you sure to delete? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            if (manager.deleteContact(phone)) {
                System.out.println("Delete successfully!");
            } else {
                System.out.println("Phone Number is not found");
            }
        }
    }

    private static void readFromFile() {
        System.out.print("Read from File will delete all contacts, continue anyway? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            try {
                manager.readFromFile("data/contacts.csv");
                System.out.println("Read successfully!");
            } catch (IOException e) {
                System.out.println("Can't read from file!");
            }
        }
    }

    private static void writeToFile() {
        System.out.print("Write to File will overload all contacts, continue anyway? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            try {
                manager.writeToFile("data/contacts.csv");
                System.out.println("Write successfully!");
            } catch (IOException e) {
                System.out.println("Can't write to file!");
            }
        }
    }

    private static void searchContact() {
        System.out.print("Enter Phone Number or Name: ");
        String keyword = scanner.nextLine();
        List<Contact> found = manager.search(keyword);
        for (Contact c : found) {
            System.out.println(c);
        }
    }

    private static Contact inputContact() {
        try {
            System.out.print("Phone Number (required): ");
            String phone = scanner.nextLine().trim();
            if (!isValidPhone(phone)) {
                System.out.println(" Invalid phone number! Must be 10 digits starting with 0.");
                return null;
            }

            System.out.print("Group: ");
            String group = scanner.nextLine().trim();

            System.out.print("Name (required): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(" Name cannot be empty.");
                return null;
            }

            System.out.print("Gender: ");
            String gender = scanner.nextLine().trim();

            System.out.print("Address: ");
            String address = scanner.nextLine().trim();

            System.out.print("Birthday: ");
            String birthdate = scanner.nextLine().trim();

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty() && !isValidEmail(email)) {
                System.out.println(" Invalid email format!");
                return null;
            }

            return new Contact(phone, group, name, gender, address, birthdate, email);
        } catch (Exception e) {
            System.out.println(" Invalid input encountered.");
            return null;
        }
    }
}