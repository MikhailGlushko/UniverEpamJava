package ua.epam.military;

public class Weapon {
    private String name;
    private String partId;
    private int price;

    private Weapon(String name, String partId, int price){
        this.name = name;
        this.partId = partId;
        this.price = price;
    }

    public static Weapon getInstance(){
        int idx = (int)(Math.random()*Integer.MAX_VALUE);
        String name = "weapon"+idx;
        idx = (int)(Math.random()*Integer.MAX_VALUE);
        String partId = "ID"+idx;
        idx = (int)(Math.random()*100000);
        return new Weapon(name,partId,idx);
    }

    public String getName() {
        return name;
    }

    public String getPartId() {
        return partId;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", partId='" + partId + '\'' +
                ", price=" + (double)price/100 +
                '}';
    }
}
