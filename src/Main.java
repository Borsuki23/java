import java.util.ArrayList;
import java.util.Scanner;
//ddz 15
class ToDoList {
    private ArrayList<String> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
        System.out.println("Завдання додано: " + task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            System.out.println("Завдання видалено: " + tasks.remove(index));
        } else {
            System.out.println("Невірний індекс!");
        }
    }

    public void moveTask(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && fromIndex < tasks.size() && toIndex >= 0 && toIndex < tasks.size()) {
            String task = tasks.remove(fromIndex);
            tasks.add(toIndex, task);
            System.out.println("Завдання переміщено: " + task);
        } else {
            System.out.println("Невірні індекси!");
        }
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список завдань порожній.");
        } else {
            System.out.println("Ваші завдання:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати завдання");
            System.out.println("2. Видалити завдання");
            System.out.println("3. Показати всі завдання");
            System.out.println("4. Перемістити завдання");
            System.out.println("5. Вийти");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введіть завдання: ");
                    String task = scanner.nextLine();
                    toDoList.addTask(task);
                    break;
                case 2:
                    System.out.print("Введіть номер завдання для видалення: ");
                    int index = scanner.nextInt() - 1;
                    toDoList.removeTask(index);
                    break;
                case 3:
                    toDoList.showTasks();
                    break;
                case 4:
                    System.out.print("Введіть номер завдання, яке хочете перемістити: ");
                    int fromIndex = scanner.nextInt() - 1;
                    System.out.print("Введіть нову позицію для цього завдання: ");
                    int toIndex = scanner.nextInt() - 1;
                    toDoList.moveTask(fromIndex, toIndex);
                    break;
                case 5:
                    System.out.println("Вихід з програми...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }
}