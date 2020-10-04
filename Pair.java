public class Pair<I, Q>{
    I item;
    Q quant;
    String name;
    double price;
    Item itemItem;
    int quantD;

    public Pair(I item, Q quantitiy){
        this.item = item;
        quant = quantitiy;
        itemItem = (Item)item;
        name = itemItem.getName();
        price = itemItem.getPrice();
        quantD = (int)quant;
    }

    public I getItem(){
        return item;
    }

    public Q getQuant(){
        return quant;
    }

    public int getQuantInt(){
        return quantD;
    }

    public String toString(){
        return name+ "\t" + price + "\t" + quantD + "\t" + (quantD * price) + "\n";
    }

    public void setQuant(int quantInput){
        quantD = quantInput;
    }
}