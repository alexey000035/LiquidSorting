package ru.alexey000035.utils;

import ru.alexey000035.model.Tube;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Tube> parseTubesFromFile(String filename) throws IOException {
        List<Tube> tubes = new ArrayList<>();
        int tubeCapacity = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    if (tubeCapacity == -1) {
                        throw new IllegalArgumentException("Пустая первая строка — нельзя определить емкость");
                    }
                    tubes.add(new Tube(tubeCapacity));
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (firstLine) {
                    tubeCapacity = parts.length;
                    firstLine = false;
                }

                List<String> colors = new ArrayList<>();
                for (int i = parts.length - 1; i >= 0; i--) {
                    colors.add(parts[i]);
                }
                tubes.add(new Tube(tubeCapacity, colors));
            }
        }

        return tubes;
    }
}
