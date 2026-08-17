import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ApplicationIntegrationTest {

    @Test
    void contextLoads() {
        assertTrue(true);
    }
}
