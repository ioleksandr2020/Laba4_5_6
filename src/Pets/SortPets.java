package Pets;

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

public class SortPets implements ShopTavernApp.Command {
    private List<Pets> petsList = PetsList.getPetsList();
    private Stage primaryStage;

    public SortPets(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void execute() {

            Stage sortStage = new Stage();
            sortStage.setTitle("Sort Pets.Pets");
            StackPane sortLayout = new StackPane();
            sortLayout.setStyle("-fx-background-color: #000000;");
            Scene sortScene = new Scene(sortLayout, 200, 200);

            VBox sortButtonsVBox = new VBox(10);

            Font buttonFont = Font.font("Arial", FontWeight.BOLD, 18);

            Button costButton = createSortButtonP("Cost", "cost", petsList, buttonFont, sortStage);

            sortButtonsVBox.getChildren().addAll(costButton);
            sortLayout.getChildren().add(sortButtonsVBox);

            sortStage.setScene(sortScene);
            sortStage.show();

    }
    private Button createSortButtonP(String text, String property, List<Pets> petList, Font buttonFont, Stage sortStage) {
        Button button = new Button(text);
        button.setTextFill(Color.PURPLE);
        button.setFont(buttonFont);
        button.setStyle("-fx-background-color: #000000;");
        button.setOnAction(event -> {
            // Sorting pets based on the selected property
            List<Pets> sortedPetList = new ArrayList<>(petList);
            sortedPetList.sort(getComparatorForPropertyP(property));
            ShopTavernApp.Command openBuyPetsWindowCommand = new OpenPetsWindowCommand(sortedPetList);
            sortStage.close(); // Close the sorting window
            openBuyPetsWindowCommand.execute(); // Open a window with the new list
        });
        return button;
    }

    private Comparator<Pets> getComparatorForPropertyP(String property) {
        switch (property) {
            case "cost":
                return Comparator.comparing(Pets::getPrice);
            default:
                return Comparator.comparing(Pets::getPrice); // Default sorting by cost
        }
    }

}





