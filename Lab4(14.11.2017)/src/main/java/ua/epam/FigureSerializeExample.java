package ua.epam;

import ua.epam.entity.Line;
import ua.epam.entity.Point;
import ua.epam.entity.Triangle;

import java.io.*;

public class FigureSerializeExample {

        Point a;
        Point b;
        Point c;
        Line ab;
        Line ac;
        Line bc;
        Triangle abc;

    public static void main(String[] args) {

        FigureSerializeExample f = new FigureSerializeExample();

        f.createFigures();
        System.out.println("After creating");
        f.checkFigures();
        f.serializeFigures();

        f = new FigureSerializeExample();
        f.unSerializeFigures();
        System.out.println("After unserializarion");
        f.checkFigures();
    }

    private void unSerializeFigures() {
        ObjectInputStream ois = null;
        try(FileInputStream fis = new FileInputStream("temp.out")){
            ois = new ObjectInputStream(fis);
            a = (Point) ois.readObject();
            b = (Point) ois.readObject();
            c = (Point) ois.readObject();
            ab = (Line) ois.readObject();
            bc = (Line) ois.readObject();
            ac = (Line) ois.readObject();
            abc = (Triangle) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void serializeFigures() {
        ObjectOutputStream oos = null;
        try (FileOutputStream fos = new FileOutputStream("temp.out")) {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
            oos.writeObject(b);
            oos.writeObject(c);
            oos.writeObject(ab);
            oos.writeObject(bc);
            oos.writeObject(ac);
            oos.writeObject(abc);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFigures() {
        if(a==null || b==null || c==null || ab==null || ac==null || bc==null || abc==null)
            return;

        System.out.println("A = "+a._toString());
        System.out.println("AB.A = "+ab.getBeg()._toString());
        System.out.println("AC.A = "+ac.getBeg()._toString());
        System.out.println("ABC.A = "+abc.getApexA()._toString());
        System.out.println();

        System.out.println("B = "+b._toString());
        System.out.println("AB.B = "+ab.getEnd()._toString());
        System.out.println("BC.B = "+bc.getBeg()._toString());
        System.out.println("ABC.B = "+abc.getApexB()._toString());
        System.out.println();

        System.out.println("C = "+c._toString());
        System.out.println("AC.C = "+ac.getEnd()._toString());
        System.out.println("BC.C = "+bc.getEnd()._toString());
        System.out.println("ABC.C = "+abc.getApexC()._toString());
        System.out.println();
    }

    private void createFigures() {
        a = new Point(0,0);
        b = new Point(0,2);
        c = new Point(2,0);

        ab = new Line(a,b);
        ac = new Line(a,c);
        bc = new Line(b,c);

        abc = new Triangle(a,b,c);
    }
}
