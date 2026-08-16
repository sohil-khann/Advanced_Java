import java.util.ArrayList;
import java.util.List;

class SeatNotAvailableException extends Exception {
    public SeatNotAvailableException(String message) {
        super(message);
    }
}

class InvalidPassengerDetailsException extends Exception {
    public InvalidPassengerDetailsException(String message) {
        super(message);
    }
}

class FlightFullException extends Exception {
    public FlightFullException(String message) {
        super(message);
    }
}

class Passenger {
    String name;
    String passport;
    int age;

    public Passenger(String name, String passport, int age) {
        this.name = name;
        this.passport = passport;
        this.age = age;
    }
}

class Flight {
    String flightNumber;
    int totalSeats;
    List<Passenger> bookedPassengers;

    public Flight(String flightNumber, int totalSeats) {
        this.flightNumber = flightNumber;
        this.totalSeats = totalSeats;
        this.bookedPassengers = new ArrayList<>();
    }

    public void bookPassenger(Passenger p) throws SeatNotAvailableException, InvalidPassengerDetailsException, FlightFullException {
        if (p.name == null || p.name.trim().isEmpty() || p.passport == null || p.passport.trim().isEmpty() || p.age <= 0) {
            throw new InvalidPassengerDetailsException("Invalid passenger details");
        }
        if (bookedPassengers.size() >= totalSeats) {
            throw new FlightFullException("Flight " + flightNumber + " is full");
        }
        for (Passenger existing : bookedPassengers) {
            if (existing.passport.equals(p.passport)) {
                throw new SeatNotAvailableException("Passenger with passport " + p.passport + " already booked");
            }
        }
        bookedPassengers.add(p);
    }
}

class FlightBookingSystem {
    public static void main(String[] args) {
        Flight flight = new Flight("AI-101", 2);
        Passenger[] passengers = {
            new Passenger("Priya", "P1001", 30),
            new Passenger("Rahul", "P1002", 25),
            new Passenger("Arjun", "P1003", 40),
            new Passenger("", "P1004", 35),
            new Passenger("Anjali", "P1002", 28)
        };

        for (Passenger p : passengers) {
            try {
                flight.bookPassenger(p);
                System.out.println("Booked: " + p.name);
            } catch (InvalidPassengerDetailsException | FlightFullException | SeatNotAvailableException e) {
                System.out.println("Booking failed for " + p.name + ": " + e.getMessage());
            }
        }
    }
}

