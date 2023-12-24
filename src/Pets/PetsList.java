package Pets;

import java.util.ArrayList;
import java.util.List;
public class PetsList {
    private static  List<Pets> petslist = new ArrayList<>();
    public static List<Pets> getPetsList() {
        return petslist;
    }

    public static void petsList() {

        petslist.add(new Horse(500.0, "A strong and loyal horse", 2, "Brown","Horse"));
        petslist.add(new Dog(250.0, "A friendly and loyal dog", 5, "White","Dog"));
    }
}
