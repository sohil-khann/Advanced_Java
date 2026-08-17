import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Flight {
    private static final AtomicInteger ID_GEN = new AtomicInteger(1);
    private final int flightId;
    private final String flightNumber;
    private final String source;
    private final String destination;
    private final LocalDateTime departureTime;
    private final int totalSeats;
    private final int price;
    private final List<Booking> bookings;

    public Flight(String flightNumber, String source, String destination, LocalDateTime departureTime, int totalSeats, int price) {
        this.flightId = ID_GEN.getAndIncrement();
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.price = price;
        this.bookings = new ArrayList<>();
    }

    public int getFlightId() { return flightId; }
    public String getFlightNumber() { return flightNumber; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public int getTotalSeats() { return totalSeats; }
    public int getPrice() { return price; }
    public List<Booking> getBookings() { return bookings; }

    public int getAvailableSeats() {
        return totalSeats - (int) bookings.stream().filter(Booking::isConfirmed).count();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public double getOccupancyRate() {
        if (totalSeats == 0) return 0.0;
        return (bookings.stream().filter(Booking::isConfirmed).count() * 100.0) / totalSeats;
    }
}
