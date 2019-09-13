import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static String calculate(String str) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("^\\s*?(10|\\d)\\s*?([+-/*])\\s*?(10|\\d)\\s*?$|" +
                "^\\s*?(I{1,3}|V|X|VI{1,3}|IV|IX)\\s*?([+-/*])\\s*?(I{1,3}|V|X|VI{1,3}|IV|IX)\\s*?$");
        Matcher matcher = pattern.matcher(str);
        double result = 0;
        boolean isRoman = false;

        if (matcher.find()) {
            int a;
            String operation;
            int b;
            if (matcher.group(1) == null) {
                a = getNumber(matcher.group(4));
                operation = matcher.group(5);
                b = getNumber(matcher.group(6));
                isRoman = true;
            } else {
                a = Integer.parseInt(matcher.group(1));
                operation = matcher.group(2);
                b = Integer.parseInt(matcher.group(3));
            }

            switch (operation) {
                case "+" :
                    result = a + b;
                    break;
                case "-" :
                    result = a - b;
                    break;
                case "*" :
                    result = a * b;
                    break;
                case "/" :
                    if (b == 0) {
                        throw new IllegalArgumentException("Деление на ноль.");
                    }
                    result = (double) a / b;
                    break;
            }
        } else {
            throw new IllegalArgumentException("Не верный формат.");
        }
        if (isRoman) {
            return getRomanResult((int) result);
        }
        return String.valueOf(result);
    }

    public static String getRomanResult(int number) {
        StringBuilder builder = new StringBuilder();

        if (number == 100) {
            builder.append("C");
            number = number - 100;
        }
        if (number >= 90) {
            builder.append("XC");
            number -= 90;
        }
        if (number >= 50) {
            builder.append("L");
            number -= 50;
        }
        if (number >= 40) {
            builder.append("XL");
            number -= 40;
        }
        while (number >= 10) {
            builder.append("X");
            number -= 10;
        }
        if (number == 9) {
            builder.append("IX");
            return builder.toString();
        }
        if (number >= 5) {
            builder.append("V");
            number -= 5;
        }
        if (number == 4) {
            builder.append("IV");
            return builder.toString();
        }
        while (number > 0) {
            builder.append("I");
            number -= 1;
        }

        return builder.toString();
    }

    public static int getNumber(String stringNumber) {
        int result = 0;
        int k = 1;
        for (int i = stringNumber.length() - 1; i >= 0; i--) {
            if (stringNumber.charAt(i) == 'I') {
                result += 1 * k;
            }
            if (stringNumber.charAt(i) == 'X') {
                result += 10;
                k = -1;
            }
            if (stringNumber.charAt(i) == 'V') {
                result += 5;
                k = -1;
            }
        }
        return result;
    }
}
