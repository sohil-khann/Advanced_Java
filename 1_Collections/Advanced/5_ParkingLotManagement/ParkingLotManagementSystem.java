import java.util.*;
import java.util.stream.Collectors;

/**
 * Parking Lot Management System using collections.
 */
public class ParkingLotManagementSystem {
    private Map<Integer, ParkingSlot> slots; // slotId -> ParkingSlot
    private Map<String, ParkingSlot> vehicleToSlotMap; // licensePlate -> ParkingSlot

    /**
     * Constructs a parking lot with specified number of general slots.
     *
     * @param totalSlots the total number of slots
     */
    public ParkingLotManagementSystem(int totalSlots) {
        this.slots = new HashMap<>();
        this.vehicleToSlotMap = new HashMap<>();

        for (int i = 1; i <= totalSlots; i++) {
            slots.put(i, new ParkingSlot(i, "GENERAL"));
        }
    }

    /**
     * Parks a vehicle in the first available general slot.
     *
     * @return the slot number where the vehicle was parked, or -1 if no slots available
     */
    public int parkVehicle(Vehicle vehicle) {
        if (vehicleToSlotMap.containsKey(vehicle.getLicensePlate())) {
            System.out.println("Vehicle with plate " + vehicle.getLicensePlate() + " is already parked!");
            return -1;
        }

        for (ParkingSlot slot : slots.values()) {
            if (!slot.isOccupied()) {
                slot.park(vehicle);
                vehicleToSlotMap.put(vehicle.getLicensePlate(), slot);
                System.out.println("Vehicle parked at slot " + slot.getSlotId());
                return slot.getSlotId();
            }
        }

        System.out.println("No available slots!");
        return -1;
    }

    /**
     * Removes a vehicle by license plate.
     *
     * @return the slot number that was freed, or -1 if vehicle not found
     */
    public int leaveVehicle(String licensePlate) {
        ParkingSlot slot = vehicleToSlotMap.get(licensePlate);
        if (slot == null) {
            System.out.println("Vehicle with plate " + licensePlate + " not found in parking lot.");
            return -1;
        }

        Vehicle vehicle = slot.leave();
        vehicleToSlotMap.remove(licensePlate);
        System.out.println("Vehicle " + vehicle.getLicensePlate() + " left from slot " + slot.getSlotId());
        return slot.getSlotId();
    }

    /**
     * Finds the slot where a vehicle is parked by license plate.
     */
    public ParkingSlot findVehicle(String licensePlate) {
        return vehicleToSlotMap.get(licensePlate);
    }

    /**
     * Returns a list of all available (unoccupied) slots.
     */
    public List<ParkingSlot> getAvailableSlots() {
        return slots.values().stream()
                .filter(s -> !s.isOccupied())
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of all occupied slots.
     */
    public List<ParkingSlot> getOccupiedSlots() {
        return slots.values().stream()
                .filter(ParkingSlot::isOccupied)
                .collect(Collectors.toList());
    }

    /**
     * Displays the current status of all slots.
     */
    public void displayStatus() {
        System.out.println("\n=== Parking Lot Status ===");
        for (ParkingSlot slot : slots.values()) {
            System.out.println(slot);
        }
        System.out.println("Available: " + getAvailableSlots().size() + " | Occupied: " + getOccupiedSlots().size());
    }

    /**
     * Demonstrates the Parking Lot Management System.
     */
    public static void main(String[] args) {
        ParkingLotManagementSystem parkingLot = new ParkingLotManagementSystem(5);

        System.out.println("=== Parking Vehicles ===");
        Vehicle car1 = new Vehicle("ABC-123", "Aarav Sharma", "CAR");
        Vehicle car2 = new Vehicle("XYZ-789", "Priya Patel", "CAR");
        Vehicle bike1 = new Vehicle("BIKE-01", "Vikram Kumar", "BIKE");
        Vehicle truck1 = new Vehicle("TRK-456", "Diya Singh", "TRUCK");

        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(car2);
        parkingLot.parkVehicle(bike1);
        parkingLot.parkVehicle(truck1);

        parkingLot.displayStatus();

        System.out.println("\n=== Vehicle Lookup ===");
        ParkingSlot found = parkingLot.findVehicle("XYZ-789");
        if (found != null) {
            System.out.println("Vehicle XYZ-789 is at slot " + found.getSlotId());
        }

        System.out.println("\n=== Leaving Vehicle ===");
        parkingLot.leaveVehicle("BIKE-01");
        parkingLot.displayStatus();

        System.out.println("\n=== Parking in Freed Slot ===");
        Vehicle car3 = new Vehicle("NEW-001", "Rohan Reddy", "CAR");
        parkingLot.parkVehicle(car3);

        parkingLot.displayStatus();
    }
}
