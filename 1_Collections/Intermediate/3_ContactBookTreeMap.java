import java.util.Scanner;
import java.util.TreeMap;

public class ContactBookTreeMap {
    // Static nested Contact class
    static class Contact {
        String phoneNumber;
        String email;

        Contact(String phoneNumber, String email) {
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String toString() {
            return \"Phone: \" + phoneNumber + \", Email: \" + email;
        }
    }

    static TreeMap<String, Contact> contactBook = new TreeMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println(\"\n--- Contact Book ---\");
            System.out.println(\"1. Add Contact\");
            System.out.println(\"2. View All Contacts\");
            System.out.println(\"3. Search Contact\");
            System.out.println(\"4. Exit\");
            System.out.print(\"Enter your choice: \");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewAllContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    System.out.println(\"Exiting...\");
                    return;
                default:
                    System.out.println(\"Invalid choice!\");
            }
        }
    }

    static void addContact() {
        System.out.print(\"Enter Name: \");
        String name = scanner.nextLine();
        System.out.print(\"Enter Phone Number: \");
        String phone = scanner.nextLine();
        System.out.print(\"Enter Email: \");
        String email = scanner.nextLine();

        contactBook.put(name, new Contact(phone, email));
        System.out.println(\"Contact added successfully!\");
    }

    static void viewAllContacts() {
        if (contactBook.isEmpty()) {
            System.out.println(\"No contacts found.\");
            return;
        }
        for (String name : contactBook.keySet()) {
            System.out.println(\"Name: \" + name + \" -> \" + contactBook.get(name));
        }
    }

    static void searchContact() {
        System.out.print(\"Enter name to search: \");
        String name = scanner.nextLine();
        Contact contact = contactBook.get(name);
        if (contact != null) {
            System.out.println(\"Contact found: \" + contact);
        } else {
            System.out.println(\"Contact not found!\");
        }
    }
}
