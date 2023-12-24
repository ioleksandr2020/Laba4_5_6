package Weapon;

public class Weapon {
    private String name;
    private double weight;
    private double price;
    private double damage;
    private String rarity;
    private int quantity;

    public Weapon(String name, double weight, double price, double damage, String rarity,int quantity) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.damage = damage;
        this.rarity = rarity;
        this.quantity = quantity;
    }

    // Getters for the fields
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public double getDamage() {
        return damage;
    }

    public String getRarity() {
        return rarity;
    }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
class OneHandedSword extends Weapon {
    private String hilt;
    public OneHandedSword(String name, double weight, double price, double damage, String rarity,int quantity) {
        super(name, weight, price, damage, rarity, quantity);
    }
}

class TwoHandedSword extends Weapon {
    private String hilt;
    public TwoHandedSword(String name, double weight, double price, double damage, String rarity, int quantity) {
        super(name, weight, price, damage, rarity, quantity);
    }
}

class Dagger extends Weapon {
    private String hilt;
    public Dagger(String name, double weight, double price, double damage, String rarity, int quantity) {
        super(name, weight, price, damage, rarity, quantity);
    }
}

class Bow extends Weapon {
    private double chargingTime;
    private double pushStrong;

    public Bow(String name, double weight, double price, double damage, String rarity, double chargingTime, double pushStrong, int quantity) {
        super(name, weight, price, damage, rarity, quantity);
        this.chargingTime = chargingTime;
        this.pushStrong = pushStrong;
    }
    public double getChargingTime() {
        return chargingTime;
    }
    public double getPushStrong() {
        return pushStrong;
    }

}

class Crossbow extends Weapon {
    private double chargingTime;
    private double pushStrong;


    public Crossbow(String name, double weight, double price, double damage, String rarity, double chargingTime,double pushStrong,int quantity ) {
        super(name, weight, price, damage, rarity, quantity);
        this.chargingTime = chargingTime;
        this.pushStrong = pushStrong;
    }
    public double getChargingTime() {
        return chargingTime;
    }
    public double getPushStrong() {
        return pushStrong;
    }
}

class Arrow extends Weapon {
    private String material;  // New field for the material
    private double flightSpeed;

    public Arrow(String name, double weight, double price, double damage, String rarity, String material,int quantity) {
        super(name, weight, price, damage, rarity, quantity);
        this.material = material;
        this.flightSpeed = flightSpeed;
    }
    public String getMaterial() {
        return material;

    }
    public double getFlightSpeed() {
        return flightSpeed;
    }
}
