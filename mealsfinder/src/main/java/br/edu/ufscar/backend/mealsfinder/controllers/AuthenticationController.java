
public class AuthenticationController {
    // TODO: implementar injecão de dependencia
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }
}