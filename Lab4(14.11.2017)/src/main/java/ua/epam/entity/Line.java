package ua.epam.entity;

public class Line extends Figure implements Cloneable{
    private Point beg;
    private Point end;

    /**
     * Конструируем линию за двумя точками
     * @param beg
     * @param end
     * @throws IllegalArgumentException - исключение если одна из точек == null или точки равны
     */
    public Line(Point beg, Point end) throws IllegalArgumentException{
        this(beg.getX(),beg.getY(), end.getY(), end.getY());
    }

    /**
     * Конструируем линию за координатами двух точек
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @throws IllegalArgumentException - исключение если одна из точек == null или точки равны
     */
    public Line(int x1, int x2, int y1, int y2) throws IllegalArgumentException{
        this.beg = new Point(x1,x2);
        this.end = new Point(y1,y2);

        if(beg==null || end==null || beg.equals(end))
            throw new IllegalArgumentException("Спроба задати точку по неправильним координатам");
    }

    public double length(){
        return Math.sqrt(Math.pow(beg.getX()-end.getX(),2d)+Math.pow(beg.getY()-end.getY(),2d));
    }

    public Point getBeg() {
        return beg;
    }

    public void setBeg(Point beg) {
        this.beg = beg;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    protected Object clone() {
        try {
            Line result =  (Line) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Line{" + "beg=" + getBeg() + ", end=" + getEnd() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (beg != null ? !beg.equals(line.beg) : line.beg != null) return false;
        return end != null ? end.equals(line.end) : line.end == null;
    }

    @Override
    public int hashCode() {
        int result = beg != null ? beg.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
