package Armor;

public class Armor {
    private String name;
    private double weight;
    private double price;
    private String rarity;
    private double defense;
    private int quantity;

    public Armor(String name, double weight, double price, String rarity, double defense, int quantity) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.rarity = rarity;
        this.defense = defense;
        this.quantity = quantity;
    }

    // Getters for the fields
    public String getName() { return name; }

    public double getWeight() { return weight; }

    public double getPrice() { return price; }

    public String getRarity() { return rarity; }

    public double getDefense() { return defense; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}

class Boots extends Armor {

    public Boots(String name, double weight, double price, String rarity, double defense, int quantity) {
        super(name, weight, price, rarity, defense, quantity);
    }
}

class Shield extends Armor {
    // Additional fields and methods for shield
    public Shield(String name, double weight, double price, String rarity, double defense, int quantity) {
        super(name, weight, price, rarity, defense, quantity);
    }
}

class PlateArmor extends Armor {
    // Additional fields and methods for plate armor
    public PlateArmor(String name, double weight, double price, String rarity, double defense, int quantity) {
        super(name, weight, price, rarity, defense, quantity);
    }
}

class HeraldryAndRings extends Armor {
    // Additional fields and methods for heraldry and rings
    public HeraldryAndRings(String name, double weight, double price, String rarity, double defense,int quantity) {
        super(name, weight, price, rarity, defense, quantity);
    }
}

class HelmetDecorations extends Armor {

    public HelmetDecorations(String name, double weight, double price, String rarity, double defense, int quantity) {
        super(name, weight, price, rarity, defense, quantity);
    }
}

class Helmet extends Armor {
    // Additional fields and methods for helmet
    public Helmet(String name, double weight, double price, String rarity, double defense,int quantity) {
        super(name, weight, price, rarity, defense, quantity);
    }
}

class Chestplate extends Armor {

    public Chestplate(String name, double weight, double price, String rarity, double defense,int quantity) {
        super(name, weight, price, rarity, defense, quantity);
    }
}
