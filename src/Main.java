class Animal {
    private String name;
    private int age;
    private String breed;
    private int price;

    public Animal() {}

    public Animal(String name, int age, String breed, int price) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.price = price;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getBreed() { return breed; }
    public int getPrice() { return price; }

    public void displayInfo() {
        System.out.println("Ім'я: " + name + ", Вік: " + age + ", Порода: " + breed + ", Ціна: " + price);
    }
}

class ZooShop {
    private Animal[] animals;
    private int count;

    public ZooShop(int size) {
        animals = new Animal[size];
        count = 0;
    }

    public void addAnimal(Animal animal) {
        if (count < animals.length) {
            animals[count] = animal;
            count++;
            System.out.println(animal.getName() + " додано до магазину.");
        } else {
            System.out.println("Магазин заповнений!");
        }
    }

    public void removeAnimal(String name) {
        for (int i = 0; i < count; i++) {
            if (animals[i].getName().equals(name)) {
                System.out.println(name + " продано!");
                animals[i] = animals[count - 1];
                animals[count - 1] = null;
                count--;
                return;
            }
        }
        System.out.println("Тварину не знайдено.");
    }

    public void displayAnimals() {
        System.out.println("Список тварин у магазині:");
        for (int i = 0; i < count; i++) {
            animals[i].displayInfo();
        }
    }

    public Animal findAnimal(String name) {
        for (int i = 0; i < count; i++) {
            if (animals[i].getName().equals(name)) {
                return animals[i];
            }
        }
        return null;
    }
}

class Customer {
    private String name;
    private int money;

    public Customer(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public void buyAnimal(ZooShop shop, String animalName) {
        Animal animal = shop.findAnimal(animalName);
        if (animal != null && money >= animal.getPrice()) {
            money -= animal.getPrice();
            shop.removeAnimal(animalName);
            System.out.println(name + " купив " + animalName + " за " + animal.getPrice() + " грн.");
        } else {
            System.out.println("Не вдалося купити " + animalName + ". Можливо, не вистачає грошей або тварина відсутня.");
        }
    }

    public void displayInfo() {
        System.out.println("Ім'я покупця: " + name + ", Гроші: " + money + " грн");
    }
}

public class Main {
    public static void main(String[] args) {
        ZooShop shop = new ZooShop(10);

        Animal cat = new Animal("Барсик", 3, "Сибірська", 500);
        Animal dog = new Animal("Рекс", 5, "Німецька вівчарка", 1000);

        shop.addAnimal(cat);
        shop.addAnimal(dog);

        shop.displayAnimals();

        Customer customer = new Customer("Іван", 1200);
        customer.displayInfo();

        customer.buyAnimal(shop, "Рекс");
        customer.displayInfo();

        shop.displayAnimals();
    }
}
