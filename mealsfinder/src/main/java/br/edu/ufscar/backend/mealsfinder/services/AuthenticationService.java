public interface AuthenticationService {
    Client registerClient(UUID id);

    Establishment registerEstablishment(UUID id);
}