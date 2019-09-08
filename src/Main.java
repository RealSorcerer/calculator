import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите строку для расчера, например a + b, a / b и т. д.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String str = reader.readLine();
            System.out.println("Результат: " + Calculator.calculate(str));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
