import java.util.Arrays;

public class Practice extends Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Practice obj = new Practice();
        obj.main();

            // Access the single instance of LibraryCatalog
            LibraryCatalog catalog = LibraryCatalog.getInstance();

            // Now you can use 'catalog' to call methods or work with the catalog
            System.out.println("Got the LibraryCatalog instance: " + catalog);

UserFactory userFactory = new UserFactory();
userFactory.createUser("student").showRole();

////////////////////////////////////////////////////////////////////////


            // Create the subject (BookCatalog)
            BookCatalog bookCatalog = new BookCatalog();

            // Create observers (users who want notifications)
            UserObserver user1 = new UserObserver("Sohil");
            UserObserver user2 = new UserObserver("Raj");

            // Register observers with the catalog
            bookCatalog.addObserver(user1);
            bookCatalog.addObserver(user2);

            // When a new book arrives, notify all observers
            bookCatalog.newBookArrived("Design Patterns in Java");

            // Remove one observer
            bookCatalog.removeObserver(user1);
            // Notify again (only Bob will get this update)
            bookCatalog.newBookArrived("Clean Code");



///////////////////////////////////////////////////////////////////////////////////////

        Book book1 = new Book.BookBuilder("Effective Java")
                .author("Joshua Bloch")
                .edition("3rd Edition")
                .genre("Programming")
                .build();

        Book book2 = new Book.BookBuilder("Clean Code")
                .author("Robert C. Martin")
                .genre("Software Engineering")
                .build();

        System.out.println("Book 1 created: " + book1);
        System.out.println("Book 2 created: " + book2);



//    @Override
//    void main() {
//        super.main();
//    }
}
}

//
//Categories of Patterns (GoF classification):
//        1. Creational Patterns – deal with object creation (e.g., Singleton, Factory,
//                                                            Builder).
//        2. Structural Patterns – deal with object composition (e.g., Adapter, Composite,
//                                                               Decorator).
//        3. Behavioral Patterns – deal with communication/interaction (e.g., Observer,
//                                                                      Strategy, Command).