package ua.epam.task7;

public class Cell implements Cloneable{
    private final int i;
    private final int j;
    private int set;
    private int left, right, up, down;
    private char use;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
        this.set = 0;
        this.up = 1;
        this.right = 1;
        this.down = 1;
        this.left = 1;
        this.use = ' ';
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public char getUse() {
        return use;
    }

    public void setUse(char use) {
        this.use = use;
    }

    @Override
    public String toString() {
        return "[" +
                i +","+
                j +
                //", set=" + set +
                //", left=" + left +
                //", right=" + right +
                //", up=" + up +
                //", down=" + down +
                //", use=" + use +
                ']';
    }

    @Override
    protected Cell clone() throws CloneNotSupportedException {
        return (Cell)super.clone();
    }
}
