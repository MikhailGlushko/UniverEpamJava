package ua.epam.mazeold;

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
 * @see "Мозговой М.В. Занимательное программирование.Самоучитель. - Спб.:Питер,2005" - страница 111
 */
class Maze {
    private int lastSet;
    private final int width;
    private final int heigth;
    private final Cell[][] mazeField;
    private Cell start;
    private Cell end;
    private final Map<Integer, Stack> map = new HashMap<Integer, Stack>();

    public Maze(int width, int heigth) {
        this.lastSet = 1;
        this.width = width;
        this.heigth = heigth;

        mazeField = new Cell[heigth][width];
        for (int i = 0; i < heigth; i++)
            for (int j = 0; j < width; j++)
                mazeField[i][j] = new Cell(i, j);
    }

    /**
     * Генерація лабіринту за алгоритмом Краскала
     *
     * @throws Exception
     */
    public void makeMaze() throws Exception {
        int location = width * heigth;
        while (location > 1) { // потрібно прибрати n*n-1 стіну
            // вибираємо випадкову стіну між двома сусідніми клітинками, вертикальну чи горизонтальну
            int wallVerticalOrHotizontal = (int) (Math.random() * 2); //0 - вертикальна, 1 - горизонтальна стіна
            int position = (int) (Math.random() * (width - 1)) + 1;
            int index = (int) (Math.random() * heigth);
            Point from = null;
            Point to = null;

            if (wallVerticalOrHotizontal == 0) {
                from = new Point(index, position - 1);
                to = new Point(index, position);
            } else {
                from = new Point(position - 1, index);
                to = new Point(position, index);
            }

            if (isWallExist(from, to) && !isWayExist(from, to))     // якщо існує стіна між сусідніми клітинками і між ними немає звязку, то прибираємо цю стіну
                if (breakeWall(from, to) == 1)
                    location--;
        }
    }

    /**
     * перевірка наявності стіни між двома сусідніми квадратами лабіринту
     * повертає true - якщо протилежні напрямки в обох клітинок відкриті, в іншому випадку - повертає false
     *
     * @param from - коодинати першої точки
     * @param to - координати сусідньої точки
     * @return boolean
     */
    private boolean isWallExist(Point from, Point to) {
        return (from.getJ() == to.getJ() && from.getI() != to.getI()
                        && mazeField[from.getI()][from.getJ()].getDown() == 1
                        && mazeField[to.getI()][to.getJ()].getUp() == 1 ||
                from.getI() == to.getI() && from.getJ() != to.getJ()
                        && mazeField[from.getI()][from.getJ()].getRight() == 1
                        && mazeField[to.getI()][to.getJ()].getLeft() == 1);
    }

    /**
     * Перевірка наявності шляху між двома сусідніми квадратами
     * повертає tuue -якщо обидві клітинки знаходять в одній множині, в іншому випадку - повертає false
     *
     * @param from - координати першої точки
     * @param to - коодинати сусідньої точки
     * @return boolean
     */
    private boolean isWayExist(Point from, Point to) {
        return (mazeField[from.getI()][from.getJ()].getSet() != 0 ||
                mazeField[to.getI()][to.getJ()].getSet() != 0)
                    && mazeField[from.getI()][from.getJ()].getSet() == mazeField[to.getI()][to.getJ()].getSet();
    }

    /**
     * Прибираємо стіну тільки між між двома сусідніми квадратами
     * після того як стіна прибрана, заносить в клітинки інформацію про множини, до яких віднесено ці клітинки
     * Якщо обидкі клітинки не належали до жодної меожини - вони обидві вносяться до наступної по порядку множини
     * Якщо, хочаб одна з них має множину а інша ні - то нова вноситься до цієї множини
     * Якщо обидві клітинки вже маєть свої множини - обєднаємо їх в одну спільну множину
     * повертає 1 - якщо стіну прибрано, 0 - якщо не прибрано
     *
     * @param from - координати першої точки
     * @param to - координати сусідньої точки
     * @return int
     */
    private int breakeWall(Point from, Point to) {
        if (from.getJ() == to.getJ() && from.getI() != to.getI()) {
            mazeField[from.getI()][from.getJ()].setDown(0);
            mazeField[to.getI()][to.getJ()].setUp(0);
        } else if (from.getI() == to.getI() && from.getJ() != to.getJ()) {
            mazeField[from.getI()][from.getJ()].setRight(0);
            mazeField[to.getI()][to.getJ()].setLeft(0);
        } else {
            return 0;
        }
        int max = Math.max(mazeField[from.getI()][from.getJ()].getSet(), mazeField[to.getI()][to.getJ()].getSet());
        int min = Math.min(mazeField[from.getI()][from.getJ()].getSet(), mazeField[to.getI()][to.getJ()].getSet());

        if (max == 0) {
            AddToSet(from, lastSet);
            AddToSet(to, lastSet++);
        } else if (min == 0) {
            if (mazeField[from.getI()][from.getJ()].getSet() == 0)
                AddToSet(from, max);
            else
                AddToSet(to, max);
        } else {
            MergeSets(min, max);
        }
        return 1;
    }

    /**
     * Заносимо квадрат в множину
     * Заносимо номер множини в клітинку для швидкого порівняння множин, та вномимо цю клітинку в стек з номером клітинки.
     * Стек клітинок з однаковими номерами зберігаємо в карті Map<Integer,Stack<Cell>>
     *
     * @param point - координати точки, яку треба добавити в множину
     * @param value - номер множини, в яку треба добавити
     */
    private void AddToSet(Point point, int value) {
        Stack<Cell> stack = map.get(value);
        if (stack == null)
            stack = new Stack();
        mazeField[point.getI()][point.getJ()].setSet(value);
        stack.push(mazeField[point.getI()][point.getJ()]);
        map.put(value, stack);
    }

    /**
     * об'єднання двох множин, шляхом заміни всіх значить на нові
     * переміщення відбувається з стеку з меньшою чисельністью елементів в стек з більшою чисельністью
     * результуючий стек зберігаємо в в множині, стек з меншою кількістью елементів - видаляємо з множини
     *
     * @param oldValue - номер однієї множини
     * @param newValue - номер іншої множини
     */
    private void MergeSets(int oldValue, int newValue) {

        int value = newValue;
        Stack<Cell> oldStack = map.get(oldValue);
        if (oldStack == null)
            return;

        Stack<Cell> newStack = map.get(newValue);
        if (newStack == null)
            return;

        if (map.get(oldValue).size() >= map.get(newValue).size()) {
            oldStack = map.get(newValue);
            newStack = map.get(oldValue);
            value = oldValue;
            oldValue = newValue;
        }

        while (!oldStack.isEmpty()) {
            Cell cell = oldStack.pop();
            cell.setSet(value);
            mazeField[cell.getI()][cell.getJ()].setSet(value);
            newStack.push(cell);
        }
        map.remove(oldValue);
    }

    /**
     * Шукаємо шлях
     * Пробуємо піти спочатку верх, поки не знайдемо вихід або не попадемо в клітинку с якої немає проходу
     * Якщо немає прокоду вверх пробуємо пройти вправо
     * Якщо немає прохоту вправо пробуємо пройти вниз
     * Якщо немає проходу вниз пробуємо пройти вліво
     * після кожної спроби - запамятовуємо напрямок куди ми пробували пройти
     * Якщо вичерпані всі спроби повертаємось на крок назад і пробуємо продовжити пошук шляху
     * з урахуванням раніше відмічених напрямків руху для цієї клітинки
     * Продовжуємо пошук шляху допоки не знайдемо вихід або не вопернемося в початкову клітинку
     * і для неї вичерпаємо всі мпроби продовжити рух
     * Якщо стек не пустий - він містить маршрут,
     * пустий стек - відсутність маршруту
     *
     * @return Stack<Cell>
     * @throws CloneNotSupportedException
     */
    public Stack<Cell> buildWay() throws CloneNotSupportedException {
        int count = 0;
        Stack<Cell> stack = new Stack();
        Cell from = start.clone();
        Cell to = end.clone();
        while (!(from.getI() == to.getI() && from.getJ() == to.getJ())) {
            if (from.getUp() != 1 && from.getI() > 0) { // є прохід вверх і це не вхід
                from.setUp(1);                      // запамятаємо що пішли вверх, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = mazeField[from.getI() - 1][from.getJ()].clone();   // копія клітинки куди пішли
                from.setDown(1);                    // запамятаємо що прийшли знизу, щоб ще раз не піти
            } else if (from.getRight() != 1 && from.getJ() < width - 1) { // є прохід вправо і це не вхід
                from.setRight(1);                   // запамятаємо що пішли вправо, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = mazeField[from.getI()][from.getJ() + 1].clone();   // копія клітинки куди пішли
                from.setLeft(1);                    // запамятаємо що прийшли зліва, щоб ще раз не піти
            } else if (from.getDown() != 1 && from.getI() < heigth - 1) { // є прохід вниз і це не вхід
                from.setDown(1);                    // запамятаємо що пішли вниз, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = mazeField[from.getI() + 1][from.getJ()].clone();   // копія клітинки куди пішли
                from.setUp(1);                      // запамятаємо що прийшли зверху, щоб ще раз не піти
            } else if (from.getLeft() != 1 && from.getJ() > 0) { // є прохід вліво і це не вхід
                from.setLeft(1);                    // запамятаємо що пішли вліво, щоб ще раз не піти
                stack.push(from);                   // запамятаємо поточну позицію
                from = mazeField[from.getI()][from.getJ() - 1].clone();   // копія клітинки куди пішли
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

    /**
     * Перетворюємо стек в чергу
     * для можливості друку в зворотньому порядку
     * (можна було використати двонаправлену чергу - більше ресурсів, меннше часу на вивід результатів)
     * Якщо стек не пустий - він містить маршрут,
     * пустий стек - відсутність маршруту
     *
     * @param stack - стек, який треба реверсувати
     * @return Stack<Cell> - результуючий стек
     */
    private Stack<Cell> reverseStack(Stack<Cell> stack) {
        Stack<Cell> reverse = new Stack();
        while (!stack.isEmpty()) {
            Cell cell = stack.pop();
            mazeField[cell.getI()][cell.getJ()].setUse('#');
            reverse.push(cell);
        }
        return reverse;
    }

    /**
     * Виводимо маршрут
     * послідовно виводимо координати точок, що знаходяться в стеку.
     * Якщо стек не пустий - він містить маршрут,
     * пустий стек - відсутність маршруту
     *
     * @param stack - стек, що містить координати шляху
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
     * Виводимо лабіринт, а точніше генеруємо стрічку-текст лоя наступного друку чи запису в файл
     * Спочатку генеруємо верхню межу лабіринту
     * Потім гереруємо горизонтальні стрічки лабіринту та межі між ними
     * Остання межа - нижня межа лабіринту
     *
     * @param title - текстове поле для виводу назви лабіринту, якщо таке потрібно
     * @return String - результат у вигляді стрічки, після кожного рядка добавлено перехід на новий рядок
     */
    public String generateMaze(String title, boolean... asBinary) {
        StringBuilder builder = new StringBuilder();        // для можливості записати лабіринт в файл
        builder.append(title).append("\n");
        builder.append(start == null ? "Початок маршруту: не вказано" : "Початок маршруту: " + start).append("\n");
        builder.append(end == null ? "Кінець маршруту:  не вказано" : "Кінець маршруту:  " + end).append("\n");

        generateMazeTopBorder(builder,asBinary);

        for (int i = 0; i < heigth; i++) {
            generateMazeHorizontalLine(builder, i,asBinary);
            generateMazeHorizonlBorder(builder, i,asBinary);
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * генеруємо стіни між i та i+1 рядком лабіринту
     *
     * @param builder
     * @param row - параметр що задає номер рядка лабіринту
     */
    private void generateMazeHorizonlBorder(StringBuilder builder, int row, boolean...asBinary) {
        boolean binary = false;
        if(asBinary!=null && asBinary.length>0 && asBinary[0]==true)
            binary = true;
        builder.append(binary?"1":"+");
        for (int col = 0; col < width; col++) {
            if (mazeField[row][col].getDown() == 1)
                if (col != 0 && col != heigth - 1 && row == start.getI() && col == start.getJ()
                        || col != 0 && col != heigth - 1 && row == end.getI() && col == end.getJ()) // вхід чи вихід знизу, для кутових - тільки в боки
                    builder.append(binary?" 1 ":" # ");
                else
                    builder.append(binary?" 1 ":"---");
            else if (row + 1 < heigth && mazeField[row][col].getUse() != ' ' && mazeField[row + 1][col].getUse() != ' ')
                builder.append(binary?" 1 ":" # ");
            else
                builder.append(binary?" 0 ":"   ");

            if (col < width)
                builder.append(binary?"1":"+");
        }
        builder.append("\n");
    }

    /**
     * генеруємо i-й рядок лабіринту
     *
     * @param builder
     * @param row - параметр що задає номер рядка лабіринту
     */
    private void generateMazeHorizontalLine(StringBuilder builder, int row, boolean ... asBinary) {
        boolean binary = false;
        if(asBinary!=null && asBinary.length>0 && asBinary[0]==true)
            binary = true;
        if (mazeField[row][0].getLeft() == 1)
            if (row == start.getI() && 0 == start.getJ()
                    || row == end.getI() && 0 == end.getJ()) // вхід чи вихід зліва
                builder.append(binary?"0":"#");
            else
                builder.append(binary?"1":"|");
        else
            builder.append(binary?"0":" ");

        for (int col = 0; col < width; col++) {
            builder.append(" " + (binary?"0":mazeField[row][col].getUse()) + " ");
            if (col < width - 1 && mazeField[row][col].getRight() == 1)
                builder.append(binary?"1":"|");
            else if (col < width - 1)
                if (mazeField[row][col].getUse() != ' ' && mazeField[row][col + 1].getUse() != ' ')
                    builder.append(binary?"0":"#");
                else
                    builder.append(binary?"0":" ");
        }

        if (mazeField[row][width - 1].getRight() == 1)
            if (row == end.getI() && width - 1 == end.getJ()
                    || row == start.getI() && width - 1 == start.getJ()) // вхід чи вихід справа
                builder.append(binary?"0":"#");
            else
                builder.append(binary?"1":"|");
        else
            builder.append(" ");
        builder.append("\n");
    }

    /**
     * генеруємо верхню лінію лабіринту
     *
     * @param builder
     */
    private void generateMazeTopBorder(StringBuilder builder, boolean... asBinary) {
        boolean binary = false;
        if(asBinary!=null && asBinary.length>0 && asBinary[0]==true)
            binary = true;
        builder.append(binary?"1":"+");
        for (int col = 0; col < width; col++) {
            if (mazeField[0][col].getUp() == 1)
                if (col != 0 && col != heigth - 1 && 0 == start.getI() && col == start.getJ()
                        || col != 0 && col != heigth - 1 && 0 == end.getI() && col == end.getJ()) // вхід чи вихід зверху, для кутових - тільки в боки
                    builder.append(binary?" 0 ":" # ");
                else
                    builder.append(binary?" 1 ":"---");
            else
                builder.append(binary?" 0 ":"   ");
            if (col < width - 1)
                builder.append(binary?"1":"+");
        }
        builder.append(binary?"1":"+").append("\n");
    }


    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public Cell[][] getMazeField() {
        return mazeField;
    }

    /**
     * Задаємо початок маршруту
     *
     * @param cell - клітинка лабіринту
     * @throws Exception
     */
    public void setStart(Cell cell) throws Exception {
        if (end != null && cell.getI() == end.getI() && cell.getJ() == end.getJ()) {
            System.out.println("Помилка: початок не повинен дорінювати кінцю");
            throw new Exception();
        }
        this.start = cell;
    }

    /**
     * Задаємо кінець маршруту
     *
     * @param cell - клітинка лабіринту
     * @throws Exception
     */
    public void setEnd(Cell cell) throws Exception {
        if (start != null && cell.getI() == start.getI() && cell.getJ() == start.getJ()) {
            System.out.println("Помилка: початок не повинен дорінювати кінцю");
            throw new Exception();
        }
        this.end = cell;
    }

    /**
     * Записуємо лабіринт в файл
     * Файл зберігається в тимчасову папку, яка визначається з оточення системи
     *
     * @param filename назва файлу
     * @param text
     */
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


