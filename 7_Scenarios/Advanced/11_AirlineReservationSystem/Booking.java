import java.time.LocalDateTime;

public class Booking {
    public enum Status { CONFIRMED, WAITLISTED, CANCELLED }

    private static int idGen = 1;
    private final int bookingId;
    private final Passenger passenger;
    private final Flight flight;
    private final LocalDateTime bookingTime;
    private Status status;

    public Booking(Passenger passenger, Flight flight, Status status) {
        this.bookingId = idGen++;
        this.passenger = passenger;
        this.flight = flight;
        this.bookingTime = LocalDateTime.now();
        this.status = status;
    }

    public int getBookingId() { return bookingId; }
    public Passenger getPassenger() { return passenger; }
    public Flight getFlight() { return flight; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public Status getStatus() { return status; }
    public boolean isConfirmed() { return status == Status.CONFIRMED; }
    public void setStatus(Status status) { this.status = status; }
}
