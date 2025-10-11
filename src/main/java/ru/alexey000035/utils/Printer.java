package ru.alexey000035.utils;

import ru.alexey000035.model.State;
import ru.alexey000035.model.Tube;

import java.util.List;

public class Printer {
    public static void printState(State state) {
        List<Tube> tubes = state.getTubes();
        for (int i = 0; i < tubes.size(); i++) {
            System.out.println("Пробирка " + i + ": " + tubes.get(i).getLiquids());
        }
    }
}
