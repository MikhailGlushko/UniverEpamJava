package ua.epam.maze.engine.generator;

public enum MazeGenerators {
    Prima;

    public static MazeFieldGenerator getGeneratorInstance(MazeGenerators mazeGenerator){
        switch (mazeGenerator){
            case Prima:
                return new MazeFieldGeneratorPrima();
            default:
                return new MazeFieldGeneratorPrima();
        }
    }
}
