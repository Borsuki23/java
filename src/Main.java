import java.util.ArrayList;

class Dish {
    private String name;
    private double price;
    private String category;

    public Dish(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void displayInfo() {
        System.out.println("Назва: " + name + ", Ціна: " + price + " грн, Категорія: " + category);
    }
}

// Клас, що управляє ресторанним меню
class Restaurant {
    private static ArrayList<Dish> menu = new ArrayList<>();

    public static void addDish(Dish dish) {
        menu.add(dish);
    }

    public static int getTotalDishes() {
        return menu.size();
    }

    public static void displayMenu() {
        for (Dish dish : menu) {
            dish.displayInfo();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Створення об'єктів страв
        Dish soup = new Dish("Суп", 120.50, "Гаряча страва");
        Dish pizza = new Dish("Піца", 250.00, "Основна страва");
        Dish iceCream = new Dish("Морозиво", 90.75, "Десерт");


        Restaurant.addDish(soup);
        Restaurant.addDish(pizza);
        Restaurant.addDish(iceCream);

        System.out.println("Меню ресторану:");
        Restaurant.displayMenu();


        System.out.println("Загальна кількість страв у меню: " + Restaurant.getTotalDishes());
    }
}
