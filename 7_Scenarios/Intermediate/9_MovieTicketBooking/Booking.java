import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Booking {
    private final String bookingId;
    private final Movie movie;
    private final List<Seat> seats;
    private final String customerName;

    public Booking(String bookingId, Movie movie, List<Seat> seats, String customerName) {
        this.bookingId = bookingId;
        this.movie = movie;
        this.seats = new ArrayList<>(seats);
        this.customerName = customerName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount(double pricePerSeat) {
        return seats.size() * pricePerSeat;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", movie=" + movie.getTitle() +
                ", seats=" + seats.stream().map(seat -> seat.getRow() + seat.getSeatNumber()).collect(Collectors.joining(", ")) +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
