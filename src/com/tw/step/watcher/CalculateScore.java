package com.tw.step.watcher;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CalculateScore {
    private final Stream<String> files;

    public CalculateScore(Stream<String> files) {
        this.files = files;
    }

    public int calculateSum() throws IOException {
        AtomicInteger sum = new AtomicInteger(0);

        this.files.forEach(file -> {
            try (FileReader reader = new FileReader("./resources/" + file)) {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNext()) {
                    sum.addAndGet(Integer.parseInt(scanner.next()));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return sum.get();
    }
}
