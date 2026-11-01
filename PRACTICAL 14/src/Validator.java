public class Validator {
    public static boolean validMobile(String mobile) {
        return mobile != null && mobile.matches("[6-9][0-9]{9}");
    }

    public static boolean validEmail(String email) {
        return email != null
                && email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }

    public static void requireValid(boolean condition, String message) {
        if (!condition) {
            throw new BankException(message);
        }
    }

    public static long rupeesToPaise(double rupees) {
        if (!Double.isFinite(rupees) || rupees <= 0) {
            throw new BankException("Amount must be positive");
        }

        return Math.round(rupees * 100);
    }
}