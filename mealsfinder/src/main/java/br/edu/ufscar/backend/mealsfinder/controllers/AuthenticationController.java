import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationController {
    // TODO: implementar injecão de dependencia
    @Autowired
    private AuthenticationServiceImpl authenticationService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }
}