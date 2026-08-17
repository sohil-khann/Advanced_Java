import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AirlineReservationSystem {
    private static final List<Flight> flights = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Flight f1 = new Flight("AI101", "Delhi", "Mumbai", LocalDateTime.now().plusDays(2), 2, 5000);
        Flight f2 = new Flight("AI102", "Mumbai", "Bangalore", LocalDateTime.now().plusDays(3), 1, 4000);
        flights.addAll(Arrays.asList(f1, f2));

        Passenger p1 = new Passenger("Priya", "priya@example.com", "9876543210");
        Passenger p2 = new Passenger("Rahul", "rahul@example.com", "9876543211");
        Passenger p3 = new Passenger("Arjun", "arjun@example.com", "9876543212");

        try {
            bookFlight(p1, f1);
            bookFlight(p2, f1);
            bookFlight(p3, f1);
            bookFlight(p1, f2);
        } catch (Exception e) {
            System.out.println("Booking error: " + e.getMessage());
        }

        generateOccupancyReport();
        System.out.println("Top customers: " + getTopCustomers(2));
    }

    public static void bookFlight(Passenger passenger, Flight flight) throws Exception {
        if (flight.getAvailableSeats() > 0) {
            Booking booking = new Booking(passenger, flight, Booking.Status.CONFIRMED);
            flight.addBooking(booking);
            bookings.add(booking);
            System.out.println("Booking confirmed for " + passenger.getName() + " on " + flight.getFlightNumber());
        } else {
            Booking waitlist = new Booking(passenger, flight, Booking.Status.WAITLISTED);
            flight.addBooking(waitlist);
            bookings.add(waitlist);
            System.out.println("Flight full. Added " + passenger.getName() + " to waitlist for " + flight.getFlightNumber());
        }
    }

    public static void generateOccupancyReport() {
        System.out.println("\n=== Occupancy Report ===");
        flights.stream().forEach(f -> {
            System.out.printf("Flight %s: %.1f%% occupied (%d/%d seats)%n",
                f.getFlightNumber(), f.getOccupancyRate(),
                f.getBookings().stream().filter(Booking::isConfirmed).count(),
                f.getTotalSeats());
        });
    }

    public static List<Map.Entry<String, Long>> getTopCustomers(int n) {
        return bookings.stream()
            .filter(Booking::isConfirmed)
            .collect(Collectors.groupingBy(b -> b.getPassenger().getName(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(n)
            .collect(Collectors.toList());
    }
}
