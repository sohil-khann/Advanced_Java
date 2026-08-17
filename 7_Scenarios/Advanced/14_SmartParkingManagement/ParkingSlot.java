public class ParkingSlot {
    public enum Type { CAR, BIKE, TRUCK }
    public enum Status { AVAILABLE, OCCUPIED, RESERVED }

    private static int idGen = 1;
    private final int slotId;
    private final Type type;
    private Status status;
    private Vehicle vehicle;

    public ParkingSlot(Type type) {
        this.slotId = idGen++;
        this.type = type;
        this.status = Status.AVAILABLE;
    }

    public int getSlotId() { return slotId; }
    public Type getType() { return type; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
}
