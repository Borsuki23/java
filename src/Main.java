public class Main {

    // Власний виняток, якщо число не парне
    static class NotEvenNumberException extends Exception {
        public NotEvenNumberException(String message) {
            super(message);
        }
    }

    // Перевірка, що всі числа парні
    public static void checkEvenNumbers(int[] numbers) throws NotEvenNumberException {
        for (int number : numbers) {
            if (number % 2 != 0) {
                throw new NotEvenNumberException("Знайдено непарне число: " + number);
            }
        }
    }

    // Розрахунок середнього значення
    public static double calculateAverage(int[] numbers) {
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return (double) sum / numbers.length;
    }

    // Друк масиву
    public static void printArray(int[] array) {
        System.out.print("Масив: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data1 = {2, 4, 6, 8};
        int[] data2 = {1, 3, 5};

        try {
            printArray(data1);
            checkEvenNumbers(data1);
            System.out.println("Середнє значення: " + calculateAverage(data1));
        } catch (NotEvenNumberException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        System.out.println();

        try {
            printArray(data2);
            checkEvenNumbers(data2);
            System.out.println("Середнє значення: " + calculateAverage(data2));
        } catch (NotEvenNumberException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
