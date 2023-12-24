package Armor;

import Cart.CartItem;
import Cart.OpenCartWindowCommand;

import MainWindows.ShopTavernApp;

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

public class OpenArmorWindowCommand implements ShopTavernApp.Command {
    private Stage primaryStage;
    private List<Armor> armorList;
    public OpenArmorWindowCommand(List<Armor> armorList) {
        this.armorList = armorList;
    }
    @Override
    public void execute() {
        ShopTavernApp.Command sortWindow= new SortArmor(primaryStage);
            Stage armorStage = new Stage();
            armorStage.setTitle("Buy Armor.Armor");
            StackPane armorLayout = new StackPane();
            armorLayout.setStyle("-fx-background-color: #000000;");
            Scene armorScene = new Scene(armorLayout, 800, 800);

            VBox armorContentVBox = new VBox(10);

            Text descriptionText = new Text("");
            descriptionText.setFill(Color.WHITE);
            Font descriptionFont = Font.font("Arial", FontWeight.BOLD, 18);
            StackPane.setAlignment(descriptionText, Pos.TOP_CENTER);
            descriptionText.setFont(descriptionFont);

            armorContentVBox.getChildren().add(descriptionText);

            armorLayout.getChildren().add(armorContentVBox);

            armorStage.setScene(armorScene);
            armorStage.show();

            String description = "In the tavern, you can find various items of protection (except magic) of different rarities";
            Duration frameTime = Duration.millis(30);
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

            timeline.getKeyFrames().add(keyFrame);

            timeline.setOnFinished(e -> {
                for (Armor armor : armorList) {
                    Button armorButton = new Button(armor.getName());
                    armorButton.setTextFill(Color.PURPLE);
                    Font buttonFont = Font.font("Arial", FontWeight.BOLD, 22);
                    armorButton.setFont(buttonFont);
                    armorButton.setStyle("-fx-background-color: #000000;");
                    armorButton.setOnAction(event -> {
                        String armorName = armorButton.getText();
                        openArmorWindow(armorName, armorList);
                    });
                    armorContentVBox.getChildren().add(armorButton);
                }

                Button sortButton = new Button("Sort");
                sortButton.setTextFill(Color.PURPLE);
                Font buttonFont = Font.font("Arial", FontWeight.BOLD, 22);
                sortButton.setFont(buttonFont);
                sortButton.setStyle("-fx-background-color: #000000;");
                sortButton.setOnAction(event -> {
                    sortWindow.execute();
                    armorStage.close();
                });

                armorContentVBox.getChildren().add(sortButton);
            });

            timeline.play();

    }
    private void openArmorWindow(String armorName, List<Armor> armorList) {
        Armor selectedArmor = null;
        for (Armor armor : armorList) {
            if (armor.getName().equals(armorName)) {
                selectedArmor = armor;
                break;
            }
        }

        if (selectedArmor != null) {
            String name = selectedArmor.getName();
            double price = selectedArmor.getPrice();
            int index = 1;
            Stage armorStage = new Stage();
            armorStage.setTitle("Armor.Armor Details");

            StackPane armorLayout = new StackPane();
            armorLayout.setStyle("-fx-background-color: #000000;");
            Scene armorScene = new Scene(armorLayout, 400, 400);

            VBox armorDetailsVBox = new VBox(10);
            armorDetailsVBox.setAlignment(Pos.TOP_LEFT);

            Text nameText = new Text("Name: " + selectedArmor.getName());
            Text weightText = new Text("Weight: " + selectedArmor.getWeight());
            Text priceText = new Text("Price: " + selectedArmor.getPrice());
            Text rarityText = new Text("Rarity: " + selectedArmor.getRarity());
            Text defenseText = new Text("Defense: " + selectedArmor.getDefense());

            Font textFont = Font.font("Arial", FontWeight.BOLD, 18);
            nameText.setFont(textFont);
            weightText.setFont(textFont);
            priceText.setFont(textFont);
            rarityText.setFont(textFont);
            defenseText.setFont(textFont);

            nameText.setFill(Color.RED);
            weightText.setFill(Color.RED);
            priceText.setFill(Color.RED);
            rarityText.setFill(Color.RED);
            defenseText.setFill(Color.RED);

            armorDetailsVBox.getChildren().addAll(nameText, weightText, priceText, rarityText, defenseText);

            armorLayout.getChildren().add(armorDetailsVBox);

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

            armorLayout.getChildren().add(buttonsBox);
            armorStage.setScene(armorScene);

            armorStage.show();

            backButton.setOnAction(e -> {
                armorStage.close();
            });
            CartItem cartItem = new CartItem(name, price, 1, 1);
            addToCartButton.setOnAction(e -> {
                OpenCartWindowCommand.addToCart(cartItem); // Викликаємо метод addToCart з потрібними параметрами
                armorStage.close();
            });

        }
    }
}
