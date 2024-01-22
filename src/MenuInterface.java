import java.util.ArrayList;
import java.util.Scanner;
public class MenuInterface {
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n" + "Welcome to our Pizza Cafe");
            System.out.println("1. Make an order");
            System.out.println("2. Change order status");
            System.out.println("3. Quit");
            System.out.println("\nEnter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    placeOrder();
                    break;
                case 2:
                    checkOrders();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public void placeOrder(){
        boolean running = true;
        GetData getData = new GetData();
        Order newOrder = new Order();

        while(running) {
            choosePizza(newOrder);
            chooseIngredients(newOrder);
            addAddress(newOrder);
            getData.sendOrder(newOrder);

            running = false;
        }

    }

    public void choosePizza(Order newOrder){
        GetData getData = new GetData();
        System.out.println("Please, choose the pizza you like to order");
        ArrayList<PizzaType> pizzaArrayList = getData.getPizzaTypes();

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice <= 0 || choice > 9) {
            System.out.println("Please, choose pizza type from 1 to 9" + "\n");
        } else {
            newOrder.pizzaType = pizzaArrayList.get(choice - 1).name;
            newOrder.totalPrice += pizzaArrayList.get(choice - 1).price;
        }
    }

    public void chooseIngredients(Order newOrder) {
        boolean running = true;
        GetData getData = new GetData();
        ArrayList<IngredientType> ingredientsArrayList;

        while (running) {
            System.out.println("Please, choose ingredients you like to add");

            ingredientsArrayList = getData.getIngredients();
            System.out.println("8 Finish adding ingredients");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice <= 0 || choice > 8) {
                System.out.println("Please, choose an ingredient from 1 to 7" + "\n");
            }

            if (choice == 8) {
                running = false;
            } else {
                newOrder.totalPrice += ingredientsArrayList.get(choice - 1).price;
                newOrder.ingredients +=  choice + " ";
            }
        }
    }

    public void addAddress(Order newOrder){
        boolean running = true;

        while (running) {
            OrderStatus status = OrderStatus.ORDER_IS_PLACED;

            System.out.println("Type in you address please: ");

            newOrder.address = scanner.nextLine();
            newOrder.status = String.valueOf(status);

            running = false;
        }
    }

    public void checkOrders() {
        GetData getData = new GetData();
        boolean running = true;

        while(running) {
            int orderId = getOrders();
            String status = checkStatus();

            getData.changeOrderStatus(status, orderId);

            running = false;
        }
    }

    public int getOrders(){
        GetData getData = new GetData();

        System.out.println("The list of active orders: \n");
        ArrayList<Order> ordersArray = getData.getOrders();

        System.out.println("\nWhich order status would you like to change? ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        return ordersArray.get(choice - 1).id;
    }

    public String checkStatus(){
        boolean running = true;
        String status = " ";

        while(running) {
            System.out.println("Choose new status: ");
            System.out.println("1. Preparing");
            System.out.println("2. Delivering");
            System.out.println("3. Delivered");

            int choice = scanner.nextInt();
            scanner.nextLine();

            status = String.valueOf(OrderStatus.getStatus(choice));

            running = false;
        }

        return status;
    }

}
