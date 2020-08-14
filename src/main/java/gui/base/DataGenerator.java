package gui.base;

import com.github.javafaker.Faker;
import java.util.Date;

public class DataGenerator {
    public static String getUniqueUsername() {
        StringBuilder username = new StringBuilder("user_");
        long uniqueSuffix = new Date().getTime();
        return username.append(uniqueSuffix).toString();
    }

    public static String getValidPassword() {
        int minimalLength = 6;
        return new Faker().number().digits(minimalLength);
    }
}
