package ua.epam.task7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * написати програму, що знаходить прохід по лабіринту
 * Лабіринт задано у вигляді матриці квадратів
 * Кожен квадрат або відкритий або закритий
 * Входити в закритий квадрат заборонено. Якщо квадат відкритий, то в нього можна зайти тільки зі сторони, з кута - заборонено.
 * Кржен квадрат задається його координатами в матриці.
 * Програма находить прохід через лабіринт, рухаючись від заданого входу.
 * Після знаходження шляху програма виводить його у вигляді координат квадратів. Для зберігання шляху використовувати стек.
 * <p>
 * Лабіринт генерується за аогоритмом Краскала.
 */
class Maze {
    private int lastSet;
    private final int width;
    private final int heigth;
    private final Cell[][] pole;
    private Cell start;
    private Cell end;
    private final Map<Integer, Stack> map = new HashMap<Integer, Stack>();

    public Maze(int width, int heigth) {
        this.lastSet = 1;
        this.width = width;
        this.heigth = heigth;

        pole = new Cell[heigth][width];
        for (int i = 0; i < heigth; i++)
            for (int j = 0; j < width; j++)
                pole[i][j] = new Cell(i, j);
    }

    /**
     * Генерація лабіринту за алгоритмом Краскала
     *
     * @throws Exception
     */
    public void makeMaze() throws Exception {
        int[][][] wall = new int[heigth][width][2];
        int location = width * heigth;
        while (location > 1) {
            int wallVerticalOrHotizontal = (int) (Math.random() * 2); //0 - вертикальна, 1 - горизонтальна стіна
            int position = (int) (Math.random() * (width - 1)) + 1;
            int index = (int) (Math.random() * heigth);
            if (wallVerticalOrHotizontal == 0) {
                int row = index;
                if ((wall[row][position][wallVerticalOrHotizontal] == 0)                // існує стіна
                        && (pole[row][position - 1].getSet() == 0                       // не існує шляху
                        || pole[row][position].getSet() == 0
                        || pole[row][position - 1].getSet() != pole[row][position].getSet())) {
                    wall[row][position][wallVerticalOrHotizontal] = breakeWall(row, position, wallVerticalOrHotizontal);    //  ламаємо стіну і обєднуємо множини
                    location--;
                }
            } else {
                int col = index;
                if ((wall[position][col][wallVerticalOrHotizontal] == 0)                // існує стіна
                        && (pole[position - 1][col].getSet() == 0                       // не існує шляху
                        || pole[position][col].getSet() == 0
                        || pole[position - 1][col].getSet() != pole[position][col].getSet())) {
                    wall[position][col][wallVerticalOrHotizontal] = breakeWall(col, position, wallVerticalOrHotizontal);    //  ламаємо стіну і обєднуємо множини
                    location--;
                }
            }
        }
    }

    /**
     * забираємо вертикальну стіну між двома сусідніми квадратами, якщо між ними не існує шляху
     *
     * @param index
     * @param position
     * @param wallVerticalOrHotizontal
     * @return 1
     * @throws Exception
     */
    private int breakeWall(int index, int position, int wallVerticalOrHotizontal) throws Exception {
        if ((wallVerticalOrHotizontal == 0) && (index > heigth || position > width - 1) || (wallVerticalOrHotizontal == 0) && (index > width || position > heigth - 1)) {
            System.out.println("Виникла помилка в роботі алгоритму формування лабіринту...");
            throw new Exception();
        }
        if (wallVerticalOrHotizontal == 0 && pole[index][position - 1].getRight() == 1 && pole[index][position].getLeft() == 1) {
            int row = index;
            pole[row][position - 1].setRight(0);        // прибираємо стіни
            pole[row][position].setLeft(0);
            mergeSetsForVerticalWall(position, row);    // обєднуємо множини
        } else if (wallVerticalOrHotizontal == 1 && pole[position - 1][index].getDown() == 1 && pole[position][index].getUp() == 1) {
            int col = index;
            pole[position - 1][col].setDown(0);         // прибираємо стіни
            pole[position][col].setUp(0);
            mergeSetsForHorizontalWall(position, col);  // обєднуємо множини
        }
        return 1;
    }

    private void mergeSetsForHorizontalWall(int position, int col) {
        int max = Math.max(pole[position - 1][col].getSet(), pole[position][col].getSet());
        int min = Math.min(pole[position - 1][col].getSet(), pole[position][col].getSet());
        if (max == 0) {                                     // добавляємо квадрати в нову множину
            setNewSet(position - 1, col, lastSet);
            setNewSet(position, col, lastSet++);
        } else if (min == 0) {                              // обєднуємо квадрат з існуючою множиною
            if (pole[position - 1][col].getSet() == 0)
                setNewSet(position - 1, col, max);
            else
                setNewSet(position, col, max);
        } else {                                            // обєднуємо множини
            setNewSet(min, max);
        }
    }

    private void mergeSetsForVerticalWall(int position, int row) {
        int max = Math.max(pole[row][position - 1].getSet(), pole[row][position].getSet());
        int min = Math.min(pole[row][position - 1].getSet(), pole[row][position].getSet());
        if (max == 0) {                                     // добавляємо квадрати в нову множину
            setNewSet(row, position - 1, lastSet);
            setNewSet(row, position, lastSet++);
        } else if (min == 0) {                              // обєднуємо квадрат з існуючою множиною
            if (pole[row][position - 1].getSet() == 0)
                setNewSet(row, position - 1, max);
            else
                setNewSet(row, position, max);
        } else {                                            // обєднуємо множини
            setNewSet(min, max);
        }
    }

    /**
     * Занасення нових елементів в множини
     * або в нову множину, або в вже існуючу
     *
     * @param row
     * @param col
     * @param value
     */
    private void setNewSet(int row, int col, int value) {
        Stack<Cell> stack = map.get(value);
        if (stack == null)
            stack = new Stack();
        pole[row][col].setSet(value);
        stack.push(pole[row][col]);
        map.put(value, stack);
    }

    /**
     * об'єднання двох множин, шляхом заміни всіх значить на нові
     * переміщення відбувається з множини з меньшою чисельністью елементів і множину з більшою чисельністью
     *
     * @param oldValue
     * @param newValue
     */
    private void setNewSet(int oldValue, int newValue) {

        Stack<Cell> oldStack = map.get(oldValue);
        if (oldStack == null)
            return;

        Stack<Cell> newStack = map.get(newValue);
        if (newStack == null)
            return;

        if (map.get(oldValue).size() < map.get(newValue).size()) {
            while (!oldStack.isEmpty()) {
                Cell cell = oldStack.pop();
                cell.setSet(newValue);
                pole[cell.getI()][cell.getJ()].setSet(newValue);
                newStack.push(cell);
            }
            map.remove(oldValue);
        } else {
            while (!newStack.isEmpty()) {
                Cell cell = newStack.pop();
                cell.setSet(oldValue);
                pole[cell.getI()][cell.getJ()].setSet(oldValue);
                oldStack.push(cell);
            }
            map.remove(newValue);
        }
    }

    /**
     * Шукаємо шлях
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Stack buildWay() throws CloneNotSupportedException {
        int count = 0;
        Stack<Cell> stack = new Stack();
        Cell from = start.clone();
        Cell to = end.clone();
        while (!(from.getI() == to.getI() && from.getJ() == to.getJ())) {
            if (from.getUp() != 1 && from.getI() > 0) { // є прохід вверх і це не вхід
                from.setUp(1);                      // запамятаємо що пішли вверх, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = pole[from.getI() - 1][from.getJ()].clone();   // копія клітинки куди пішли
                from.setDown(1);                    // запамятаємо що прийшли знизу, щоб ще раз не піти
            } else if (from.getRight() != 1 && from.getJ() < width - 1) { // є прохід вправо і це не вхід
                from.setRight(1);                   // запамятаємо що пішли вправо, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = pole[from.getI()][from.getJ() + 1].clone();   // копія клітинки куди пішли
                from.setLeft(1);                    // запамятаємо що прийшли зліва, щоб ще раз не піти
            } else if (from.getDown() != 1 && from.getI() < heigth - 1) { // є прохід вниз і це не вхід
                from.setDown(1);                    // запамятаємо що пішли вниз, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = pole[from.getI() + 1][from.getJ()].clone();   // копія клітинки куди пішли
                from.setUp(1);                      // запамятаємо що прийшли зверху, щоб ще раз не піти
            } else if (from.getLeft() != 1 && from.getJ() > 0) { // є прохід вліво і це не вхід
                from.setLeft(1);                    // запамятаємо що пішли вліво, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = pole[from.getI()][from.getJ() - 1].clone();   // копія клітинки куди пішли
                from.setRight(1);                   // запамятаємо що прийшли зправа, щоб ще раз не піти
            } else {
                if (!stack.isEmpty())
                    from = stack.pop();              // повернення на крок назад
                else
                    break;
            }
            count++;
        }
        Stack<Cell> reverse = new Stack();
        if (!stack.isEmpty()) {
            stack.push(to);
            reverse = reverseStack(stack);
        }
        System.out.println(" - DONE");
        System.out.println("Зроблено кроків  = " + count);
        if (!reverse.isEmpty())
            System.out.println("Довжина маршруту = " + (reverse.size() - 1));
        return reverse;
    }

    private Stack<Cell> reverseStack(Stack<Cell> stack) {
        Stack<Cell> reverse = new Stack();
        while (!stack.isEmpty()) {
            Cell cell = stack.pop();
            pole[cell.getI()][cell.getJ()].setUse('#');
            reverse.push(cell);
        }
        return reverse;
    }

    /**
     * Виводимо шлях
     *
     * @param stack
     * @throws CloneNotSupportedException
     */
    public void showWay(Stack<Cell> stack) {
        if (stack.size() < 1)
            System.out.print("Маршрут не знайдено");
        else
            System.out.print("Маршрут : ");
        while (!stack.isEmpty()) {
            Cell cell = stack.pop();
            System.out.print("[" + cell.getJ() + "," + cell.getI() + "] ");
        }
        System.out.println();
    }

    /**
     * Виводимо лабіринт
     *
     * @param title
     */
    public String drawMaze(String title) {
        StringBuilder builder = new StringBuilder();        // для можливості записати лабіринт в файл
        builder.append(title).append("\n");
        builder.append(start == null ? "Початок маршруту: не вказано" : "Початок маршруту: " + start).append("\n");
        builder.append(end == null ? "Кінець маршруту:  не вказано" : "Кінець маршруту:  " + end).append("\n");
        builder.append("+");
        for (int j = 0; j < width; j++) {
            if (pole[0][j].getUp() == 1)
                if (j != 0 && j != heigth - 1 && 0 == start.getI() && j == start.getJ()
                        || j != 0 && j != heigth - 1 && 0 == end.getI() && j == end.getJ()) // вхід чи вихід зверху, для кутових - тільки в боки
                    builder.append(" # ");
                else
                    builder.append("---");
            else
                builder.append("   ");
            if (j < width - 1)
                builder.append("+");
        }
        builder.append("+").append("\n");

        for (int i = 0; i < heigth; i++) {
            if (pole[i][0].getLeft() == 1)
                if (i == start.getI() && 0 == start.getJ()
                        || i == end.getI() && 0 == end.getJ()) // вхід чи вихід зліва
                    builder.append("#");
                else
                    builder.append("|");
            else
                builder.append(" ");

            for (int j = 0; j < width; j++) {
                builder.append(" " + pole[i][j].getUse() + " ");
                if (j < width - 1 && pole[i][j].getRight() == 1)
                    builder.append("|");
                else if (j < width - 1)
                    if (pole[i][j].getUse() != ' ' && pole[i][j + 1].getUse() != ' ')
                        builder.append("#");
                    else
                        builder.append(" ");
            }

            if (pole[i][width - 1].getRight() == 1)
                if (i == end.getI() && width - 1 == end.getJ()
                        || i == start.getI() && width - 1 == start.getJ()) // вхід чи вихід справа
                    builder.append("#");
                else
                    builder.append("|");
            else
                builder.append(" ");
            builder.append("\n");


            builder.append("+");
            for (int j = 0; j < width; j++) {
                if (pole[i][j].getDown() == 1)
                    if (j != 0 && j != heigth - 1 && i == start.getI() && j == start.getJ()
                            || j != 0 && j != heigth - 1 && i == end.getI() && j == end.getJ()) // вхід чи вихід знизу, для кутових - тільки в боки
                        builder.append(" # ");
                    else
                        builder.append("---");
                else if (i + 1 < heigth && pole[i][j].getUse() != ' ' && pole[i + 1][j].getUse() != ' ')
                    builder.append(" # ");
                else
                    builder.append("   ");

                if (j < width)
                    builder.append("+");
            }
            builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }


    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public Cell[][] getPole() {
        return pole;
    }

    public void setStart(Cell cell) throws Exception {
        if (end != null && cell.getI() == end.getI() && cell.getJ() == end.getJ()) {
            System.out.println("Помилка: початок не повинен дорінювати кінцю");
            throw new Exception();
        }
        this.start = cell;
    }

    public void setEnd(Cell cell) throws Exception {
        if (start != null && cell.getI() == start.getI() && cell.getJ() == start.getJ()) {
            System.out.println("Помилка: початок не повинен дорінювати кінцю");
            throw new Exception();
        }
        this.end = cell;
    }

    public void save(String filename, String text) {
        String tmp = System.getenv("TEMP");
        if (tmp == null)
            tmp = System.getenv("TMP");
        if (tmp == null)
            tmp = "";
        else
            tmp += "\\";
        try {
            FileWriter fileWriter = new FileWriter(tmp + filename);
            fileWriter.write(text);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Файл " + tmp + filename + " збережено");
    }
}


