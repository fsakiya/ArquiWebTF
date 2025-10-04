import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Hash de admin123: " + encodedPassword);
        
        // Verificar si el hash existente es correcto
        String existingHash = "$2a$12$NlhEr1LUMp6C7n6c9SqJQeTKeL7dSrGRVvuOk1clzrbmJkcvLClPa";
        boolean matches = encoder.matches(rawPassword, existingHash);
        System.out.println("¿El hash existente coincide con admin123? " + matches);
    }
}
