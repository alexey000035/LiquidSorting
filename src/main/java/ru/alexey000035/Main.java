package ru.alexey000035;

import ru.alexey000035.logic.Solver;
import ru.alexey000035.model.State;
import ru.alexey000035.model.Tube;
import ru.alexey000035.utils.Parser;
import ru.alexey000035.utils.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Введите название файла");
            return;
        }

        String filename = args[0];

        try {
            List<Tube> tubes = Parser.parseTubesFromFile(filename);
            State initialState = new State(tubes);

            System.out.println("Начальное состояние:");
            Printer.printState(initialState);

            List<String> moves = new ArrayList<>();
            boolean solved = Solver.solve(initialState, moves);

            if (solved) {
                System.out.println("Решено!");
                Printer.printMoves(moves);
                Printer.printState(Solver.finalState);
            } else {
                System.out.println("Решения не найдено");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}