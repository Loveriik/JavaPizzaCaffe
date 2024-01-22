import java.sql.*;
import java.util.ArrayList;

public class GetData {
    String getPizzaTable = "select * from pizza_cafe.pizza_types";
    String getIngredients = "select * from pizza_cafe.ingredients";
    String addOrder = "insert into pizza_cafe.orders(pizza_name, ingredients, total_price, address, status) values (?, ?, ?, ?, ?)";
    String getOrders = "select * from pizza_cafe.orders";
    String changeOrderStatus = "update pizza_cafe.orders set status = ? where order_id = ?" ;
    Connection con;
    Statement statement;
    PreparedStatement pStmt;
    ResultSet result;

    public ArrayList<PizzaType> getPizzaTypes(){
        ArrayList<PizzaType> pizzaArray = new ArrayList<>();

        try {
            con = DriverManager.getConnection(Credentials.url, Credentials.uName, Credentials.password);
            statement = con.createStatement();
            result = statement.executeQuery(getPizzaTable);

            while (result.next()){
                PizzaType pizzaType = new PizzaType();

                pizzaType.position = result.getInt("pizza_id");
                pizzaType.name = result.getString("pizza_type");
                pizzaType.price = result.getInt("price");
                pizzaArray.add(pizzaType);
            }

            for (int i = 0; i < pizzaArray.size(); i++) {
                System.out.println(pizzaArray.get(i).position + "  " + pizzaArray.get(i).name + " " + pizzaArray.get(i).price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pizzaArray;
    }

    public ArrayList<IngredientType> getIngredients(){
        ArrayList<IngredientType> ingredientsArray = new ArrayList<>();

        try {
            con = DriverManager.getConnection(Credentials.url, Credentials.uName, Credentials.password);
            statement = con.createStatement();
            result = statement.executeQuery(getIngredients);

            while (result.next()){
                IngredientType ingredientType = new IngredientType();

                ingredientType.position = result.getInt("id");
                ingredientType.name = result.getString("name");
                ingredientType.price = result.getInt("price");
                ingredientsArray.add(ingredientType);
            }

            for (int i = 0; i < ingredientsArray.size(); i++) {
                System.out.println(ingredientsArray.get(i).position + "  " + ingredientsArray.get(i).name + " " + ingredientsArray.get(i).price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredientsArray;
    }

    public void sendOrder(Order newOrder) {
        try {
            con = DriverManager.getConnection(Credentials.url, Credentials.uName, Credentials.password);
            pStmt = con.prepareStatement(addOrder);

            pStmt.setString(1, newOrder.pizzaType);
            pStmt.setString(2, newOrder.ingredients);
            pStmt.setInt(3, newOrder.totalPrice);
            pStmt.setString(4, newOrder.address);
            pStmt.setString(5, newOrder.status);

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> ordersArray = new ArrayList<>();

        try {
            con = DriverManager.getConnection(Credentials.url, Credentials.uName, Credentials.password);
            statement = con.createStatement();
            result = statement.executeQuery(getOrders);

            while (result.next()){
                Order order = new Order();

                order.id = result.getInt("order_id");
                order.pizzaType = result.getString("pizza_name");
                order.ingredients = result.getString("ingredients");
                order.totalPrice = result.getInt("total_price");
                order.address = result.getString("address");
                order.status = result.getString("status");

                ordersArray.add(order);
            }

            for (int i = 0; i < ordersArray.size(); i++) {
                System.out.println(
                   ordersArray.get(i).id + "  " + ordersArray.get(i).pizzaType + " " +
                   ordersArray.get(i).ingredients + " " + ordersArray.get(i).totalPrice + " " +
                   ordersArray.get(i).address + " " + ordersArray.get(i).status
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersArray;
    }

    public void changeOrderStatus(String status, int id){
        try {
            con = DriverManager.getConnection(Credentials.url, Credentials.uName, Credentials.password);
            pStmt = con.prepareStatement(changeOrderStatus);

            pStmt.setString(1, status);
            pStmt.setInt(2, id);

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
