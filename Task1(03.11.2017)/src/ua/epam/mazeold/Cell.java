package ua.epam.mazeold;

/**
 * клітинка поля лабіринта,
 * має координати Point, множину, до якої вона відноситься (допоміжна змінна для побудови лабіринту)
 * має інформацію про допустимий напрямок руку Вкрх, Права, Лниз та Ліво
 * Зперейти з квадрату в квадрат можливо, якщо протилежні напрямки двох сусідніх клітинок відкриті
 */
public class Cell extends Point implements Cloneable{
    private int set;
    private int left, right, up, down;
    private char use;

    public Cell(int i, int j) {
        super(i,j);
        this.set = 0;
        this.up = 1;
        this.right = 1;
        this.down = 1;
        this.left = 1;
        this.use = ' ';
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
                getI() +","+
                getJ() +
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
