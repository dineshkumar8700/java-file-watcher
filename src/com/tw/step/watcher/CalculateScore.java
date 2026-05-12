package com.tw.step.watcher;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CalculateScore {
    private final String name;

    public CalculateScore(String name) {
        this.name = name;
    }

    public int calculate() throws IOException {
        int sum = 0;

        try (FileReader reader = new FileReader("./resources/" + this.name)) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                sum += Integer.parseInt(scanner.next());
            }

        }

        return sum;
    }
}
