/**
 * Represents a parking slot in the parking lot.
 */
public class ParkingSlot {
    private final int slotId;
    private final String slotType; // CAR, BIKE, TRUCK, GENERAL
    private Vehicle parkedVehicle;
    private boolean isOccupied;

    /**
     * Constructs a ParkingSlot.
     *
     * @param slotId   the unique slot identifier
     * @param slotType the type of vehicle this slot accommodates
     */
    public ParkingSlot(int slotId, String slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public int getSlotId() { return slotId; }
    public String getSlotType() { return slotType; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
    public boolean isOccupied() { return isOccupied; }

    /**
     * Parks a vehicle in this slot.
     *
     * @return true if parking was successful
     */
    public boolean park(Vehicle vehicle) {
        if (isOccupied) {
            return false;
        }
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    /**
     * Removes the vehicle from this slot.
     *
     * @return the removed vehicle, or null if slot was empty
     */
    public Vehicle leave() {
        if (!isOccupied) {
            return null;
        }
        Vehicle vehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        this.isOccupied = false;
        return vehicle;
    }

    @Override
    public String toString() {
        return String.format("Slot{id=%d, type='%s', occupied=%s, vehicle=%s}",
                slotId, slotType, isOccupied, parkedVehicle != null ? parkedVehicle.getLicensePlate() : "None");
    }
}
