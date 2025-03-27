package ru.iukhimenko.demoblaze.ui.base;

import com.github.javafaker.Faker;
import java.util.Date;

public class DataGenerator {
    public static String getUniqueUsername() {
        StringBuilder username = new StringBuilder("user_");
        long uniqueSuffix = new Date().getTime();
        return username.append(uniqueSuffix).toString();
    }

    public static String getValidPassword(int minimalLength ) {
        return new Faker().number().digits(minimalLength);
    }

    public static String getValidPassword() {
        return getValidPassword(6);
    }
}
