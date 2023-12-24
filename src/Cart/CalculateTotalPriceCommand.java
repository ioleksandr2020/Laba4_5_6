package Cart;


import MainWindows.ShopTavernApp;
import javafx.scene.control.Alert;

import java.util.List;

public class CalculateTotalPriceCommand implements ShopTavernApp.Command {

    private  List<CartItem> cartItems;

    public CalculateTotalPriceCommand(List<CartItem> cartItem) {
        this.cartItems = cartItem;

    }
    @Override
    public void execute() {
        double totalPriceWithQuantity = 0.0;

        for (CartItem cartItem : cartItems) {
            int quantity = cartItem.getQuantity(); // Отримуємо кількість товарів
            totalPriceWithQuantity += (cartItem.getPrice() * quantity); // Обчислюємо загальну ціну з урахуванням кількості
        }
        // Вивести результат
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Total Price");
        alert.setHeaderText(null);
        alert.setContentText("Total Price: " + totalPriceWithQuantity);
        alert.showAndWait();
    }
}
