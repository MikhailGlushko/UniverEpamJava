package ua.epam.mazeold;

import java.util.Stack;

class TestMazeOld {
        public static void main(String[] args) throws Exception {

            testWithNoWay();
            testWithWay();

        }

    private static void testWithNoWay() throws Exception {
        System.out.println("Лабіринт в якому немає шляху міх заданими точками");
        int width = 10;
        int heigth =10;
        System.out.print("Гереруємо Поле-заготовка для лабіринту розміром: " + width + " x " + heigth);
        Maze maze = new Maze(width, heigth);
        System.out.println(" - DONE");
        System.out.print("Генеруємо лабіринт ");
        maze.makeMaze();
        maze.getMazeField()[maze.getHeigth() - 1][maze.getWidth() - 1].setLeft(1);
        maze.getMazeField()[maze.getHeigth() - 1][maze.getWidth() - 1].setUp(1);
        maze.getMazeField()[maze.getHeigth() - 1][maze.getWidth() - 2].setRight(1);
        maze.getMazeField()[maze.getHeigth() - 2][maze.getWidth() - 1].setDown(1);
        System.out.println(" - DONE");
        System.out.print("Задаємо початок та кінець маршруту:");
        maze.setStart(maze.getMazeField()[0][0]);
        maze.setEnd(maze.getMazeField()[maze.getHeigth() - 1][maze.getWidth() - 1]);
        System.out.println(" - DONE");
        System.out.print("Розраховуємо маршрут: ");
        Stack<Cell> way = maze.buildWay();
        maze.showWay(way);
        String text = maze.generateMaze("");
        System.out.println(text);
    }

    private static void testWithWay() throws Exception {
        System.out.println("лабіринт в якому є шлях міх заданими точками");
        int width = 10;
        int heigth =10;
        System.out.print("Гереруємо Поле-заготовка для лабіринту розміром: " + width + " x " + heigth);
        Maze maze = new Maze(width, heigth);
        System.out.println(" - DONE");
        System.out.print("Задаємо початок та кінець маршруту:");
        maze.setStart(maze.getMazeField()[0][0]);
        maze.setEnd(maze.getMazeField()[maze.getHeigth() - 1][maze.getWidth() - 1]);
        System.out.println(" - DONE");
        System.out.print("Генеруємо лабіринт ");
        maze.makeMaze();
        System.out.println(" - DONE");
        maze.save("lab_with_way_clear.txt",maze.generateMaze("Лабіринт без маршруту"));
        System.out.print("Розраховуємо маршрут: ");
        Stack<Cell> way = maze.buildWay();
        maze.showWay(way);
        String text = maze.generateMaze("");
        System.out.println(text);
        maze.save("lab_with_way.txt",text);
        System.out.println();
    }


}
