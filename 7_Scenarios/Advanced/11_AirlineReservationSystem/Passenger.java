public class Passenger {
    private static int idGen = 1;
    private final int passengerId;
    private final String name;
    private final String email;
    private final String phone;

    public Passenger(String name, String email, String phone) {
        this.passengerId = idGen++;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getPassengerId() { return passengerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
