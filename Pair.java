public class Pair<I, Q>{
    Item item;
    Q quant;

    public Pair(I item, Q quantitiy){
        this.item = item;
        quant = quantitiy;
    }

    public I getItem(){
        return item;
    }

    public Q getQuant(){
        return quant;
    }
}