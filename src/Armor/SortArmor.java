package Armor;


import MainWindows.ShopTavernApp;

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

public class SortArmor implements ShopTavernApp.Command {
    private List<Armor> armorList = ArmorList.getArmorList();
    private Stage primaryStage;

    public SortArmor(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void execute() {

        Stage sortStage = new Stage();
        sortStage.setTitle("Sort Armor.Armor");
        StackPane sortLayout = new StackPane();
        sortLayout.setStyle("-fx-background-color: #000000;");
        Scene sortScene = new Scene(sortLayout, 200, 200);

        VBox sortButtonsVBox = new VBox(10);

        Font buttonFont = Font.font("Arial", FontWeight.BOLD, 18);

        Button rarityButton = createSortButtonA("Rarity", "rarity", armorList, buttonFont, sortStage);
        Button priceButton = createSortButtonA("Price", "price", armorList, buttonFont, sortStage);
        Button weightButton = createSortButtonA("Weight", "weight", armorList, buttonFont, sortStage);

        sortButtonsVBox.getChildren().addAll(rarityButton, priceButton, weightButton);
        sortLayout.getChildren().add(sortButtonsVBox);

        sortStage.setScene(sortScene);
        sortStage.show();

    }
    private Button createSortButtonA(String text, String property, List<Armor> armorList, Font buttonFont, Stage sortStage) {

        Button button = new Button(text);
        button.setTextFill(Color.PURPLE);
        button.setFont(buttonFont);
        button.setStyle("-fx-background-color: #000000;");
        button.setOnAction(event -> {
            // Сортування броні за вибраним властивостям
            List<Armor> sortedArmorList = new ArrayList<>(armorList);
            sortedArmorList.sort(getComparatorForPropertyA(property));
            ShopTavernApp.Command openBuyArmorWindowCommand = new OpenArmorWindowCommand(sortedArmorList);
            sortStage.close(); // Закрити вікно сортування
            openBuyArmorWindowCommand.execute(); // Відкрити вікно з новим списком
        });
        return button;
    }

    private Comparator<Armor> getComparatorForPropertyA(String property) {
        switch (property) {
            case "rarity":
                return Comparator.comparing(Armor::getRarity);
            case "price":
                return Comparator.comparing(Armor::getPrice);
            case "weight":
                return Comparator.comparing(Armor::getWeight);
            default:
                return Comparator.comparing(Armor::getRarity); // За замовчуванням сортування за рідкістю
        }
    }

}




