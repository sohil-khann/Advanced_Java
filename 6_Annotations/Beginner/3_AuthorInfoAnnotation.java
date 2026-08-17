import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
    String date();
}

@Author(name = "Priya Sharma", date = "2026-08-14")
class DocumentManager {
    public void createDocument(String title) {
        System.out.println("Document created: " + title);
    }
}

public class _3_AuthorInfoAnnotation {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = DocumentManager.class;

        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.println("Author Name : " + author.name());
            System.out.println("Author Date : " + author.date());
        } else {
            System.out.println("No @Author annotation found.");
        }
    }
}

