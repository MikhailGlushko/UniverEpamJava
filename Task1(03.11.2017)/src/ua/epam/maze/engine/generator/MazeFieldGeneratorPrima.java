package ua.epam.maze.engine.generator;

import ua.epam.maze.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MazeFieldGeneratorPrima implements MazeFieldGenerator {
    private int heigth;
    private int width;
    private int[][] wave;
    char[][] mazeField;
    private Map<Integer, Stack> map;
    private int waweIndex = 1;

    @Override
    public char[][] generate(int heigth, int width) {

        char tmp[][] = new char[heigth][width];
        clearMazeField(tmp);

        return generate(tmp);
    }

    /**
     * Генеруємо лабіринт за алгоритмом Пріма
     *
     * 1. Спочатко позначимо всі можливі стіни 1
     * 2. Для N = heigth*width-1 беремо випадкову стіну
     * 3. Якщо між клітинками, що ця стіна розділяє немає шляху - ламаємо стіну
     * 4. зменшуємо N на 1
     * 5. Повторюємо алгоритм з кроку 2
     *
     * Детальне описання алгоритму:
     * Для перевірки чи існує шлях між локаціями скористаємось наступним алгоритмом
     * Коли ми ламаємо стіну між двома локаціями то помічаємо їх деяким числом - заносимо в множину а саме
     * 1. Помітимо всі локаціє числом 0
     * 2. Задаємо початковий номер множини N = 1
     * 3. Якщо дві сусідні локації, для яких треба прибрати стіну відносяться до множини 0 то помічаємо їх обох числом N і збільшуємо число N на одиницю
     * 4. Якщо одна локація в множині 0 а інша відмінна від 0 - заносимо 0-у локацію в множину що не рівна 0
     * 5  Якщо обидві локації знаходяться в різних множинах, відмінних від 0 то переносимо локації, кількість яких менша в іншу множину
     * 6 Якщо множини рівні і не рівні 0 - існує між ними шлях
     */
    @Override
    public char[][] generate(char[][] field) {
        heigth = field.length;
        width  = field[0].length;
        wave = new int[this.heigth][this.width];
        mazeField = field;
        map = new HashMap<Integer, Stack>();

        int location = (width/2)*(heigth/2);

        while (location > 1) {
            int wallVerticalOrHotizontal = (int) (Math.random() * 2); //0 - вертикальна, 1 - горизонтальна стіна
            Point from = null;
            Point to = null;
            if (wallVerticalOrHotizontal == 0) {
                int i = (int) (Math.random() * (heigth / 2)) * 2 + 1;
                int j = (int) (Math.random() * (width / 2 - 1)) * 2 + 2;
                from = new Point(i, j - 1);
                to = new Point(i, j + 1);
            } else {
                int j = (int) (Math.random() * (heigth / 2)) * 2 + 1;
                int i = (int) (Math.random() * (width / 2 - 1)) * 2 + 2;
                from = new Point(i - 1, j);
                to = new Point(i + 1, j);
            }
            if (isWallExist(from, to) && !isWayExist(from, to)) {
                if (breakeWall(from, to)==1)
                    location--;
            }
        }

        return mazeField;
    }

    /**
     * Перевіряємо чи існує шлях між двома точками (чи входять вони в одну множину)
     * @param from - початкова точка
     * @param to   - кінцева точка
     * @return true - існує шлях, false - не існує шляху
     */
    private boolean isWallExist(Point from, Point to) {
        return ((from.getI() == to.getI() && from.getJ() != to.getJ() && mazeField[from.getI()][from.getJ() + 1] == '1') ||
                (from.getI() != to.getI() && from.getJ() == to.getJ()) && mazeField[from.getI() + 1][from.getJ()] == '1');
    }

    /**
     * Перевіряємо чи існує стіна між двома точками
     * @param from - початкова точка
     * @param to   - кінцева точка
     * @return true - існує, false - не існує
     */
    private boolean isWayExist(Point from, Point to) {
        return (wave[from.getI()][from.getJ()] == wave[to.getI()][to.getJ()] && wave[from.getI()][from.getJ()] != 0);
    }

    /**
     * Прибираємо стіну між двома точками та розносимо локаціє по множинах
     * @param from - початкова точка
     * @param to   - кінцева точкаж
     */
    private int breakeWall(Point from, Point to) {
        int row = 0;
        int col = 0;
        if (from.getI() == to.getI() && from.getJ() != to.getJ()) {
            row = from.getI();
            col = from.getJ() + 1;
        } else if (from.getI() != to.getI() && from.getJ() == to.getJ()) {
            row = from.getI() + 1;
            col = from.getJ();
        } else
            return 0;

        mazeField[row][col] = '0';

        int min = Math.min(wave[from.getI()][from.getJ()], wave[to.getI()][to.getJ()]);
        int max = Math.max(wave[from.getI()][from.getJ()], wave[to.getI()][to.getJ()]);

        if (max == 0) {
            addToSet(from,waweIndex);
            addToSet(to,waweIndex);
            waweIndex++;
        } else if(min==0){
            if(wave[from.getI()][from.getJ()]==0){
                addToSet(from,max);
            } else {
                addToSet(to,max);
            }
        } else {
            mergeSets(min,max);
        }
        return 1;
    }

    /**
     * Добавляє локацію в множину
     * @param point - точка яку треба внести в множину
     * @param value - множина, в яку треба внести точку
     */
    private void addToSet(Point point, int value) {
        Stack<Point> stack = map.get(value);
        if (stack==null) stack = new Stack<>();
        wave[point.getI()][point.getJ()]=value;
        stack.push(point);
        map.put(value,stack);
    }

    /**
     * Обєднує дві множини з локаціями
     * @param first - перша множина
     * @param second - друша множина
     */
    private void mergeSets(int first, int second) {
        int value = second;
        int oldValue = first;

        Stack<Point> newStack = map.get(second);
        if(newStack==null) return;
        Stack<Point> oldStack = map.get(first);
        if(oldStack==null) return;

        if (map.get(first).size() >= map.get(second).size()) {
            oldStack = map.get(second);
            newStack = map.get(first);
            value = first;
            oldValue=second;
        }
        while (!oldStack.isEmpty()){
            Point point = oldStack.pop();
            wave[point.getI()][point.getJ()]=value;
            newStack.push(point);
        }
        map.remove(oldValue);
    }

    /**
     *  Приведення поля в початковий стан
     * @param mazeField
     */
    private void clearMazeField(char[][] mazeField) {
        for (int i = 0; i < heigth; i++)
            for (int j = 0; j < width; j++)
                if (i % 2 == 0 || j % 2 == 0)
                    mazeField[i][j] = '1';
                else
                    mazeField[i][j] = '0';
    }
}
