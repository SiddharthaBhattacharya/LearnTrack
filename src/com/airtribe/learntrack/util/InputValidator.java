package com.airtribe.learntrack.util;

import java.util.Scanner;

public class InputValidator {
    private InputValidator() {
    }

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            int number = readInt(scanner, prompt);
            if (number > 0) {
                return number;
            }
            System.out.println("Please enter a number greater than zero.");
        }
    }

    public static String readRequiredText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    public static String readOptionalText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static String readEmail(Scanner scanner, String prompt) {
        while (true) {
            String email = readOptionalText(scanner, prompt);
            if (email.isEmpty() || isValidEmail(email)) {
                return email;
            }
            System.out.println("Please enter a valid email address, or leave it blank.");
        }
    }

    public static boolean isValidEmail(String email) {
        int atPosition = email.indexOf('@');
        int dotPosition = email.lastIndexOf('.');
        return atPosition > 0 && dotPosition > atPosition + 1
                && dotPosition < email.length() - 1;
    }
}
