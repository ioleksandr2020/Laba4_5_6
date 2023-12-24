package Pets;

import MainWindows.ShopTavernApp;

import Cart.CartItem;
import Cart.OpenCartWindowCommand;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;


public class OpenPetsWindowCommand implements ShopTavernApp.Command {

    private final List<Pets> petsList;

    private Stage primaryStage;

    public OpenPetsWindowCommand(List<Pets> petList) {
        this.petsList = petList;
    }

    @Override
    public void execute() {
        ShopTavernApp.Command sortWindow= new SortPets(primaryStage);
            Stage petsStage = new Stage();
            petsStage.setTitle("Buy Pets");
            StackPane petsLayout = new StackPane();
            petsLayout.setStyle("-fx-background-color: #000000;");
            Scene petsScene = new Scene(petsLayout, 400, 400);

            // Vertical container for displaying pet names and description
            VBox petContentVBox = new VBox(10);

            Text descriptionText = new Text("");
            descriptionText.setFill(Color.WHITE);
            Font descriptionFont = Font.font("Arial", FontWeight.BOLD, 18);
            StackPane.setAlignment(descriptionText, Pos.TOP_CENTER);
            descriptionText.setFont(descriptionFont);

            petContentVBox.getChildren().add(descriptionText);

            petsLayout.getChildren().add(petContentVBox);

            petsStage.setScene(petsScene);
            petsStage.show();

            String description = "Select a pet to buy:";
            Duration frameTime = Duration.millis(50);
            Timeline timeline = new Timeline();

            KeyFrame keyFrame = new KeyFrame(Duration.ZERO, event -> {
                descriptionText.setText("");
            });

            timeline.getKeyFrames().add(keyFrame);

            for (int i = 0; i < description.length(); i++) {
                int finalI = i;
                keyFrame = new KeyFrame(frameTime.multiply(i), event -> {
                    descriptionText.setText(descriptionText.getText() + description.charAt(finalI));
                });
                timeline.getKeyFrames().add(keyFrame);
            }
            Font  buttonFont = Font.font("Arial", FontWeight.BOLD, 22); ;
            timeline.setOnFinished(e -> {
                for (Pets pet : petsList) {
                    Button petButton = new Button(pet.getName());
                    petButton.setTextFill(Color.PURPLE);
                    petButton.setFont(buttonFont);
                    petButton.setStyle("-fx-background-color: #000000;");
                    petButton.setOnAction(event -> {
                        String petName = petButton.getText();
                        // Replace the following line with your logic for handling the pet selection
                        openPetWindow(petName, petsList);
                    });
                    petContentVBox.getChildren().add(petButton); // Add the button to the container
                }
                    Button sortButton = new Button("Sort");
                    sortButton.setTextFill(Color.PURPLE);
                    sortButton.setFont(buttonFont);
                    sortButton.setStyle("-fx-background-color: #000000;");
                    sortButton.setOnAction(event -> {
                        sortWindow.execute();
                        petsStage.close();
                    });
                    petContentVBox.getChildren().add(sortButton);

            });

            timeline.play();

    }
    private void openPetWindow(String petName, List<Pets> petsList) {
        Pets selectedPet = null;
        for (Pets pet : petsList) {
            if (pet.getName().equals(petName)) {
                selectedPet = pet;
                break;
            }
        }

        if (selectedPet != null) {
            String name = selectedPet.getName();
            double price = selectedPet.getPrice();
            int index = 3;
            Stage petStage = new Stage();
            petStage.setTitle("Pet Details");

            StackPane petLayout = new StackPane();
            petLayout.setStyle("-fx-background-color: #000000;");
            Scene petScene = new Scene(petLayout, 400, 400);

            VBox petDetailsVBox = new VBox(10);
            petDetailsVBox.setAlignment(Pos.TOP_LEFT);

            Text nameText = new Text("Name: " + selectedPet.getName());
            Text costText = new Text("Cost: " + selectedPet.getPrice());
            Text descriptionText = new Text("Description: " + selectedPet.getDescription());
            Text quantityText = new Text("Quantity: " + selectedPet.getQuantity());
            Text colorText = new Text("Color: " + selectedPet.getColor());

            Font textFont = Font.font("Arial", FontWeight.BOLD, 18);
            nameText.setFont(textFont);
            costText.setFont(textFont);
            descriptionText.setFont(textFont);
            quantityText.setFont(textFont);
            colorText.setFont(textFont);

            nameText.setFill(Color.RED);
            costText.setFill(Color.RED);
            descriptionText.setFill(Color.RED);
            quantityText.setFill(Color.RED);
            colorText.setFill(Color.RED);

            petDetailsVBox.getChildren().addAll(nameText, costText, descriptionText, quantityText, colorText);

            petLayout.getChildren().add(petDetailsVBox);

            HBox buttonsBox = new HBox(10);
            buttonsBox.setAlignment(Pos.BOTTOM_LEFT);

            Button backButton = new Button("Back");
            backButton.setTextFill(Color.PURPLE);
            Font buttonFont = Font.font("Arial", FontWeight.BOLD, 18);
            backButton.setFont(buttonFont);
            backButton.setStyle("-fx-background-color: #000000;");

            Button addToCartButton = new Button("Cart");
            addToCartButton.setTextFill(Color.PURPLE);
            addToCartButton.setFont(buttonFont);
            addToCartButton.setStyle("-fx-background-color: #000000;");

            buttonsBox.getChildren().addAll(backButton, addToCartButton);

            petLayout.getChildren().add(buttonsBox);
            petStage.setScene(petScene);

            petStage.show();

            backButton.setOnAction(e -> {
                petStage.close();
            });
            CartItem cartItem = new CartItem(name, price, 1, 3);
            addToCartButton.setOnAction(e -> {
                OpenCartWindowCommand.addToCart(cartItem);
                petStage.close();
            });
        }
    }
}