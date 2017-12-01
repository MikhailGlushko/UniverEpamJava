package ua.epam.maze;

import ua.epam.maze.engine.finder.MazeWayFinders;
import ua.epam.maze.engine.generator.MazeGenerators;

public class TestMazeNew {
    public static void main(String[] args) {
        Maze maze = new Maze(11,11);
        maze.setGenerator(MazeGenerators.getGeneratorInstance(MazeGenerators.Prima));
        maze.generateMaze();
        maze.setStart(new Point(1,0));
        maze.setEnd(new Point(maze.getHeigth()-1, maze.getWidth()-2));
        maze.showField();

        maze.setFinder(MazeWayFinders.getFinderInstance(MazeWayFinders.WaveTracing));
        maze.getWay();
        maze.showWay();

        maze.setFinder(MazeWayFinders.getFinderInstance(MazeWayFinders.CompleteBypass));
        maze.getWay();
        maze.showWay();

        maze = new Maze(11,11);
        // генератор не задаємо - використаємо по замовчуванню MazeGenerators.Prima
        maze.generateMaze(true);
        maze.setStart(new Point(1,0));
        maze.setEnd(new Point(maze.getHeigth()-1, maze.getWidth()-2));
        maze.showField();

        // алгоритм пошуку не задаємо - використаємо по замовчуванню MazeWayFinders.WaveTracing
        maze.getWay();
        maze.showWay();

        maze.setFinder(MazeWayFinders.getFinderInstance(MazeWayFinders.CompleteBypass));
        maze.getWay();
        maze.showWay();

    }
}
