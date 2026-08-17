import java.util.regex.Pattern;

public class Vehicle {
    public enum Type { CAR, BIKE, TRUCK }

    private static final Pattern PLATE_PATTERN = Pattern.compile("^[A-Z]{2}\\d{2}[A-Z]{1,2}\\d{4}$");

    private static int idGen = 1;
    private final int vehicleId;
    private final String numberPlate;
    private final Type type;
    private final String ownerName;

    public Vehicle(String numberPlate, Type type, String ownerName) {
        this.vehicleId = idGen++;
        this.numberPlate = numberPlate;
        this.type = type;
        this.ownerName = ownerName;
    }

    public int getVehicleId() { return vehicleId; }
    public String getNumberPlate() { return numberPlate; }
    public Type getType() { return type; }
    public String getOwnerName() { return ownerName; }

    public static boolean isValidPlate(String plate) {
        return PLATE_PATTERN.matcher(plate.toUpperCase()).matches();
    }
}
