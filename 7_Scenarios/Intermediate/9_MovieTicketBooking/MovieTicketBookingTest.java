import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class MovieTicketBookingTest {
    private Movie movie;
    private Seat seat1;
    private Seat seat2;

    @BeforeEach
    public void setup() {
        movie = new Movie("Test Movie", "2026-08-14 18:00", 10);
        seat1 = movie.getSeats().get(0);
        seat2 = movie.getSeats().get(1);
    }

    @Test
    public void testSeatAllocation() throws SeatUnavailableException {
        movie.bookSeat(seat1);
        assertTrue(seat1.isBooked());
        assertEquals(1, movie.getBookedSeats().size());
        assertEquals(9, movie.getAvailableSeats().size());
    }

    @Test
    public void testDoubleBookingPrevention() {
        try {
            movie.bookSeat(seat1);
            movie.bookSeat(seat1);
            fail("Expected SeatUnavailableException");
        } catch (SeatUnavailableException e) {
            assertTrue(e.getMessage().contains("already booked"));
        }
    }

    @Test
    public void testMultipleSeatBooking() throws SeatUnavailableException {
        movie.bookSeat(seat1);
        movie.bookSeat(seat2);
        assertEquals(2, movie.getBookedSeats().size());
        assertEquals(8, movie.getAvailableSeats().size());
    }

    @Test
    public void testOccupancyRate() throws SeatUnavailableException {
        movie.bookSeat(seat1);
        movie.bookSeat(seat2);
        movie.bookSeat(movie.getSeats().get(2));
        assertEquals(0.3, movie.getOccupancyRate(), 0.01);
    }

    @Test
    public void testBookingStatsByRow() throws SeatUnavailableException {
        movie.bookSeat(seat1);
        movie.bookSeat(seat2);
        Map<String, Long> stats = movie.getBookingStatsByRow();
        assertEquals(2, stats.get("A"));
    }
}
