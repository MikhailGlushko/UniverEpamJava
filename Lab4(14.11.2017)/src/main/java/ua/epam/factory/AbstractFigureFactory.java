package ua.epam.factory;

import ua.epam.entity.Figure;

abstract public class AbstractFigureFactory {
    public enum FiguresType{
        COLORED, NO_COLORED, COLORED_AND_NO_COLORED,
    }
    public abstract Figure getFigure(FiguresType type);
}
