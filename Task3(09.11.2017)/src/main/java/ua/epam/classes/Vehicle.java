package ua.epam.classes;

abstract public class Vehicle {
    private Point start;
    private Point end;
    private int price;
    private int speed;
    private int year;
    private String name;

    public Vehicle(String name, int year) {
        this.price = price;
        this.speed = speed;
        this.year = year;
        this.name = name;
    }

    @Override
    public String toString() {
        return "name='" + name+ '\'' +
                ", start=" + start +
                ", end=" + end +
                ", price=" + price +
                ", speed=" + speed +
                ", year=" + year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}

