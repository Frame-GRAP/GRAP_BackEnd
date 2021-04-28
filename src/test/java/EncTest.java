public class EncTest {

    public void hash_enc() {
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234 해쉬: " + encPassword);

    }
}
