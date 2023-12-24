package Weapon;

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

public class OpenWeaponWindowCommand implements ShopTavernApp.Command {
    private  List<Weapon> weaponList;
    private Stage primaryStage;
    public OpenWeaponWindowCommand(List<Weapon> weaponList) {
        this.weaponList = weaponList;
    }
    @Override
    public void execute() {
        ShopTavernApp.Command sortWindow= new SortWeapon(primaryStage);
            Stage weaponStage = new Stage();
            weaponStage.setTitle("Buy Weapon.Weapon");

            StackPane weaponLayout = new StackPane();
            weaponLayout.setStyle("-fx-background-color: #000000;");
            Scene weaponScene = new Scene(weaponLayout, 800, 800);

            VBox weaponNamesVBox = new VBox(10);
            weaponNamesVBox.setAlignment(Pos.TOP_LEFT);

            Text descriptionText = new Text("");
            descriptionText.setFill(Color.WHITE);
            Font descriptionFont = Font.font("Arial", FontWeight.BOLD, 18);
            StackPane.setAlignment(descriptionText, Pos.TOP_CENTER);
            descriptionText.setFont(descriptionFont);

            weaponNamesVBox.getChildren().add(descriptionText);

            weaponLayout.getChildren().add(weaponNamesVBox);

            weaponStage.setScene(weaponScene);
            weaponStage.show();

            String description = "Select a weapon from the list:";
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

            timeline.setOnFinished(e -> {
                for (Weapon weapon : weaponList) {
                    Button weaponButton = new Button(weapon.getName());
                    weaponButton.setTextFill(Color.PURPLE);
                    Font buttonFont = Font.font("Arial", FontWeight.BOLD, 22);
                    weaponButton.setFont(buttonFont);
                    weaponButton.setStyle("-fx-background-color: #000000;");
                    weaponNamesVBox.getChildren().add(weaponButton);

                    weaponButton.setOnAction(event -> {
                        String weaponName = weaponButton.getText();
                        openWeaponWindow(weaponName, weaponList);
                    });
                }

                Button sortButton = new Button("Sort");
                sortButton.setTextFill(Color.PURPLE);
                Font buttonFont = Font.font("Arial", FontWeight.BOLD, 22);
                sortButton.setFont(buttonFont);
                sortButton.setStyle("-fx-background-color: #000000;");
                sortButton.setOnAction(event -> {
                    sortWindow.execute();
                    weaponStage.close();
                });
                weaponNamesVBox.getChildren().add(sortButton);


            });


            timeline.play();

    }
    private void openWeaponWindow(String weaponName, List<Weapon> weaponList) {
        Weapon selectedWeapon = null;
        for (Weapon weapon : weaponList) {
            if (weapon.getName().equals(weaponName)) {
                selectedWeapon = weapon;
                break;
            }
        }

        if (selectedWeapon != null) {
            String name = selectedWeapon.getName();
            double price = selectedWeapon.getPrice();
            int index = 2;
            Stage weaponStage = new Stage();
            weaponStage.setTitle("Weapon.Weapon Details");

            StackPane weaponLayout = new StackPane();
            weaponLayout.setStyle("-fx-background-color: #000000;");
            Scene weaponScene = new Scene(weaponLayout, 400, 300);

            VBox weaponDetailsVBox = new VBox(10);
            weaponDetailsVBox.setAlignment(Pos.TOP_LEFT);

            Text nameText = new Text("Name: " + selectedWeapon.getName());
            Text weightText = new Text("Weight: " + selectedWeapon.getWeight());
            Text priceText = new Text("Price: " + selectedWeapon.getPrice());
            Text damageText = new Text("Damage: " + selectedWeapon.getDamage());
            Text rarityText = new Text("Rarity: " + selectedWeapon.getRarity());

            Font textFont = Font.font("Arial", FontWeight.BOLD, 18);
            nameText.setFont(textFont);
            weightText.setFont(textFont);
            priceText.setFont(textFont);
            damageText.setFont(textFont);
            rarityText.setFont(textFont);

            nameText.setFill(Color.RED);
            weightText.setFill(Color.RED);
            priceText.setFill(Color.RED);
            damageText.setFill(Color.RED);
            rarityText.setFill(Color.RED);

            weaponDetailsVBox.getChildren().addAll(nameText, weightText, priceText, damageText, rarityText);

            weaponLayout.getChildren().add(weaponDetailsVBox);

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

            weaponLayout.getChildren().add(buttonsBox);
            weaponStage.setScene(weaponScene);

            weaponStage.show();

            backButton.setOnAction(e -> {
                weaponStage.close();
            });
            CartItem cartItem = new CartItem(name, price, 1, 2);
            addToCartButton.setOnAction(e -> {
                OpenCartWindowCommand.addToCart(cartItem);
                weaponStage.close();

            });
        }
    }
}
