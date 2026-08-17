import java.util.*;

public class Student {
    private static int idGen = 1;
    private final int studentId;
    private final String name;
    private final String email;
    private final List<Integer> enrolledCourses;
    private final Map<Integer, Double> courseRatings;

    public Student(String name, String email) {
        this.studentId = idGen++;
        this.name = name;
        this.email = email;
        this.enrolledCourses = new ArrayList<>();
        this.courseRatings = new HashMap<>();
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Integer> getEnrolledCourses() { return enrolledCourses; }

    public void enroll(int courseId) { enrolledCourses.add(courseId); }
    public void rateCourse(int courseId, double rating) { courseRatings.put(courseId, rating); }
}
