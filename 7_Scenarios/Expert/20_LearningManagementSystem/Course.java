import java.util.*;

public class Course {
    private static int idGen = 1;
    private final int courseId;
    private final String title;
    private final String instructorId;
    private final List<Integer> enrolledStudents;
    private final List<Double> ratings;

    public Course(String title, String instructorId) {
        this.courseId = idGen++;
        this.title = title;
        this.instructorId = instructorId;
        this.enrolledStudents = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public int getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public String getInstructorId() { return instructorId; }
    public List<Integer> getEnrolledStudents() { return enrolledStudents; }
    public List<Double> getRatings() { return ratings; }

    public void enrollStudent(int studentId) { enrolledStudents.add(studentId); }
    public void addRating(double rating) { ratings.add(rating); }
    public double getAverageRating() {
        return ratings.isEmpty() ? 0.0 : ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
