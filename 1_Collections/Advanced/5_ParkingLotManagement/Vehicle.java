/**
 * Represents a vehicle parked in the parking lot.
 */
public class Vehicle {
    private String licensePlate;
    private String ownerName;
    private String vehicleType; // CAR, BIKE, TRUCK

    /**
     * Constructs a Vehicle.
     *
     * @param licensePlate the vehicle number plate
     * @param ownerName    the owner name
     * @param vehicleType  the type of vehicle
     */
    public Vehicle(String licensePlate, String ownerName, String vehicleType) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getOwnerName() { return ownerName; }
    public String getVehicleType() { return vehicleType; }

    @Override
    public String toString() {
        return String.format("Vehicle{plate='%s', owner='%s', type='%s'}", licensePlate, ownerName, vehicleType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return licensePlate.equalsIgnoreCase(vehicle.licensePlate);
    }

    @Override
    public int hashCode() {
        return licensePlate.toLowerCase().hashCode();
    }
}
