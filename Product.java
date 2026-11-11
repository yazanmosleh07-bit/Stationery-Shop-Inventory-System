public class Product {
    private  String name;
    private  double price;
    private  int id;
    private  int quantity;

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
    public void getInfo(){
        System.out.println("Name: "+ getName()+ " "+"Price: "+getPrice()+" "+"Quantity: "+" "+getQuantity());
    }

    public void setQuantity(int q){
        quantity = q;
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
    // a method to calculate price
    public double calculatePrice(int qty){
        double result;
        result = (price * quantity);
        quantity -= qty;

        return result;
    }
    
}
