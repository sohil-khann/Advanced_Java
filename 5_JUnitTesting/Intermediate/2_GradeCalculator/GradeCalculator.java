public class GradeCalculator {

    public double calculatePercentage(Student student) {
        int total = student.getSubject1() + student.getSubject2() + student.getSubject3();
        return total / 3.0;
    }

    public String calculateGrade(Student student) {
        double percentage = calculatePercentage(student);
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public boolean isPassed(Student student) {
        return calculatePercentage(student) >= 40;
    }
}
