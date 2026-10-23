import java.io.IOException;
import java.sql.SQLException;

class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

interface ConfigService {
    String readKey(String key);
}

class FlakyConfig {
    public String getRaw(String k) throws IOException, SQLException {
        if ("db.url".equals(k)) {
            return "jdbc:postgresql://localhost:5432/mydb";
        } else if ("sql.error".equals(k)) {
            throw new SQLException("Simulated database connection failure");
        } else {
            throw new IOException("Simulated file read error");
        }
    }
}

public class SafeConfigAdapter implements ConfigService {
    private final FlakyConfig flakyConfig;

    public SafeConfigAdapter() {
        this.flakyConfig = new FlakyConfig();
    }

    @Override
    public String readKey(String key) {
        try {
            return flakyConfig.getRaw(key);
        } catch (IOException | SQLException e) {
            throw new ConfigNotFoundException("Failed to read config key: " + key, e);
        }
    }

    public static void main(String[] args) {
        String studentId = "25103061";
        System.out.println("Student ID: " + studentId);

        ConfigService service = new SafeConfigAdapter();

        System.out.println("\n--- Testing Valid Key ---");
        System.out.println("Success: " + service.readKey("db.url"));

        System.out.println("\n--- Testing Failing Key ---");
        try {
            service.readKey("sql.error");
        } catch (ConfigNotFoundException e) {
            System.out.println("Caught ConfigNotFoundException: " + e.getMessage());
            System.out.println("Underlying cause name: " + e.getCause().getClass().getSimpleName());
        }
    }
}
