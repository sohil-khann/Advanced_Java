public class Grade {
    public enum Letter { A, B, C, D, F }

    public static Letter calculateGrade(double marks, int maxMarks) {
        double percentage = (marks / maxMarks) * 100.0;
        if (percentage >= 90) return Letter.A;
        if (percentage >= 75) return Letter.B;
        if (percentage >= 60) return Letter.C;
        if (percentage >= 40) return Letter.D;
        return Letter.F;
    }

    public static double gradePoints(Letter grade) {
        switch (grade) {
            case A: return 10.0;
            case B: return 8.0;
            case C: return 6.0;
            case D: return 4.0;
            default: return 0.0;
        }
    }
}
