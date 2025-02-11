public class Main {
    public static void main(String[] args) {

        System.out.println("=== Магазин книг ===");

        Book book1 = new Book("Біба", "біба бобович ", 300);
        Book book2 = new Book("боба", "Боба бабкович", 250);
        Book book3 = new Book("абоба", "абоба абобович ", 200);

        // Знижка
        book3.price = book3.price - (book3.price * 10 / 100); // Знижка 10%

        // Інфа про книги
        System.out.println(book1.title + " — " + book1.author + " — " + book1.price + " грн");
        System.out.println(book2.title + " — " + book2.author + " — " + book2.price + " грн");
        System.out.println(book3.title + " — " + book3.author + " — " + book3.price + " грн");

        //Задача 2
        System.out.println("\n=== Геометричні фігури: Коло ===");

        Circle circle = new Circle();
        circle.radius = 5;

        // Площа і довжина кола
        double area = 3.14159 * circle.radius * circle.radius;
        double circumference = 2 * 3.14159 * circle.radius;

        // результати
        System.out.println("Радіус: " + circle.radius);
        System.out.println("Площа: " + area);
        System.out.println("Довжина кола: " + circumference);
    }
}

// Клас для книг
class Book {
    String title;
    String author;
    double price;

    // Конструктор для ініціалізації
    Book(String t, String a, double p) {
        title = t;
        author = a;
        price = p;
    }$ git config --global user.name "Borsuki23"

    $ git config --global user.email arturgnat2009@gmail.com
}

//Клас для кола (змінна для радіуса)
class Circle {
    double radius;
}
