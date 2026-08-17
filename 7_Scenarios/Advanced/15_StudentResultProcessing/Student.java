public class Student {
    private static int idGen = 1;
    private final int studentId;
    private final String name;
    private final String department;
    private final List<Subject> subjects;
    private boolean isAbsent;

    public Student(String name, String department) {
        this.studentId = idGen++;
        this.name = name;
        this.department = department;
        this.subjects = new ArrayList<>();
        this.isAbsent = false;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<Subject> getSubjects() { return subjects; }
    public boolean isAbsent() { return isAbsent; }
    public void setAbsent(boolean absent) { isAbsent = absent; }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public double getAverageMarks() {
        if (subjects.isEmpty()) return 0.0;
        return subjects.stream().mapToDouble(Subject::getMarks).average().orElse(0.0);
    }
}
