public class Seat {
    private final int seatNumber;
    private final String row;
    private boolean booked;

    public Seat(int seatNumber, String row) {
        this.seatNumber = seatNumber;
        this.row = row;
        this.booked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getRow() {
        return row;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book() {
        this.booked = true;
    }

    @Override
    public String toString() {
        return row + seatNumber + (booked ? " [Booked]" : " [Available]");
    }
}
