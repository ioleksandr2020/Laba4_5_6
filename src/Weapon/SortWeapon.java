package Weapon;

import MainWindows.ShopTavernApp;
import Weapon.Weapon;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortWeapon implements ShopTavernApp.Command {
    private List<Weapon> weaponList = WeaponList.getWeaponList();
    private Stage primaryStage;

    public SortWeapon(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void execute() {


            Stage sortStage = new Stage();
            sortStage.setTitle("Sort Weapons");
            StackPane sortLayout = new StackPane();
            sortLayout.setStyle("-fx-background-color: #000000;");
            Scene sortScene = new Scene(sortLayout, 200, 200);

            VBox sortButtonsVBox = new VBox(10);

            Font buttonFont = Font.font("Arial", FontWeight.BOLD, 18);

            Button damageButton = createSortButtonW("Damage", "damage", weaponList, buttonFont, sortStage);
            Button priceButton = createSortButtonW("Price", "price", weaponList, buttonFont, sortStage);
            Button weightButton = createSortButtonW("Weight", "weight", weaponList, buttonFont, sortStage);

            sortButtonsVBox.getChildren().addAll(damageButton, priceButton, weightButton);
            sortLayout.getChildren().add(sortButtonsVBox);

            sortStage.setScene(sortScene);
            sortStage.show();


    }
    private Button createSortButtonW(String text, String property, List<Weapon> weaponList, Font buttonFont, Stage sortStage) {

        Button button = new Button(text);
        button.setTextFill(Color.PURPLE);
        button.setFont(buttonFont);
        button.setStyle("-fx-background-color: #000000;");
        button.setOnAction(event -> {
            // Sorting weapons based on the selected property
            List<Weapon> sortedWeaponList = new ArrayList<>(weaponList);
            sortedWeaponList.sort(getComparatorForPropertyW(property));
            ShopTavernApp.Command openBuyWeaponWindowCommand = new OpenWeaponWindowCommand(sortedWeaponList);
            sortStage.close(); // Close the sorting window
            openBuyWeaponWindowCommand.execute(); // Open a window with the new list
        });
        return button;
    }
    private Comparator<Weapon> getComparatorForPropertyW(String property) {
        switch (property) {
            case "damage":
                return Comparator.comparing(Weapon::getDamage);
            case "price":
                return Comparator.comparing(Weapon::getPrice);
            case "weight":
                return Comparator.comparing(Weapon::getWeight);
            default:
                return Comparator.comparing(Weapon::getDamage); // Default sorting by damage
        }
    }

}




