package Armor;

import java.util.ArrayList;
import java.util.List;

public class ArmorList {
    private static List<Armor> armorlist = new ArrayList<>();
    public static List<Armor> getArmorList() {
        return armorlist;
    }
    public static void armorList() {
        armorlist.add(new Armor("Common Armor", 5.0, 10.0, "Common", 10.0, 5));
        armorlist.add(new Armor("Godly Armor", 12.0, 100000000.0, "Godly", 25.0, 1));


    }
}

