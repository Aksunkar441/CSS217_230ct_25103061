record UserProfile(String studentId, String email, int accessLevel) {}

interface UserRegistry {
    UserProfile fetchUser();
}

interface LegacyUserFeed {
    String getRawRecord();
}

public class UserRegistryAdapter implements UserRegistry {
    private final LegacyUserFeed legacyFeed;

    public UserRegistryAdapter(LegacyUserFeed legacyFeed) {
        this.legacyFeed = legacyFeed;
    }

    @Override
    public UserProfile fetchUser() {
        String raw = legacyFeed.getRawRecord();
        if (raw == null || raw.trim().isEmpty()) {
            throw new IllegalArgumentException("Raw record is empty");
        }

        String[] parts = raw.split("#");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format: expected 3 parts separated by '#'");
        }

        try {
            int accessLevel = Integer.parseInt(parts[2]);
            return new UserProfile(parts[0], parts[1], accessLevel);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid access level format");
        }
    }

    public static void main(String[] args) {
        String studentId = "25103061";
        System.out.println("Student ID: " + studentId);

        LegacyUserFeed feed = () -> studentId + "#user@college.edu#4";
        UserRegistryAdapter adapter = new UserRegistryAdapter(feed);

        UserProfile profile = adapter.fetchUser();
        System.out.println("Parsed UserProfile: " + profile);
    }
}
