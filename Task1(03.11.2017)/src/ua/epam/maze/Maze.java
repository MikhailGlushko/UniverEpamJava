package ua.epam.maze;

import ua.epam.maze.engine.finder.MazeWayFinder;
import ua.epam.maze.engine.finder.MazeWayFinderWaveTracinc;
import ua.epam.maze.engine.finder.MazeWayFinders;
import ua.epam.maze.engine.generator.MazeFieldGenerator;
import ua.epam.maze.engine.generator.MazeGenerators;

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
 * Дяя генерування скористаємося реалізацією алгоритма Пріма @see {@link ua.epam.maze.engine.generator.MazeFieldGeneratorPrima}
 * <p>
 * Для пошук маршруту скористаємося реалізацією алгоритмів:
 * 1. метод рекрсивного обходу лабіринту @see {@link ua.epam.maze.engine.finder.MazeWayFinderCompleteBypass}
 * 2. метод хвильової трасіровки @see {@link MazeWayFinderWaveTracinc}
 */
public class Maze {

    private int heigth;
    private int width;
    private char[][] mazeField;
    private Point start;
    private Point end;
    public int steps = 0;

    private Stack<Point> way = null;
    private MazeFieldGenerator generator = MazeGenerators.getGeneratorInstance(MazeGenerators.Prima);    //default maze generatoe
    private MazeWayFinder      finder    = MazeWayFinders.getFinderInstance(MazeWayFinders.WaveTracing); // default way finder;

    public Maze() {
        this(11,1);
    }

    public Maze(int heigth, int width) {
        System.out.println("\nІніціалізовуємо лабіринт розміром "+heigth+" x "+width);
        this.heigth = heigth%2==1?heigth:heigth+1;
        this.width = width%2==1?width:width+1;
        mazeField = new char[this.heigth][this.width];
        clearMazeField();
    }

    public void generateMaze() {

        if(generator==null)
            throw new UnsupportedOperationException("Не задано генератор лабіринту");

        mazeField = new char[heigth][width];
        clearMazeField();

        System.out.println("Застосовуємо "+generator.getClass().getSimpleName()+" для створення лабіринту");
        mazeField = generator.generate(mazeField);
    }


    public void generateMaze(boolean noWay) {
        generateMaze();
        if(noWay){
            mazeField[heigth-2][width-2]='1';
        }
    }

    public void getWay(){
        if(finder==null)
            throw new UnsupportedOperationException("Не задано алгоритм пошуку шляхулабіринту");

        steps=0;
        System.out.println("Застосовуємо "+finder.getClass().getSimpleName()+" для пошуку шляху в лабіринті з точки "+start+" т вочку "+end);
        way = (Stack<Point>)finder.getWay(mazeField,start,end)[1];
        steps = (int)finder.getWay(mazeField,start,end)[0];
        System.out.println("Алгоритм зробив кроків: "+steps);
    }

    /**
     * Заносимо в лабіринт початнові значення, а саме всі кімнати позначимо 0, всі стіни позначимо 1
     */
    private void clearMazeField() {
        for (int i = 0; i < heigth; i++)
            for (int j = 0; j < width; j++)
                if (i % 2 == 0 || j % 2 == 0)
                    mazeField[i][j] = '1';
                else
                    mazeField[i][j] = '0';
    }

    /**
     * Друк координат маршруту шляшом вилучення їх зі стеку
     * Якщо стек пустий або не сніє - значить і маршруту не існує
     */
    public void showWay() {
        char[][] tmp = copy(mazeField);
        Stack<Point> stack = way;
        if (stack == null || stack.isEmpty()) {
            System.out.println("Маршрут між точками з координатами " + start + " та " + end + " не існує!\n");
            return;
        }
        System.out.println("Довжина маршрута : "+stack.size());
        System.out.print("Маршрут між точками з координатами " + start + " та " + end + " : ");
        while (!stack.isEmpty()) {
            Point point = stack.pop();
            System.out.print(point + " ");
            mazeField[point.getI()][point.getJ()] = '#';
        }
        System.out.println("");
        showField();
        mazeField = copy(tmp);
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
    /**
     * Виводимо лабіринт
     */
    public void showField() {
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                if(mazeField[i][j]=='0')
                    System.out.print("  ");
                else
                    System.out.print(mazeField[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setStart(Point point) {
        this.start = point;
        mazeField[point.getI()][point.getJ()]='0';
    }

    public void setEnd(Point point) {
        this.end = point;
        mazeField[point.getI()][point.getJ()]='0';
    }

    public int getHeigth() {
        return heigth;
    }

    public int getWidth() {
        return width;
    }

    public void setGenerator(MazeFieldGenerator generator) {
        this.generator = generator;
    }

    public void setFinder(MazeWayFinder finder){
        this.finder = finder;
    }
}
