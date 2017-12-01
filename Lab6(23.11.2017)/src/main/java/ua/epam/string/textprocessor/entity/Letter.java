package ua.epam.string.textprocessor.entity;

import java.util.Iterator;
import java.util.List;

/**
 * Буква
 */
public class Letter extends TextElement {

    private Character value;

    public Letter(Character value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public List<TextElement> getUniqueList() {
        return null;
    }

    @Override
    public List<TextElement> getList() {
        return null;
    }
}
