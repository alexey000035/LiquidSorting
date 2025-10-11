package ru.alexey000035.model;

import java.util.*;

public class State {
    private final List<Tube> tubes;

    public State(List<Tube> tubes) {
        this.tubes = tubes;
    }

    public List<Tube> getTubes() {
        return tubes;
    }

    public boolean isSolved() {
        for (Tube tube : tubes) {
            if(tube.isEmpty()) continue;;

            String color = tube.peek();
            for (String drop : tube.getLiquid()) {
                if (!color.equals(drop)) return false;
            }
        }

        Set<String> seenColors = new HashSet<>();
        for (Tube tube : tubes) {
            if (tube.isEmpty()) continue;

            String color = tube.peek();
            if (seenColors.contains(color)) return false;
            seenColors.add(color);
        }

        return true;
    }

    public State copy() {
        List<Tube> newTubes = new ArrayList<>();
        for (Tube tube : tubes) {
            newTubes.add(tube.copy());
        }
        return new State(newTubes);
    }
}
