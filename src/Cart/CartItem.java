package Cart;

public class CartItem {
    private String name;
    private double price;
    private int quantity;
    private  int index;

    public CartItem(String name, double price,int quantity,int index  ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.index = index;
    }



    public String getName() {
        return name;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getIndex() {
        return index;
    }
}
