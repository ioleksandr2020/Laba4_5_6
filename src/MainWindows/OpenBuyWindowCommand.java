package MainWindows;

import Armor.ArmorList;
import Armor.Armor;
import Armor.OpenArmorWindowCommand;

import Cart.OpenCartWindowCommand;

import Pets.PetsList;
import Pets.Pets;
import Pets.OpenPetsWindowCommand;

import Weapon.WeaponList;
import Weapon.Weapon;
import Weapon.OpenWeaponWindowCommand;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class OpenBuyWindowCommand implements ShopTavernApp.Command {
        private Stage primaryStage;
    private Scene buyScene;
    private static List<Armor> armors;
    private static List<Weapon> weapons;
    private static List<Pets> petss;
    public OpenBuyWindowCommand(Stage primaryStage) {
        this.primaryStage = primaryStage;
        petss = PetsList.getPetsList();
        weapons = WeaponList.getWeaponList();
        armors = ArmorList.getArmorList();

    }

    @Override
    public void execute() {
        ShopTavernApp.Command openBuyArmorWindow= new OpenArmorWindowCommand(armors);
        ShopTavernApp.Command openBuyWeaponWindow= new OpenWeaponWindowCommand(weapons);
        ShopTavernApp.Command openPetsWeaponWindow= new OpenPetsWindowCommand(petss);
        ShopTavernApp.Command openCartWindow= new OpenCartWindowCommand();

            StackPane buyLayout = new StackPane();
            buyLayout.setStyle("-fx-background-color: #000000;");
            Text buyMessage = new Text("");
            buyMessage.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            buyMessage.setFill(Color.PINK);

            StackPane.setAlignment(buyMessage, Pos.TOP_CENTER);

            buyLayout.getChildren().add(buyMessage);

             buyScene = new Scene(buyLayout, 800, 400);
            primaryStage.setScene(buyScene);

            String message = "Our store has a large selection of ammunition for every knight in our kingdom";
            Duration frameTime = Duration.millis(30);
            Timeline timeline = new Timeline();

            KeyFrame keyFrame = new KeyFrame(Duration.ZERO, event -> {
                buyMessage.setText("");
            });

            timeline.getKeyFrames().add(keyFrame);

            for (int i = 0; i < message.length(); i++) {
                int finalI = i;
                keyFrame = new KeyFrame(frameTime.multiply(i), event -> {
                    buyMessage.setText(buyMessage.getText() + message.charAt(finalI));
                    if (finalI == message.length() - 1) {

                        Button armorButton = new Button("Armor");
                        Button weaponButton = new Button("Weapon");
                        Button petsButton = new Button("Pets");
                        Button cartButton = new Button("Cart");
                        Button backButton = new Button("Back");


                        armorButton.setTextFill(Color.PURPLE);
                        weaponButton.setTextFill(Color.PURPLE);
                        petsButton.setTextFill(Color.PURPLE);
                        cartButton.setTextFill(Color.PURPLE);
                        backButton.setTextFill(Color.PURPLE);


                        Font buttonFont = Font.font("Arial", FontWeight.BOLD, 22);
                        armorButton.setFont(buttonFont);
                        weaponButton.setFont(buttonFont);
                        petsButton.setFont(buttonFont);
                        cartButton.setFont(buttonFont);
                        backButton.setFont(buttonFont);


                        armorButton.setStyle("-fx-background-color: #000000;");
                        weaponButton.setStyle("-fx-background-color: #000000;");
                        petsButton.setStyle("-fx-background-color: #000000;");
                        cartButton.setStyle("-fx-background-color: #000000;");
                        backButton.setStyle("-fx-background-color: #000000");


                        VBox buttonsVBox = new VBox(10);
                        buttonsVBox.setAlignment(Pos.CENTER);
                        buttonsVBox.getChildren().addAll(armorButton, weaponButton, petsButton, cartButton, backButton);

                        buyLayout.getChildren().add(buttonsVBox);

                        armorButton.setOnAction(e -> openBuyArmorWindow.execute());
                        weaponButton.setOnAction(e -> openBuyWeaponWindow.execute());
                        petsButton.setOnAction(e -> openPetsWeaponWindow.execute());
                        cartButton.setOnAction(e -> openCartWindow.execute());
                        backButton.setOnAction(actionEvent -> {
                            ShopTavernApp shopTavernApp = new ShopTavernApp();
                            Stage stage = new Stage();
                            shopTavernApp.start(stage);
                            primaryStage.close();
                            armors.clear(); // Очистити список armors
                            weapons.clear(); // Очистити список weapons
                            petss.clear(); // Очистити список petss
                        });


                    }
                });
                timeline.getKeyFrames().add(keyFrame);
            }
            timeline.play();

    }
}
