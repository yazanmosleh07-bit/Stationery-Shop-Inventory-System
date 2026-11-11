import java.io.Serializable;
import java.util.ArrayList;

public class InventoryClass implements Serializable{
    private ArrayList<Product> stock = new ArrayList<>();

    public void setStock(ArrayList<Product> stock) {
        this.stock = stock;
    }
    public ArrayList<Product> getStock() {
        return stock;
    }
    public Product getStockIndex(int i){
        return stock.get(i);
    }
    public void addProduct(Product p ){
        stock.add(p);
    }
    public boolean removeProduct(Product p) {
    return stock.remove(p);
    }

    public boolean removeProductAt(int i) {
    if (i < 0 || i >= stock.size()) {
        return false;
    }
    stock.remove(i);
    return true;
    }
    public int getStockSize(){
        return stock.size();
    }
    public boolean isEmpty(){
        return stock.isEmpty();
    }
    public void printStock() {
    for (int i = 0; i < stock.size(); i++) {
        System.out.println(i + ": " + stock.get(i));
        }
    }
    
}
