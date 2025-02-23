abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void makeSound() {
        System.out.println(name + " - Some generic animal sound");
    }
}

class Dog extends Animal {
    public Dog() {
        super("Dog");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " - Woof woof");
    }
}

class Cat extends Animal {
    public Cat() {
        super("Cat");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " - Meow");
    }
}

class Parrot extends Animal {
    public Parrot() {
        super("Parrot");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " - Squawk");
    }
}

class CustomAnimal extends Animal {
    private String sound;

    public CustomAnimal(String name, String sound) {
        super(name);
        this.sound = sound;
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " - " + sound);
    }
}

class Fish extends Animal {
    public Fish() {
        super("Fish");
    }

    @Override
    public void makeSound() {
        // Fish does not make a sound
    }

    public void swim() {
        System.out.println(getName() + " is swimming");
    }
}

public class Main {
    public static void printAnimalActions(Animal[] animals) {
        for (Animal animal : animals) {
            if (animal instanceof Fish) {
                ((Fish) animal).swim();
            } else {
                animal.makeSound();
            }
        }
    }

    public static void main(String[] args) {
        Animal[] animals = {
                new Dog(),
                new Cat(),
                new Parrot(),
                new Fish(),
                new CustomAnimal("Lion", "Roar")
        };

        printAnimalActions(animals);
    }
}
