package Weapon;

import Weapon.*;

import java.util.ArrayList;
import java.util.List;

public class WeaponList {   private static List<Weapon> weaponlist = new ArrayList<>();
    public static List<Weapon> getWeaponList() {
        return weaponlist;
    }
    public static void weaponList() {


        weaponlist.add(new OneHandedSword("Bronze Sword", 3.0, 20.0, 25.0, "Common", 3));
        weaponlist.add(new OneHandedSword("Steel Sword", 4.0, 50.0, 40.0, "Rare", 2));
        weaponlist.add(new TwoHandedSword("Greatsword", 6.0, 80.0, 60.0, "Rare", 1));
        weaponlist.add(new Dagger("Assassin's Weapon", 1.0, 100.0, 30.0, "Epic", 2));
        weaponlist.add(new Bow("Longbow", 3.0, 60.0, 45.0, "Common", 2.0, 50.0, 4));
        weaponlist.add(new Bow("Weapon", 5.0, 120.0, 55.0, "Rare", 2.5, 60.0, 3));
        weaponlist.add(new Arrow("Iron Weapon", 0.1, 1.0, 10.0, "Common", "Iron", 100));
        weaponlist.add(new Arrow("Silver Weapon", 0.2, 5.0, 20.0, "Rare", "Silver", 50));
    }
}
