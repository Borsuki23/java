class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void increaseSalary(double amount) {
        if (amount > 0) {
            this.salary += amount;
        }
    }

    public void displayInfo() {
        System.out.println("Имя: " + name + ", Возраст: " + age + ", Зарплата: " + salary);
    }
}

class Manager extends Employee {
    private double bonus;

    public Manager(String name, int age, double salary, double bonus) {
        super(name, age, salary);
        this.bonus = bonus;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Бонус: " + bonus);
    }
}

class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String name, int age, double salary, String programmingLanguage) {
        super(name, age, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Язык программирования: " + programmingLanguage);
    }
}

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager("Алексей", 40, 80000, 10000);
        Developer developer = new Developer("Иван", 30, 60000, "Java");

        System.out.println("До повышения зарплаты:");
        manager.displayInfo();
        developer.displayInfo();

        manager.increaseSalary(5000);

        System.out.println("После повышения зарплаты менеджеру:");
        manager.displayInfo();
    }
}
