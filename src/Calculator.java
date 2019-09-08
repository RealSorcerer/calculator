import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public final static HashMap<String, Integer> ROMAN_NUMERALS;
    static {
        ROMAN_NUMERALS = new HashMap<>();
        ROMAN_NUMERALS.put("I", 1);
        ROMAN_NUMERALS.put("II", 2);
        ROMAN_NUMERALS.put("III", 3);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("VI", 6);
        ROMAN_NUMERALS.put("VII", 7);
        ROMAN_NUMERALS.put("VIII", 8);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("X", 10);
    }

    public static Number calculate(String str) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("^\\s*?(10|\\d)\\s*?([+-/*])\\s*?(10|\\d)\\s*?$|" +
                "^\\s*?(I{1,3}|V|X|VI{1,3}|IV|IX)\\s*?([+-/*])\\s*?(I{1,3}|V|X|VI{1,3}|IV|IX)\\s*?$");
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            Integer a;
            String operation;
            Integer b;
            if (matcher.group(1) == null) {
                a = ROMAN_NUMERALS.get(matcher.group(4));
                operation = matcher.group(5);
                b = ROMAN_NUMERALS.get(matcher.group(6));
            } else {
                a = Integer.parseInt(matcher.group(1));
                operation = matcher.group(2);
                b = Integer.parseInt(matcher.group(3));
            }

            switch (operation) {
                case "+" :
                    return a + b;
                case "-" :
                    return a - b;
                case "*" :
                    return a * b;
                case "/" :
                    if (b == 0) {
                        throw new IllegalArgumentException("Деление на ноль.");
                    }
                    return (double) a / b;
                default: return 0;
            }
        } else {
            throw new IllegalArgumentException("Не верный формат.");
        }
    }
}
