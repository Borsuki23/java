import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Додати числа");
            System.out.println("2. Обробити числа");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.println("Введіть числа через пробіл:");
                String[] input = scanner.nextLine().split(" ");
                for (String s : input) {
                    try {
                        numbers.add(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        System.out.println("Невірне число: " + s);
                    }
                }
            } else if (choice == 2) {
                if (numbers.isEmpty()) {
                    System.out.println("Список порожній.");
                    continue;
                }

                int evenSum = 0;
                for (int n : numbers) {
                    if (n % 2 == 0) evenSum += n;
                }
                System.out.println("Сума парних чисел: " + evenSum);

                System.out.print("Числа * 2: ");
                for (int n : numbers) {
                    System.out.print(n * 2 + " ");
                }
                System.out.println();

                int max = numbers.get(0);
                for (int n : numbers) {
                    if (n > max) max = n;
                }
                System.out.println("Максимальне число: " + max);

                System.out.print("Непарні числа: ");
                boolean first = true;
                for (int n : numbers) {
                    if (n % 2 != 0) {
                        if (!first) System.out.print(", ");
                        System.out.print(n);
                        first = false;
                    }
                }
                System.out.println();

                int sum = 0;
                for (int n : numbers) sum += n;
                double avg = (double) sum / numbers.size();
                System.out.println("Середнє арифметичне: " + avg);

            } else if (choice == 3) {
                System.out.println("До побачення!");
                break;
            } else {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
