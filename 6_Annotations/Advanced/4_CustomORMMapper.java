import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Entity {
    String tableName();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Id {
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Column {
    String name();
    String type() default "VARCHAR(255)";
}

@Entity(tableName = "users")
class User {
    @Id
    @Column(name = "id", type = "INTEGER PRIMARY KEY")
    private int id;

    @Column(name = "username", type = "VARCHAR(50)")
    private String username;

    @Column(name = "email", type = "VARCHAR(100)")
    private String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}

class ORMMapper {
    public static String generateCreateTable(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " is not annotated with @Entity");
        }
        Entity entity = clazz.getAnnotation(Entity.class);
        String tableName = entity.tableName();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tableName).append(" (\n");
        List<String> columns = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                String colDef = "  " + col.name() + " " + col.type();
                columns.add(colDef);
            }
        }
        sb.append(String.join(",\n", columns));
        sb.append("\n);");
        return sb.toString();
    }
}

public class CustomORMMapper {
    public static void main(String[] args) {
        String sql = ORMMapper.generateCreateTable(User.class);
        System.out.println(sql);
    }
}
