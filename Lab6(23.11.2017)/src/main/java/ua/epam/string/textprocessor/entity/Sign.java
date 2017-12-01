package ua.epam.string.textprocessor.entity;

import java.util.List;

/**
 * Розділовий знак
 */
public class Sign extends TextElement {
    private Character value;

    public Sign(Character value) {
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
