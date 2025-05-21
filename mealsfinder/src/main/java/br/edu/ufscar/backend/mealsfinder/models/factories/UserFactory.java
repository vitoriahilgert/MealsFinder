public class UserFactory {
    // TODO: trocar parametros para DTOs

    public User createClient(UUID id) {
        return Client();
    }

    public User createEstablishment(UUID id) {
        return Establishment();
    }
}