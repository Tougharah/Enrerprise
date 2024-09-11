import java.util.Scanner;
import java.util.ArrayList;

public class agric {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Creating an empty ArrayList to hold the stocks
        ArrayList<Stock> stocks = new ArrayList<>();

        // Main menu loop
        int choice;
        do {
            displayMenu(); // Call the displayMenu method to show the menu options
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add Stock
                    addStock(stocks, input);
                    break;
                case 2:
                    // Review Stock
                    reviewStock(stocks);
                    break;
                case 3:
                    // View Stock
                    viewStock(stocks);
                    break;
                case 4:
                    // Search Stock
                    searchStock(stocks, input);
                    break;
                case 5:
                    // Filter Stock
                    filterStock(stocks, input);
                    break;
                case 6:
                    // Exit
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        input.close(); // Close the Scanner
    }

    // Method to display the menu options
    private static void displayMenu() {
        System.out.println("\nAgric Stock Management System");
        System.out.println("1. Add Stock");
        System.out.println("2. Review Stock");
        System.out.println("3. View Stock");
        System.out.println("4. Search Stock");
        System.out.println("5. Filter Stock");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to add a new stock item
    private static void addStock(ArrayList<Stock> stocks, Scanner input) {
        System.out.println("\nEnter Stock Details:");
        System.out.print("Item Name: ");
        String name = input.nextLine();
        System.out.print("Quantity: ");

        // Error handling for non-numeric quantity input
        int quantity = 0;
        try {
            quantity = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid quantity input. Please enter a number.");
            input.nextLine(); // Consume the invalid input
            return; // Return to the main menu
        }

        input.nextLine(); // Consume the newline character
        System.out.print("Price: ");

        // Error handling for non-numeric price input
        double price = 0;
        try {
            price = input.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid price input. Please enter a number.");
            input.nextLine(); // Consume the invalid input
            return; // Return to the main menu
        }

        input.nextLine(); // Consume the newline character

        // Create a new Stock object and add it to the ArrayList
        Stock newStock = new Stock(name, quantity, price);
        stocks.add(newStock);
        System.out.println("Stock added successfully.");
    }

    // Method to review stock details
    private static void reviewStock(ArrayList<Stock> stocks) {
        if (stocks.isEmpty()) {
            System.out.println("No stocks available to review.");
            return;
        }

        System.out.println("\nCurrent Stock:");
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
    }

    // Method to view stock details
    private static void viewStock(ArrayList<Stock> stocks) {
        if (stocks.isEmpty()) {
            System.out.println("No stocks available to view.");
            return;
        }

        System.out.println("\nStock Details:");
        for (Stock stock : stocks) {
            System.out.println("Item Name: " + stock.getName());
            System.out.println("Quantity: " + stock.getQuantity());
            System.out.println("Price: " + stock.getPrice());
            System.out.println("Total Value: " + stock.getTotalValue());
            System.out.println("--------------------");
        }
    }

    // Method to search for a specific stock item
    private static void searchStock(ArrayList<Stock> stocks, Scanner input) {
        System.out.print("Enter the item name to search: ");
        String searchTerm = input.nextLine();

        ArrayList<Stock> searchResults = new ArrayList<>();
        for (Stock stock : stocks) {
            if (stock.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(stock);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No stocks found matching the search criteria.");
        } else {
            System.out.println("\nSearch Results:");
            for (Stock stock : searchResults) {
                System.out.println(stock);
            }
        }
    }

    // Method to filter the stock list based on criteria
    private static void filterStock(ArrayList<Stock> stocks, Scanner input) {
        System.out.println("\nFilter Options:");
        System.out.println("1. Filter by Quantity");
        System.out.println("2. Filter by Price");
        System.out.print("Enter your choice: ");

        int filterChoice = input.nextInt();
        input.nextLine(); // Consume the newline character

        ArrayList<Stock> filteredStocks = new ArrayList<>();
        switch (filterChoice) {
            case 1:
                System.out.print("Enter the minimum quantity: ");
                int minQuantity = input.nextInt();
                input.nextLine(); // Consume the newline character
                System.out.print("Enter the maximum quantity: ");
                int maxQuantity = input.nextInt();
                input.nextLine(); // Consume the newline character

                for (Stock stock : stocks) {
                    if (stock.getQuantity() >= minQuantity && stock.getQuantity() <= maxQuantity) {
                        filteredStocks.add(stock);
                    }
                }
                break;
            case 2:
                System.out.print("Enter the minimum price: ");
                double minPrice = input.nextDouble();
                input.nextLine(); // Consume the newline character
                System.out.print("Enter the maximum price: ");
                double maxPrice = input.nextDouble();
                input.nextLine(); // Consume the newline character

                for (Stock stock : stocks) {
                    if (stock.getPrice() >= minPrice && stock.getPrice() <= maxPrice) {
                        filteredStocks.add(stock);
                    }
                }
                break;
            default:
                System.out.println("Invalid filter choice.");
        }

        if (filteredStocks.isEmpty()) {
            System.out.println("No stocks found matching the filter criteria.");
        } else {
            System.out.println("\nFiltered Results:");
            for (Stock stock : filteredStocks) {
                System.out.println(stock);
            }
        }
    }
}

// Stock class to represent a stock item
class Stock {
    private String name;
    private int quantity;
    private double price;

    public Stock(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "Item Name: " + name + ", Quantity: " + quantity + ", Price: " + price;
    }
}