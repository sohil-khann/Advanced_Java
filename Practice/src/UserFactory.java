interface User {
    void showRole();
}
class Student implements User {
    public void showRole() { System.out.println("I am a Student"); }
}
class Faculty implements User {
    public void showRole() { System.out.println("I am a Faculty"); }
}
class Librarian implements User {

    public void showRole() { System.out.println("I am a Librarian"); }
}
class UserFactory {
    public static User createUser(String type) {
        switch(type.toLowerCase()) {
            case "student": return new Student();
            case "faculty": return new Faculty();
            case "librarian": return new Librarian();
            default: throw new IllegalArgumentException("Unknown user type");
        }
    }
}
