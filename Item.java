public class Item implements Comparable<Item>{
    private String name;
    private int price;

    public Item(String name, int price){
        this.name() = name;
        this.price = price;
    }

    public int compareTo(Item i){
        int value0 = getCharValue(0, name);
        int value1 = getCharValue(1, name);
        int valueCheck0 = getCharValue(0, i.getName());
        int valueCheck1 = getCharValue(1, i.getName());

        if(valueCheck0 == value0){
            if(valueCheck1 == value1){
                if(price = i.getPrice()){
                    return 0;
                }
                else if(price > i.getPrice()){
                    return -1;
                }
                else{
                    return 1;
                }
            }
            else if(valueCheck1 > value1){
                return 1;
            }
            else{
                return 1;
            }
        }
        else if(valueCheck0 > value0){
            return 1;
        }
        else{
            return -1;
        }
    }
    
    public boolean equals(Object o){
        Item i = (Item)o;
        if(name.equals(i.getName()) && price == i.getPrice()){
            return true;
        }

        return false;
    }

    public int hashCode(){
        int value0 = getCharValue(0, name);
        int value1 = getCharValue(1, name);
        int hashCode = value0 * 26 + value1;

        hashCode += int(price * 100);

        return hashCode;
    }

    public int getCharValue(int loc, String word){
        char myChar = word.charAt(loc);
        int value = (int)(myChar0 - 96);

        return value;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}