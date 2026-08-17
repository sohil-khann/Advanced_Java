public class Subject {
    private static int idGen = 1;
    private final int subjectId;
    private final String name;
    private final int maxMarks;
    private double marks;

    public Subject(String name, int maxMarks) {
        this.subjectId = idGen++;
        this.name = name;
        this.maxMarks = maxMarks;
        this.marks = 0;
    }

    public int getSubjectId() { return subjectId; }
    public String getName() { return name; }
    public int getMaxMarks() { return maxMarks; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }
}
