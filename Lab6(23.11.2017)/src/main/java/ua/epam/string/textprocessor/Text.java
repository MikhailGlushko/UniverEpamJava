package ua.epam.string.textprocessor;

import ua.epam.string.textprocessor.entity.Paragraph;
import ua.epam.string.textprocessor.entity.TextElement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Task2
 * Создать программу обработки текста учебника по программированию с использованием классов: Символ, Слово, Предложение, Знак препинания и др.
 * Во всех задачах с формированием текста заменять табуляции и последовательности пробелов одним пробелом.
 *
 * 3.	Найти такое слово в первом предложении, которого нет ни в одном из остальных предложений.
 */

public class Text {

    private StringBuilder text;

    public void read(String fileName){
        text = new StringBuilder();
        try {
            BufferedReader file =new BufferedReader(new FileReader(fileName));
            String line="";
            while ((line=file.readLine())!=null){
                text.append(line);
                System.out.println(line);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder getText() {
        return text;
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.read("c:\\Work\\text.txt");
        Paragraph element = new Paragraph(text.text.toString());
        List<TextElement> list = element.getList();
        if(list==null){
            System.out.println("Data not found");
            return;
        }
        Iterator<TextElement> iterator = list.iterator();

        if(!iterator.hasNext()){
            System.out.println("Data not found");
            return;
        }
        List<TextElement> firstSentence = iterator.next().getUniqueList();
        System.out.println();
        System.out.println("Word list: "+firstSentence);

        while (iterator.hasNext()){
            List<TextElement> nextSentence = iterator.next().getUniqueList();
            if(nextSentence!=null) {
                firstSentence.removeAll(nextSentence);
            }
        }
        if (firstSentence.size()>0){
            System.out.println("Unique word list: "+firstSentence);
        } else {
            System.out.println("Data not found");
        }
    }
}
