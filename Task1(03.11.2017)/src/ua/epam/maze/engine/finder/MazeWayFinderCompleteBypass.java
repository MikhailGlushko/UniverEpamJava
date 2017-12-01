package ua.epam.maze.engine.finder;

import ua.epam.maze.Point;

import java.util.Stack;

public class MazeWayFinderCompleteBypass implements MazeWayFinder {

    /**
     * Пошук шляху в лабіринті від початкової точки до кінцевої (метод рекурсивного обходу)
     * <p>
     * Пробуємо піти спочатку верх, поки не знайдемо вихід або не попадемо в тупік
     * Якщо немає прокоду вверх пробуємо пройти вправо
     * Якщо немає прохоту вправо пробуємо пройти вниз
     * Якщо немає проходу вниз пробуємо пройти вліво
     * після кожної спроби - запамятовуємо звідки ми прийшли
     * Якщо вичерпані всі спроби повертаємось на крок назад і пробуємо продовжити пошук шляху
     * Продовжуємо пошук шляху допоки не знайдемо вихід або не попадемо в початкову клітинку
     * і для неї вичерпаємо всі спроби продовжити рух
     * Якщо стек не пустий - він містить маршрут,
     * пустий стек - відсутність маршруту
     *
     * @return маршрут у вигляді стеку
     */
    @Override
    public Object[] getWay(char[][] field, Point start, Point end) {

        int heigth = field.length;
        int width  = field[0].length;
        char[][] mazeField = copy(field);

        char[][] tmp = copy(field);
        int steps = 0;
        Stack<Point> stack = new Stack<>();
        Point from = start;
        Point to = end;

        while (!from.equals(to)) {
            steps++;
            int row = from.getI();
            int col = from.getJ();
            mazeField[row][col] = '1';
            if (row > 0 && mazeField[row - 1][col] == '0') {
                stack.push(from);
                from = new Point(row - 1, col);
            } else if (col < width - 1 && mazeField[row][col + 1] == '0') {
                stack.push(from);
                from = new Point(row, col + 1);
            } else if (row < heigth - 1 && mazeField[row + 1][col] == '0') {
                stack.push(from);
                from = new Point(row + 1, col);
            } else if (col > 0 && mazeField[row][col - 1] == '0') {
                stack.push(from);
                from = new Point(row, col - 1);
            } else if (!stack.isEmpty()) {
                from = stack.pop();
            } else
                break;
        }

        Stack<Point> reverse = new Stack<>();
        if (!stack.isEmpty())
            stack.push(to);

        while (!stack.isEmpty())
            reverse.push(stack.pop());

        return new Object[]{steps,reverse};
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
