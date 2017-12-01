package ua.epam.string.textprocessor.entity;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Параграф складається з речень та знаків закінчення речення
 */
public class Paragraph extends TextElement {

    private List<TextElement> value = new ArrayList<>();

    /**
     * Створення екземпляра класа на основі отриманого тексту
     * Текст розділяється на речення, кожне речення та розділовий знак закінчення речення вноситься в список,
     * створюються екзаемпляри класів речення та розділовий знак
     * @param paragraph
     */
    public Paragraph(String paragraph) {
        paragraph = paragraph.replaceAll(" +|\\t+", " ");
        int start = 0;
        int index = 0;
        int length = paragraph.length();
        char[] chars = paragraph.toCharArray();
        while (index < length) {
            if (chars[index]=='.' && index+1<length && chars[index+1]!='.' && index-1>=0 && chars[index-1]!='.' ||
                    chars[index]=='.' && index+1<length && chars[index+1]==' '||
                    chars[index]=='!' ||
                    chars[index]=='?') {
                if (start != index) {
                    String st = paragraph.substring(start,index);
                    TextElement element = (st.length()==1)?new Letter(st.charAt(0)):new Sentence(st);
                    value.add(element);
                }
                char ch = chars[index];
                start = index+1;
                TextElement element = new Sign(ch);
                value.add(element);
            }
            index++;
        }
        if(start!=index) {
            String st = paragraph.substring(start, index);
            TextElement element = (st.length() == 1) ? new Letter(st.charAt(0)) : new Sentence(st);
            value.add(element);
        }
    }

    /**
     * Повертає захищений від коригування список
     * @return
     */
    public List<TextElement> getList() {
        return Collections.unmodifiableList(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextElement element : value) {
            sb.append(element.toString());
        }
        return sb.toString();
    }

    /**
     * Повертає список з унікальних елементів
     * @return
     */
    @Override
    public List<TextElement> getUniqueList() {
        Set<TextElement> set = new HashSet<>();
        for (TextElement element: value) {
            List<TextElement> uniqueList = element.getUniqueList();
            if (uniqueList!=null&&uniqueList.size()>0)
            set.addAll(uniqueList);
        }
        return set.stream().collect(Collectors.toList());
    }

}
