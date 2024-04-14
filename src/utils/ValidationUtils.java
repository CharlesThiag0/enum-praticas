package utils;

import java.util.Scanner;

public class ValidationUtils {
    private final static String MSG_INVALID = "Please enter a valid value.";

    public double validValue(Scanner sc, double value) {
        while (value < 0) {
            System.out.println(MSG_INVALID);
            value = sc.nextDouble();
        }
        return value;
    }

    public int validValue(Scanner sc, int value) {
        while (value < 0) {
            System.out.println(MSG_INVALID);
            value = sc.nextInt();
        }
        return value;
    }
}
