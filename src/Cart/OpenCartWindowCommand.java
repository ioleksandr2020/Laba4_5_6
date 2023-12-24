package Cart;

import Armor.ArmorList;
import Armor.Armor;

import MainWindows.ShopTavernApp;

import Pets.PetsList;
import Pets.Pets;

import Weapon.WeaponList;
import Weapon.Weapon;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;
public class OpenCartWindowCommand implements ShopTavernApp.Command {
    private static List<CartItem> cartItems = new ArrayList<>();
    private static List<Armor> armors;
    private static List<Weapon> weapons;
    private static List<Pets> petss;

    public OpenCartWindowCommand() {
        ArmorList.armorList();
        armors = ArmorList.getArmorList();
        WeaponList.weaponList();
        weapons = WeaponList.getWeaponList();
        PetsList.petsList();
        petss = PetsList.getPetsList();
    }

    public static void setCartItems(List<CartItem> cartItems) {
        OpenCartWindowCommand.cartItems = cartItems;
    }

    protected List<CartItem> getCartItems() {
        return cartItems ;
    }

    @Override
    public void execute() {
        ShopTavernApp.Command calculateTotalPriceCommand = (ShopTavernApp.Command) new CalculateTotalPriceCommand(cartItems);
        // Перевірте, чи список корзини не пустий
        if (!cartItems.isEmpty()) {
            double totalPrice = 0.0;
            // Відображення вікна корзини
            Stage cartStage = new Stage();
            cartStage.setTitle("Корзина");

            StackPane cartLayout = new StackPane();
            cartLayout.setStyle("-fx-background-color: #000000;"); // Set the background color to black
            Scene cartScene = new Scene(cartLayout, 800, 800);

            // Відображення інформації про товар
            VBox productBox = new VBox();
            productBox.setAlignment(Pos.TOP_LEFT);
            Label nameLabel = new Label("Quantity");
            nameLabel.setPrefWidth(200);
            nameLabel.setAlignment(Pos.TOP_LEFT);
            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22)); // Apply the specified font
            nameLabel.setTextFill(Color.PURPLE); // Set text color to purple
            productBox.getChildren().add(nameLabel);

            for (CartItem cartItem : cartItems) {
                Label productNameLabel = new Label(cartItem.getName());
                productNameLabel.setPrefWidth(200);
                productNameLabel.setAlignment(Pos.CENTER_LEFT);
                productNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22)); // Apply the specified font
                productNameLabel.setTextFill(Color.PURPLE); // Set text color to purple

                productBox.getChildren().add(productNameLabel);
            }

            VBox costBox = new VBox();
            costBox.setAlignment(Pos.TOP_CENTER);
            Label costLabel = new Label("Cost");
            costLabel.setPrefWidth(200);
            costLabel.setAlignment(Pos.TOP_CENTER);
            costLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22)); // Apply the specified font
            costLabel.setTextFill(Color.PURPLE); // Set text color to purple
            costBox.getChildren().add(costLabel);

            for (CartItem cartItem : cartItems) {
                Label productCostLabel = new Label(String.valueOf(cartItem.getPrice()));
                productCostLabel.setPrefWidth(200);
                productCostLabel.setAlignment(Pos.CENTER);
                productCostLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22)); // Apply the specified font
                productCostLabel.setTextFill(Color.PURPLE); // Set text color to purple

                costBox.getChildren().add(productCostLabel);
            }

            VBox numberBox = new VBox();
            numberBox.setAlignment(Pos.TOP_RIGHT);
            Label numberLabel = new Label("Number");
            numberLabel.setPrefWidth(200);
            numberLabel.setAlignment(Pos.TOP_RIGHT);
            numberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22)); // Apply the specified font
            numberLabel.setTextFill(Color.PURPLE); // Set text color to purple
            numberBox.getChildren().add(numberLabel);

            for (CartItem cartItem : cartItems) {
                int quantity = cartItem.getQuantity(); // Отримуємо кількість товарів
                totalPrice += (cartItem.getPrice() * quantity); // Обчислюємо загальну ціну з урахуванням кількості
                HBox quantityBox = new HBox();
                quantityBox.setAlignment(Pos.CENTER_RIGHT);

                // Додайте логіку для відображення та оновлення кількості товарів
                Label quantityLabel = new Label(String.valueOf(cartItem.getQuantity()));
                quantityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22)); // Apply the specified font
                quantityLabel.setTextFill(Color.PURPLE); // Set text color to purple

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                spacer.setMaxWidth(5);

                quantityBox.getChildren().addAll(quantityLabel, spacer);
                numberBox.getChildren().add(quantityBox);
            }

            // Кнопка "Back"
            Button backButton = new Button("Back");
            backButton.setStyle("-fx-background-color: #000000;"); // Set the button background color to black
            backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Apply the specified font
            backButton.setTextFill(Color.PURPLE); // Set text color to purple
            backButton.setAlignment(Pos.BOTTOM_LEFT);

            // Кнопка для обчислення та відображення загальної ціни
            Button calculateTotalPriceButton = new Button("Calculate Total Price");
            calculateTotalPriceButton.setStyle("-fx-background-color: #000000;");
            calculateTotalPriceButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            calculateTotalPriceButton.setTextFill(Color.PURPLE);
            calculateTotalPriceButton.setAlignment(Pos.BOTTOM_RIGHT);

            calculateTotalPriceButton.setOnAction(event -> {

                calculateTotalPriceCommand.execute();
            });

            HBox bottomBox = new HBox();
            bottomBox.setAlignment(Pos.BOTTOM_LEFT);
            bottomBox.getChildren().addAll(backButton, calculateTotalPriceButton);

            // Розміщення всіх компонентів
            HBox cartContent = new HBox(10);
            cartContent.setAlignment(Pos.TOP_CENTER);
            cartContent.getChildren().addAll(productBox, costBox, numberBox);

            cartLayout.getChildren().addAll(cartContent, bottomBox);
            cartStage.setScene(cartScene);
            cartStage.show();
        }
    }

    public static void addToCart(CartItem CartItem) {
        // Перевірка, чи такий товар вже є в корзині

        boolean alreadyInCart = cartItems.stream().anyMatch(item -> item.getName().equals(CartItem.getName()));

        if (!alreadyInCart) {
            boolean nameFound = false;

            // Перевірка чи ім'я знаходиться в відповідному лісті
            if (CartItem.getIndex() == 1) {
                nameFound = armors.stream().anyMatch(armor -> armor.getName().equals(CartItem.getName()));
            } else if (CartItem.getIndex() == 2) {
                nameFound = weapons.stream().anyMatch(weapon -> weapon.getName().equals(CartItem.getName()));
            } else if (CartItem.getIndex() == 3) {
                nameFound = petss.stream().anyMatch(pet -> pet.getName().equals(CartItem.getName()));
            }

            if (nameFound) {
                // Додайте товар до ліста корзини з вказаною кількістю
                cartItems.add(CartItem);
            }
        }
    }

}
