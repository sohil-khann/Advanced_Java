class LibraryCatalog {
    private static LibraryCatalog instance;
    private LibraryCatalog() { } // private constructor
    public static  LibraryCatalog getInstance() {
        if (instance == null) {
            instance = new LibraryCatalog();
        }
        return instance;
    }
}