package ru.alexey000035.model;

import java.util.ArrayList;
import java.util.List;

public class Tube {
    private final int capacity;
    private final List<String> liquids;

    public Tube(int capacity) {
        this.capacity = capacity;
        this.liquids = new ArrayList<>();
    }

    public Tube(int capacity, List<String> liquids) {
        this.capacity = capacity;
        this.liquids = new ArrayList<>(liquids);
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getLiquids() {
        return liquids;
    }

    public boolean isEmpty() {
        return liquids.isEmpty();
    }

    public boolean isFull() {
        return liquids.size() == capacity;
    }

    public String peek() {
        if (isEmpty()) return null;
        return liquids.get(liquids.size() - 1);
    }

    public int countTopColor() {
        if (isEmpty()) return 0;
        String color = peek();
        int count = 0;
        for (int i = liquids.size() - 1; i >= 0; i--) {
            if(!liquids.get(i).equals(color)) break;
            count++;
        }
        return count;
    }

    public int spaceLeft() {
        return capacity - liquids.size();
    }

    public Tube copy() {
        return new Tube(this.capacity, new ArrayList<>(this.liquids));
    }
}
