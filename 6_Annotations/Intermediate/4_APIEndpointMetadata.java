import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ApiEndpoint {
    String method();
    String path();
    String authLevel();
}

class OrderAPI {
    @ApiEndpoint(method = "GET", path = "/orders", authLevel = "PUBLIC")
    public void getOrders() {
        System.out.println("Returning orders");
    }

    @ApiEndpoint(method = "POST", path = "/orders", authLevel = "ADMIN")
    public void createOrder() {
        System.out.println("Order created");
    }
}

public class APIEndpointMetadata {
    public static void main(String[] args) {
        System.out.println("API Documentation:");
        System.out.println("------------------");
        for (Method method : OrderAPI.class.getDeclaredMethods()) {
            ApiEndpoint endpoint = method.getAnnotation(ApiEndpoint.class);
            if (endpoint != null) {
                System.out.println(endpoint.method() + " " + endpoint.path() + " [" + endpoint.authLevel() + "]");
            }
        }
    }
}
