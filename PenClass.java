class penClass extends Product {
    String inkColor;

    penClass(String name, int price, int qunatity,String color){
        super(name,price,qunatity);
        inkColor = color;
    }

    public void showInks(){}

    public double calculatePrice(int qty, double discount) {
        
            return super.calculatePrice(qty,discount);
    }

    
}