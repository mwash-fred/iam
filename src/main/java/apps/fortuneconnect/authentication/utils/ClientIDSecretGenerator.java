package apps.fortuneconnect.authentication.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;

public class ClientIDSecretGenerator {
    private static final SecureRandom random = new SecureRandom();
    public static String generateBase36(int length) {
        long currentTimeMillis = Instant.now().toEpochMilli();
        long timeComponent = currentTimeMillis % 1_000_000_000;

        int randomComponentLength = length - Long.toString(timeComponent, 36).length();
        String randomComponent = new BigInteger(randomComponentLength * 5, random).toString(36);

        String base36 = (Long.toString(timeComponent, 36) + randomComponent).substring(0, length);
        return base36.toLowerCase();
    }
}
