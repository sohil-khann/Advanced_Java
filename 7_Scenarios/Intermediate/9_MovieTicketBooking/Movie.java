import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Movie {
    private final String title;
    private final String showTime;
    private final List<Seat> seats;

    public Movie(String title, String showTime, int totalSeats) {
        this.title = title;
        this.showTime = showTime;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            char row = (char) ('A' + (i - 1) / 10);
            int seatNum = (i - 1) % 10 + 1;
            seats.add(new Seat(seatNum, String.valueOf(row)));
        }
    }

    public String getTitle() {
        return title;
    }

    public String getShowTime() {
        return showTime;
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    public synchronized void bookSeat(Seat seat) throws SeatUnavailableException {
        if (seat.isBooked()) {
            throw new SeatUnavailableException("Seat " + seat.getRow() + seat.getSeatNumber() + " is already booked.");
        }
        seat.book();
    }

    public List<Seat> getAvailableSeats() {
        return seats.stream()
                .filter(seat -> !seat.isBooked())
                .collect(Collectors.toList());
    }

    public List<Seat> getBookedSeats() {
        return seats.stream()
                .filter(Seat::isBooked)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getBookingStatsByRow() {
        return seats.stream()
                .collect(Collectors.groupingBy(Seat::getRow, Collectors.summingLong(seat -> seat.isBooked() ? 1L : 0L)));
    }

    public double getOccupancyRate() {
        return seats.stream()
                .mapToDouble(seat -> seat.isBooked() ? 1.0 : 0.0)
                .average()
                .orElse(0.0);
    }
}
