import java.util.*;
class OutOfStockException extends Exception {
    int shortfall;

    OutOfStockException(int shortfall) {
        super("Insufficient stock!");
        this.shortfall = shortfall;
    }

    int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {

    int stock = 10;

    void issue(String item, int qty)
            throws OutOfStockException,
                   InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Quantity must be greater than zero."
            );
        }

        if (qty > stock) {
            throw new OutOfStockException(qty - stock);
        }

        stock = stock - qty;

        System.out.println(
            "Successfully issued " + qty + " units of " + item
        );
    }
}

public class StockIssue {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        String[] items = {
            "Laptop", "Mouse", "Keyboard", "Monitor"
        };

        int[] quantities = {4, 0, 8, 2};

        for (int i = 0; i < quantities.length; i++) {

            try {
                warehouse.issue(items[i], quantities[i]);

            } catch (OutOfStockException e) {

                System.out.println(
                    "Error: " + e.getMessage()
                );

                System.out.println(
                    "Shortfall: " + e.getShortfall() + " units"
                );

            } catch (InvalidQuantityException e) {

                System.out.println(
                    "Error: " + e.getMessage()
                );
            }
        }

        System.out.println(
            "Remaining stock: " + warehouse.stock
        );

        System.out.println("All requests processed.");
    }
}
