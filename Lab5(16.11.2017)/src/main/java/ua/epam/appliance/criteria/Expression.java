package ua.epam.appliance.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * набір умов для фільтрування даних
 * Застосовуються всі за правилом ТА
 */
public class Expression  implements Iterable<Condition>{
    private final List<Condition> list = new ArrayList<>();

    public void add(Condition condition){
        list.add(condition);
    }

    public void remove(Condition condition){
        list.remove(condition);
    }

    public int size(){
        return list.size();
    }

    public Condition get(int id){
        return list.get(id);
    }


    @Override
    public Iterator<Condition> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
