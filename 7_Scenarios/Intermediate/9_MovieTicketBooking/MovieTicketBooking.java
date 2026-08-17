import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieTicketBooking {
    private static final double PRICE_PER_SEAT = 15.0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Movie> movies = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();
    private static int bookingCounter = 1;

    public static void main(String[] args) {
        movies.add(new Movie("Dangal", "2026-08-14 18:00", 30));
        movies.add(new Movie("3 Idiots", "2026-08-14 21:00", 30));

        while (true) {
            System.out.println("\n=== Movie Ticket Booking System ===");
            System.out.println("1. View Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Booking Reports");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewMovies();
                    break;
                case "2":
                    bookTicket();
                    break;
                case "3":
                    viewReports();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void viewMovies() {
        System.out.println("\n--- Available Movies ---");
        for (Movie movie : movies) {
            System.out.println(movie.getTitle() + " | Show Time: " + movie.getShowTime());
            System.out.println("Available Seats: " + movie.getAvailableSeats().size() + "/" + movie.getSeats().size());
            System.out.println("Occupancy: " + String.format("%.2f", movie.getOccupancyRate() * 100) + "%");
        }
    }

    private static void bookTicket() {
        System.out.println("\n--- Book Ticket ---");
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        Movie selectedMovie = movies.stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (selectedMovie == null) {
            System.out.println("Movie not found!");
            return;
        }

        System.out.println("Available seats:");
        selectedMovie.getAvailableSeats().forEach(seat ->
                System.out.println(seat.getRow() + seat.getSeatNumber())
        );

        System.out.print("Enter seat numbers to book (comma separated, e.g., A1,A2): ");
        String input = scanner.nextLine();
        List<Seat> selectedSeats = new ArrayList<>();

        for (String seatStr : input.split(",")) {
            seatStr = seatStr.trim().toUpperCase();
            if (seatStr.isEmpty()) continue;

            char row = seatStr.charAt(0);
            int seatNumber = Integer.parseInt(seatStr.substring(1));

            Seat seat = selectedMovie.getSeats().stream()
                    .filter(s -> s.getRow().equals(String.valueOf(row)) && s.getSeatNumber() == seatNumber)
                    .findFirst()
                    .orElse(null);

            if (seat == null) {
                System.out.println("Seat " + seatStr + " does not exist!");
                return;
            }
            selectedSeats.add(seat);
        }

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        try {
            for (Seat seat : selectedSeats) {
                selectedMovie.bookSeat(seat);
            }
            Booking booking = new Booking("BK" + bookingCounter++, selectedMovie, selectedSeats, customerName);
            bookings.add(booking);
            System.out.println("Booking successful! " + booking);
            System.out.println("Total Amount: ₹" + booking.getTotalAmount(PRICE_PER_SEAT));
        } catch (SeatUnavailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static void viewReports() {
        System.out.println("\n--- Booking Reports ---");

        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        System.out.println("Total Bookings: " + bookings.size());
        System.out.println("Total Seats Booked: " +
                bookings.stream().mapToInt(b -> b.getSeats().size()).sum()
        );
        System.out.println("Total Revenue: ₹" +
                String.format("%.2f", bookings.stream().mapToDouble(b -> b.getTotalAmount(PRICE_PER_SEAT)).sum())
        );

        System.out.println("\nMovie-wise Bookings:");
        movies.stream()
                .filter(movie -> movie.getBookedSeats().size() > 0)
                .forEach(movie -> {
                    System.out.println(movie.getTitle() + ": " + movie.getBookedSeats().size() + " seats booked");
                    System.out.println("  Row-wise stats: " + movie.getBookingStatsByRow());
                });
    }
}




