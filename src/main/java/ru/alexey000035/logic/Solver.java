package ru.alexey000035.logic;

import ru.alexey000035.model.State;
import ru.alexey000035.model.Tube;

import java.util.*;

public class Solver {

    public static State finalState = null;

    private static class Node {
        final State state;
        final List<String> moves;

        Node(State state, List<String> moves) {
            this.state = state;
            this.moves = moves;
        }
    }

    public static boolean solve(State initialState, List<String> resultMoves) {
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(new Node(initialState, new ArrayList<>()));
        visited.add(hashState(initialState));

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (current.state.isSolved()) {
                resultMoves.addAll(current.moves);
                finalState = current.state;
                return true;
            }

            List<Tube> tubes = current.state.getTubes();
            int n = tubes.size();

            for (int i = 0; i < n; i++) {
                Tube from = tubes.get(i);
                if (from.isEmpty()) continue;

                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    Tube to = tubes.get(j);

                    if (!canPour(from, to)) continue;

                    State nextState = current.state.copy();
                    Tube nextFrom = nextState.getTubes().get(i);
                    Tube nextTo = nextState.getTubes().get(j);

                    int amount = nextFrom.countTopColor();
                    amount = Math.min(amount, nextTo.spaceLeft());

                    List<String> removed = nextFrom.popMultiple(amount);
                    nextTo.pushMultiple(removed);

                    String hash = hashState(nextState);
                    if (visited.contains(hash)) continue;
                    visited.add(hash);

                    List<String> newMoves = new ArrayList<>(current.moves);
                    newMoves.add("Перелить из колбы " + i + " в колбу " + j);

                    stack.push(new Node(nextState, newMoves));
                }
            }
        }

        return false;
    }

    private static boolean canPour(Tube from, Tube to) {
        if (from.isEmpty() || to.isFull() || from == to) return false;
        String color = from.peek();
        return to.isEmpty() || to.peek().equals(color);
    }

    private static String hashState(State state) {
        StringBuilder sb = new StringBuilder();
        for (Tube tube : state.getTubes()) {
            if (tube.isEmpty()) sb.append("EMPTY|");
            else {
                for (String c : tube.getLiquids()) sb.append(c).append(",");
                sb.append("|");
            }
        }
        return sb.toString();
    }
}
