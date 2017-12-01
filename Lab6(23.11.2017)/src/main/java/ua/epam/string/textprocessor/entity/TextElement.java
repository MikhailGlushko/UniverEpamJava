package ua.epam.string.textprocessor.entity;

import java.util.List;

public abstract class TextElement{
    abstract public List<TextElement> getUniqueList();
    abstract public List<TextElement> getList();
}
