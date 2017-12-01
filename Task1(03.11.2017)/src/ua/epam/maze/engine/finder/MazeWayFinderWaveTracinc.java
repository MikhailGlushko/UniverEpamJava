package ua.epam.maze.engine.finder;

import ua.epam.maze.Point;

import java.util.Stack;

public class MazeWayFinderWaveTracinc implements MazeWayFinder {

    /**
     * Пошук шляху в лабіринті від початкової точки до кінцевої (метод хвильовї трасіровки)
     * <p>
     * 1.Відмітимо спочатко всі локації нулями
     * 2.Початок відмітимо як 1 = N
     * 3.Всі сусідні точки з даною точкою , які відмічені як 0 і до яких можна попасти з даної точки
     * відмітимо 1 (поточна +1) = N+1
     * 4.На наступному кроці візьмемо всі локаціх які відмучені як N+1 і повторимо для них крок 3
     * 5 продовжуємо допоки:
     * - на якомусь етапі не знайдено вихід
     * - на якомусь етапі не помічено жодної локації новим значенням
     * <p>
     * Якщо по завершенню роботи хвилі наша кінцева точка помічена числом відмінним від 0 це значить
     * що до неї існує маршрут і він складає стільки кроків
     * <p>
     * Пошук маршруту такий:
     * йдемо від кінцевої точки і переходимо на позицію, номер якої на 1 менше від поточної
     * Якщо є кілька таких точок - беремо будьяку з них
     * Повторюємо це, допоки не попадемо в початкову точку
     * <p>
     * Якщо стек не пустий - він містить маршрут,
     * пустий стек - відсутність маршруту
     *
     * @return @return маршрут у вигляді стеку
     */
    @Override
    public Object[] getWay(char[][] field, Point start, Point end) {

        int heigth = field.length;
        int width  = field[0].length;
        char[][] mazeField = copy(field);
        Point from = start;
        Point to   = end;

        char[][] tmp = copy(field);
        int steps = 0;
        int waveIndex = 1;
        int[][] wave = new int[heigth][width];

        Stack<Point> oldStack = new Stack<>();
        Stack<Point> newStack = new Stack<>();

        int row = from.getI();
        int col = from.getJ();

        wave[row][col] = waveIndex;

        oldStack.push(from);

        while (!from.equals(to)) {
            int count = 0;
            waveIndex++;
            newStack = new Stack<>();
            while (!oldStack.isEmpty()) {
                steps++;
                Point oldPoint = oldStack.pop();
                row = oldPoint.getI();
                col = oldPoint.getJ();
                if (row > 0 && mazeField[row - 1][col] == '0' && wave[row - 1][col] == 0) {
                    wave[row - 1][col] = waveIndex;
                    to = new Point(row - 1, col);
                    newStack.push(to);
                    count++;
                    if (from.equals(to))
                        break;
                }
                if (col < width - 1 && mazeField[row][col + 1] == '0' && wave[row][col + 1] == 0) {
                    wave[row][col + 1] = waveIndex;
                    to = new Point(row, col + 1);
                    newStack.push(to);
                    count++;
                    if (from.equals(to))
                        break;
                }
                if (row < heigth - 1 && mazeField[row + 1][col] == '0' && wave[row + 1][col] == 0) {
                    wave[row + 1][col] = waveIndex;
                    to = new Point(row + 1, col);
                    newStack.push(to);
                    count++;
                    if (from.equals(to))
                        break;
                }
                if (col > 0 && mazeField[row][col - 1] == '0' && wave[row][col - 1] == 0) {
                    wave[row][col - 1] = waveIndex;
                    to = new Point(row, col - 1);
                    newStack.push(to);
                    count++;
                    if (from.equals(to))
                        break;
                }
            }
            oldStack = newStack;
            if (count == 0)
                break;
        }

        to = end;
        row = to.getI();
        col = to.getJ();

        Stack<Point> stack = new Stack<>();

        if (wave[row][col] > 1) {
            stack.push(new Point(to.getI(), to.getJ()));
            waveIndex = wave[row][col];
            while (waveIndex > 1) {
                if (row > 0 && wave[row - 1][col] == waveIndex - 1) {
                    row--;
                } else if (col < width - 1 && wave[row][col + 1] == waveIndex - 1) {
                    col++;
                } else if (row < heigth - 1 && wave[row + 1][col] == waveIndex - 1) {
                    row++;
                } else if (col > 0 && wave[row][col - 1] == waveIndex - 1) {
                    col--;
                }
                waveIndex--;
                stack.push(new Point(row, col));
            }
        }

        return new Object[]{steps,stack};
    }

    private char[][] copy(char[][] from){
        int heigth = from.length;
        int width  = from[0].length;
        char[][] tmp = new char[heigth][heigth];
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                tmp[i][j] = from[i][j];
            }
        }
        return tmp;
    }
}
