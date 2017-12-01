package ua.epam.string.textprocessor.entity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Слово
 * Реалізовано паттерн "flyweight" для кешування раніше створених обєктів типу Word
 */
public class Word extends TextElement {

    private static final Map<String,Word> wordList = new TreeMap<>();

    private String value;
    private List<TextElement> elements = new ArrayList<>();

    /**
     * Створюємо екземпляр слова
     * Кожну літеру слова вносимо в список
     * @param value
     */
    private Word(String value) {
        this.value = value;
        for (char ch: value.toCharArray()) {
            TextElement currentElement = new Letter(ch);
            elements.add(currentElement);
        }
    }

    public static Word getWord(String value){
        if(wordList.containsKey(value))
            return wordList.get(value);
        Word word = new Word(value);
        return word;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return value != null ? value.equals(word.value) : word.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    /**
     * Повертає одне слово у вигляді списку
     * @return
     */
    @Override
    public List<TextElement> getUniqueList() {
        Set<TextElement> set = new HashSet<>();
        set.add(this);

        return set.stream().collect(Collectors.toList());
    }

    @Override
    public List<TextElement> getList() {
        return null;
    }
}
