package ua.epam.maze.engine.finder;

public enum MazeWayFinders {
    CompleteBypass,
    WaveTracing;

    public static MazeWayFinder getFinderInstance(MazeWayFinders mazeWayFinders) {
        switch (mazeWayFinders){
            case CompleteBypass:
                return new MazeWayFinderCompleteBypass();
            case WaveTracing:
                return new MazeWayFinderWaveTracinc();
            default:
                return new MazeWayFinderWaveTracinc();
        }
    }
}
