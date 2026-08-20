import java.util.*;

interface Observer {
    void update(String message);
}
class UserObserver implements Observer {
    private String name;
    public UserObserver(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " notified: " + message);
    }
}
class BookCatalog {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void newBookArrived(String bookName) {
        notifyAllObservers("New book available: " + bookName);
    }
    private void notifyAllObservers(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}
