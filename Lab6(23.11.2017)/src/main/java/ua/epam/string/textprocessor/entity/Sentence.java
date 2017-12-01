package ua.epam.string.textprocessor.entity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Речення складається зі слів, словосполучень та розділових знаків
 */
public class Sentence extends TextElement {

    private static final String DELIMITERS = " ,.-=<=>()~@#$%^&?";

    private List<TextElement> value = new ArrayList<>();

    /**
     * Створення екземпляра речення
     * Текст речення розділяється на слова, кожне слово та кожен розділовий знак закінчення речення вноситься в список,
     * створюються екзаемпляри класів слово та розділовий знак
     * @param sentence
     */
    public Sentence(String sentence) {
        sentence = sentence.replaceAll(",",", ").replaceAll(" +|\\t+", " ");
        int start = 0;
        int index = 0;
        int length = sentence.length();
        char[] chars = sentence.toCharArray();
        while (index < length) {
            if (Character.isSpaceChar(chars[index]) ||
                    DELIMITERS.contains(chars[index]+"")){
                if (start != index) {
                    String st = sentence.substring(start,index);
                    TextElement element = (st.length()==1)?new Letter(st.charAt(0)): Word.getWord(st);
                    value.add(element);
                }
                start = index+1;
                char ch = chars[index];
                TextElement element = new Sign(ch);
                value.add(element);
            }
            index++;
        }
        if(start!=index) {
            String st = sentence.substring(start, index);
            TextElement element = (st.length() == 1) ? new Letter(st.charAt(0)) : Word.getWord(st);
            value.add(element);
        }
    }

    /**
     * Повертає захищений від модифікації список
     * @return
     */
    public List<TextElement> getList() {
        return Collections.unmodifiableList(value);
    }

    /**
     * Повертає список унікальних значень
     * @return
     */
    public List<TextElement> getUniqueList(){
        Set<TextElement> set = new HashSet<>();
        for (TextElement element: value) {
            List<TextElement> uniqueList = element.getUniqueList();
            if (uniqueList!=null&&uniqueList.size()>0)
                set.addAll(uniqueList);
        }
        return set.stream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextElement element : value) {
            sb.append(element.toString());
        }
        return sb.toString();
    }
}
