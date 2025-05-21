
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public Client registerClient(UUID id) {
        return UserFactory.createClient(id);
    }

    @Override
    public Establishment registerEstablishment(UUID id) {
        return UserFactory.createEstablishment(id);
    }
}