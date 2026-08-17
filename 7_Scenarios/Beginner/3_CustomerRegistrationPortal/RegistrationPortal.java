import java.util.HashSet;

public class RegistrationPortal {
    private HashSet<Customer> customers;

    public RegistrationPortal() {
        this.customers = new HashSet<>();
    }

    public void registerCustomer(String name, String email, String mobile) {
        try {
            Customer customer = new Customer(name, email, mobile);
            if (customers.contains(customer)) {
                System.out.println("Duplicate customer: " + customer);
            } else {
                customers.add(customer);
                System.out.println("Registered: " + customer);
            }
        } catch (Customer.InvalidDataException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    public void printAllCustomers() {
        System.out.println("Total customers: " + customers.size());
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        RegistrationPortal portal = new RegistrationPortal();

        portal.registerCustomer("Priya", "priya@example.com", "1234567890");
        portal.registerCustomer("Rahul", "rahul@example.com", "1234567891");
        portal.registerCustomer("Priya", "priya@example.com", "1234567890");
        portal.registerCustomer("Arjun", "arjun@example.com", "1234567892");
        portal.registerCustomer("Invalid", "invalid-email", "1234567890");
        portal.registerCustomer("Invalid", "valid@email.com", "123");

        portal.printAllCustomers();
    }
}


