package Pets;

public class Pets {
    private double price;
    private String description;
    private int quantity;
    private String color;
    private String name;

    public Pets(double price, String description, int quantity, String color,String name) {
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.color = color;
        this.name = name;
    }

    public String getName() { return name; }
    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

class Horse extends Pets {
    public Horse(double price, String description, int quantity, String color, String name) {
        super(price, description, quantity, color, name);
    }
}

class Donkey extends Pets {
    public Donkey(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}

class Camel extends Pets {
    public Camel(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}

class Wolf extends Pets {
    public Wolf(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}

class Fox extends Pets {
    public Fox(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}

class Eagle extends Pets {
    public Eagle(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}

class Crow extends Pets {
    public Crow(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}

class Pigeon extends Pets {
    public Pigeon(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}

class Dog extends Pets {
    public Dog(double price, String description, int quantity, String color,String name) {
        super(price, description, quantity, color,name);
    }
}
