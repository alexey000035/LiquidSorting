package ru.alexey000035.logic;

import ru.alexey000035.model.Tube;

public class GameRules {
    public static boolean canPour(Tube from, Tube to) {
        if (from.isEmpty() || to.isFull() || from == to) return false;
        String color = from.peek();
        return to.isEmpty() || to.peek().equals(color);
    }

    public static int maxPourable(Tube from, Tube to) {
        if (!canPour(from, to)) return 0;
        int topCount = from.countTopColor();
        return Math.min(topCount, to.spaceLeft());
    }
}
