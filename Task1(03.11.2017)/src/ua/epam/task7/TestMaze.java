package ua.epam.task7;

import java.util.Stack;

class TestMaze {
        public static void main(String[] args) throws Exception {

            testWithNoWay();
            testWithWay();

        }

    private static void testWithNoWay() throws Exception {
        System.out.println("Лабіринт в якому немає шляху міх заданими точками");
        int width = 10;
        int heigth =10;
        System.out.print("Гереруємо Поле-заготовка для лабіринту розміром: " + width + " x " + heigth);
        Maze maze = new Maze(10, 10);
        System.out.println(" - DONE");
        //System.out.println(maze.drawMaze(""));
        System.out.print("Генеруємо лабіринт ");
        maze.makeMaze();
        maze.getPole()[maze.getHeigth() - 1][maze.getWidth() - 1].setLeft(1);
        maze.getPole()[maze.getHeigth() - 1][maze.getWidth() - 1].setUp(1);
        maze.getPole()[maze.getHeigth() - 1][maze.getWidth() - 2].setRight(1);
        maze.getPole()[maze.getHeigth() - 2][maze.getWidth() - 1].setDown(1);
        System.out.println(" - DONE");
        System.out.print("Задаємо початок та кінець маршруту:");
        maze.setStart(maze.getPole()[0][0]);
        maze.setEnd(maze.getPole()[maze.getHeigth() - 1][maze.getWidth() - 1]);
        System.out.println(" - DONE");
        System.out.print("Розраховуємо маршрут: ");
        Stack<Cell> way = maze.buildWay();
        maze.showWay(way);
        String text = maze.drawMaze("");
        System.out.println(text);
    }

    private static void testWithWay() throws Exception {
        System.out.println("лабіринт в якому є шлях міх заданими точками");
        int width = 10;
        int heigth =10;
        System.out.print("Гереруємо Поле-заготовка для лабіринту розміром: " + width + " x " + heigth);
        Maze maze = new Maze(10, 10);
        System.out.println(" - DONE");
        //System.out.println(maze.drawMaze(""));
        System.out.print("Генеруємо лабіринт ");
        maze.makeMaze();
        System.out.println(" - DONE");
        System.out.print("Задаємо початок та кінець маршруту:");
        maze.setStart(maze.getPole()[0][0]);
        maze.setEnd(maze.getPole()[maze.getHeigth() - 1][maze.getWidth() - 1]);
        System.out.println(" - DONE");
        maze.save("lab_with_way_clear.txt",maze.drawMaze("Лабіринт без маршруту"));
        System.out.print("Розраховуємо маршрут: ");
        Stack<Cell> way = maze.buildWay();
        maze.showWay(way);
        String text = maze.drawMaze("");
        System.out.println(text);
        maze.save("lab_with_way.txt",text);
        System.out.println();
    }


}
