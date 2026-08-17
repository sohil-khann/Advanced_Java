import java.util.*;

public class Instructor {
    private static int idGen = 1;
    private final int instructorId;
    private final String name;
    private final String expertise;
    private final List<Integer> courses;

    public Instructor(String name, String expertise) {
        this.instructorId = idGen++;
        this.name = name;
        this.expertise = expertise;
        this.courses = new ArrayList<>();
    }

    public int getInstructorId() { return instructorId; }
    public String getName() { return name; }
    public String getExpertise() { return expertise; }
    public List<Integer> getCourses() { return courses; }

    public void assignCourse(int courseId) { courses.add(courseId); }
}
