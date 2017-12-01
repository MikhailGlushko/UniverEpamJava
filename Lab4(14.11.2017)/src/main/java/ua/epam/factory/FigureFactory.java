package ua.epam.factory;

import ua.epam.entity.*;

public class FigureFactory extends AbstractFigureFactory {

    public Figure getFigure(FiguresType type) {
        int value = (int)(Math.random()*4);
        int colored = 0;
        switch (type){
            case NO_COLORED:
                colored = 0;
                break;
            case COLORED:
                colored = 1;
                break;
            default:
                colored = (int)(Math.random()*2);
        }
        Point[] points = new Point[value+1];
        for (int i = 0; i <= value; i++) {
            int x = (int)(Math.random()*200-100);
            int y = (int)(Math.random()*200-100);
            points[i] = new Point(x,y);
        }
        int color = (int)(Math.random()*255);
        switch (value){
            case 0:{
                Point a = points[0];
                if (colored==0)
                    return new Point(a.getX(), a.getY());
                else
                    return new ColorPoint(a.getX(), a.getY(),color);
            }
            case 1:{
                Point a = points[0];
                Point b = points[1];
                if(colored==0)
                    return new Line(a,b);
                else
                    return new ColorLine(a,b,color);
            }
            case 2:{
                Point a = points[0];
                Point b = points[1];
                Point c = points[2];
                if(colored==0)
                    return new Triangle(a,b,c);
                else
                    return new ColorTriangle(a,b,c,color);
            }
            case 3:{
                if(colored==0)
                    return new Poligon(points);
                else
                    return new ColorPoligon(color,points);
            }
            default:
                return null;
        }
    }


}
