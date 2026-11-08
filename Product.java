public class Product {
    public String name;
    public double price;
    public int id;
    public int quantity;

    Product(){

    }
    Product(String name, double price, int qunatity){
        this.quantity = qunatity;
        this.price = price;
        this.name = name;
    }

    // all the getters for the attributes 
    public int getId(){

        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return  quantity;

    }

    // the methods that controls the quantity
    public void addStock(int amount){
        quantity += amount;
    }
    public void removeStock(int amount){
        if (quantity > 0){
            quantity -= amount;
        }else{
            System.out.println("Can not remove, quantity is 0");
        }
    }
    // a method to calculate price for now there isn't any discount perameters 
    public int calculatePrice(int qty){
        return qty;
    }
}
