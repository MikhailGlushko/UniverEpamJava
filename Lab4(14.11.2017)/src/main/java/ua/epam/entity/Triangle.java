package ua.epam.entity;

public class Triangle extends Figure implements Cloneable {
    private Point apexA, apexB, apexC;
    private Line sideAB, sideAC, sideBC;
    private double perimetr;
    private double area;

    /**
     * Конструируем триугольник за тремя точками
     * @param apexA
     * @param apexB
     * @param apexC
     * @throws IllegalArgumentException - одна из точек не задана или две любые точки равны или все три точки на одной прямой
     */
    public Triangle(Point apexA, Point apexB, Point apexC) throws IllegalArgumentException{
        if(apexA==null || apexA==null || apexC==null || apexA.equals(apexB)
                || apexA.equals(apexC) || apexB.equals(apexC)
                || Point.threePointsOnSameeLine(apexA,apexB,apexC))
            throw new IllegalArgumentException("Помилка, не корректні точки");
        this.apexA = apexA;
        this.apexB = apexB;
        this.apexC = apexC;
    }

    public double getPerimetr(){
        if(perimetr==0)
            this.perimetr = getSideAB().length()+getSideBC().length()+getSideAC().length();
        return perimetr;
    }

    public double getArea(){
        if(area==0){
            double p = getPerimetr()/2;
            this.area = Math.sqrt(p*(p-getSideAC().length())*(p-getSideAB().length())*(p-getSideBC().length()));
        }
        return area;
    }

    public Line getSideAB(){
        if(sideAB==null)sideAB = new Line(apexA, apexB);
        return sideAB;
    }

    public Line getSideAC(){
        if(sideAC==null)sideAC = new Line(apexA, apexC);
        return sideAC;
    }

    public Line getSideBC(){
        if(sideBC==null)sideBC = new Line(apexB, apexC);
        return sideBC;
    }

    /**
     *
     * @param p
     * @throws IllegalArgumentException - на одной линии
     */
    public void setApexA(Point p) throws IllegalArgumentException{
        if(Point.threePointsOnSameeLine(p,getApexB(),getApexC()))
            throw new IllegalArgumentException("Не корректна точка");
        apexA = p;
        sideAB = null;
        sideAC = null;
    }

    /**
     *
     * @param p
     * @throws IllegalArgumentException - на одной линии
     */
    public void setApexB(Point p) throws IllegalArgumentException{
        if(Point.threePointsOnSameeLine(getApexA(),p,getApexC()))
            throw new IllegalArgumentException("Не корректна точка");
        apexB = p;
        sideAB = null;
        sideBC = null;
    }

    /**
     *
     * @param p
     * @throws IllegalArgumentException - на одной линии
     */
    public void setApexC(Point p) throws IllegalArgumentException{
        if(Point.threePointsOnSameeLine(getApexA(),getApexB(),p))
            throw new IllegalArgumentException("Не корректна точка");
        apexC = p;
        sideAC = null;
        sideBC = null;
    }

    public Point getApexA() {
        return apexA;
    }

    public Point getApexB() {
        return apexB;
    }

    public Point getApexC() {
        return apexC;
    }

    @Override
    protected Object clone() {
        try {
            Triangle result = (Triangle) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (apexA != null ? !apexA.equals(triangle.apexA) : triangle.apexA != null) return false;
        if (apexB != null ? !apexB.equals(triangle.apexB) : triangle.apexB != null) return false;
        return apexC != null ? apexC.equals(triangle.apexC) : triangle.apexC == null;
    }

    @Override
    public int hashCode() {
        int result = apexA != null ? apexA.hashCode() : 0;
        result = 31 * result + (apexB != null ? apexB.hashCode() : 0);
        result = 31 * result + (apexC != null ? apexC.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Triangle{" + "apexA=" + apexA + ", apexB=" + apexB + ", apexC=" + apexC + '}';
    }
}
