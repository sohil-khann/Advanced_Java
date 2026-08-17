@Component
public class UserService {
    @Autowired
    private EmailService emailService;

    public void registerUser(String name) {
        System.out.println("User registered: " + name);
        emailService.sendEmail(name, "Welcome!");
    }
}

@Service
public class EmailService {
    public void sendEmail(String to, String body) {
        System.out.println("Email sent to " + to + ": " + body);
    }
}

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ApplicationContext();
        ctx.scan("scenarios.expert.minispring");

        UserService userService = (UserService) ctx.getBean("userService");
        userService.registerUser("Priya");
    }
}
