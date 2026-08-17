import java.util.*;
import java.util.stream.Collectors;

public class SmartParkingSystem {
    private static final List<ParkingSlot> slots = new ArrayList<>();
    private static final List<Vehicle> vehicles = new ArrayList<>();
    private static final Map<Integer, ParkingSlot> occupiedByVehicle = new HashMap<>();

    public static void main(String[] args) {
        slots.addAll(Arrays.asList(
            new ParkingSlot(ParkingSlot.Type.CAR),
            new ParkingSlot(ParkingSlot.Type.CAR),
            new ParkingSlot(ParkingSlot.Type.BIKE),
            new ParkingSlot(ParkingSlot.Type.TRUCK)
        ));

        Vehicle v1 = new Vehicle("KA01AB1234", Vehicle.Type.CAR, "Priya");
        Vehicle v2 = new Vehicle("KA02CD5678", Vehicle.Type.BIKE, "Rahul");
        Vehicle v3 = new Vehicle("DL03EF9012", Vehicle.Type.CAR, "Arjun");

        vehicles.addAll(Arrays.asList(v1, v2, v3));

        allocateSlot(v1);
        allocateSlot(v2);
        allocateSlot(v3);

        generateOccupancyReport();
        System.out.println("Available car slots: " + getAvailableSlots(ParkingSlot.Type.CAR));
    }

    public static void allocateSlot(Vehicle vehicle) {
        if (!Vehicle.isValidPlate(vehicle.getNumberPlate())) {
            System.out.println("Invalid plate: " + vehicle.getNumberPlate());
            return;
        }
        if (occupiedByVehicle.containsKey(vehicle.getVehicleId())) {
            System.out.println("Vehicle already parked: " + vehicle.getNumberPlate());
            return;
        }

        ParkingSlot slot = slots.stream()
            .filter(s -> s.getType() == ParkingSlot.Type.CAR && s.getStatus() == ParkingSlot.Status.AVAILABLE)
            .findFirst().orElse(null);

        if (slot != null) {
            slot.setStatus(ParkingSlot.Status.OCCUPIED);
            slot.setVehicle(vehicle);
            occupiedByVehicle.put(vehicle.getVehicleId(), slot);
            System.out.println("Allocated slot " + slot.getSlotId() + " to " + vehicle.getNumberPlate());
        } else {
            System.out.println("No available slot for " + vehicle.getNumberPlate());
        }
    }

    public static void releaseSlot(Vehicle vehicle) {
        ParkingSlot slot = occupiedByVehicle.remove(vehicle.getVehicleId());
        if (slot != null) {
            slot.setStatus(ParkingSlot.Status.AVAILABLE);
            slot.setVehicle(null);
            System.out.println("Released slot " + slot.getSlotId() + " for " + vehicle.getNumberPlate());
        }
    }

    public static long getAvailableSlots(ParkingSlot.Type type) {
        return slots.stream().filter(s -> s.getType() == type && s.getStatus() == ParkingSlot.Status.AVAILABLE).count();
    }

    public static void generateOccupancyReport() {
        System.out.println("\n=== Parking Occupancy Report ===");
        Map<ParkingSlot.Type, Long> occupancy = slots.stream()
            .collect(Collectors.groupingBy(ParkingSlot::getType, Collectors.counting()));
        Map<ParkingSlot.Type, Long> available = slots.stream()
            .filter(s -> s.getStatus() == ParkingSlot.Status.AVAILABLE)
            .collect(Collectors.groupingBy(ParkingSlot::getType, Collectors.counting()));
        occupancy.forEach((type, total) -> {
            long avail = available.getOrDefault(type, 0L);
            System.out.println(type + ": " + (total - avail) + "/" + total + " occupied");
        });
    }
}
