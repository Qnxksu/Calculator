import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;


public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Я калькулятор!");
        boolean continueWork = true;
        while (continueWork) {
            try {
                System.out.println("\n Выберите операцию");
                System.out.println("1 - Сложить");
                System.out.println("2 - Вычесть");
                System.out.println("3 - Умножить");
                System.out.println("4 - Разделить");
                System.out.println("Ваш выбор:");

                int choice = scanner.nextInt();
                System.out.println("Введите первое число: ");
                double number1 = scanner.nextDouble();
                System.out.println(("Введите второе число: "));
                double number2 = scanner.nextDouble();
                double result = calculate(choice, number1, number2);
                System.out.println("Результат: " + result);
            } catch (CalculatorExeption e) {
                System.out.println("Ошибка калькулятора: " + e.getMessage());
                writeErrorToFile(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: необходимо вводить только числа!");
                writeErrorToFile("Ошибка ввода: " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Произошла неожиданная ошибка: " + e.getMessage());
                writeErrorToFile("Неожиданная ошибка " + e.getMessage());
            }
            System.out.println("\n Хотите продолжить? (Да - 1 или Нет - 0)");
            try {
                int continueChoice = scanner.nextInt();
                if (continueChoice == 0) {
                    continueWork = false;
                    System.out.println("Выход из прошораммы.Досвидания!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: нужно ввести 1 или 0");
                writeErrorToFile("Ошибка ввода при выборе продолжения: " + e.getMessage());
                continueWork = false;
            }
        }
        scanner.close();
    }

            public static double calculate(int choice, double number1, double number2) throws CalculatorExeption {
                switch (choice){
                    case 1:
                        return number1 + number2;
                    case 2:
                        return number1 - number2;
                    case 3:
                        return number1 * number2;
                    case  4:
                        if (number2 == 0){
                            throw new CalculatorExeption("Ошибка! Делить на 0 НЕЛЬЗЯ");
                        }
                        return number1 / number2;
                    default:
                        throw new CalculatorExeption("Ошибка! Неверный номер операции");
                }

            }
            public static void writeErrorToFile (String errorMessage) {
        try (FileWriter writer = new FileWriter("error.log", true)) {
            writer.write(errorMessage + "\n");
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " +e.getMessage());
        }
    }
}
