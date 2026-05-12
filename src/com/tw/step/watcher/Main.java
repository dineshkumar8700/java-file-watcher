package com.tw.step.watcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class Main {
    public static void main() throws IOException, InterruptedException {
        Path path = Paths.get("./resources");
        WatchService watchService = FileSystems.getDefault().newWatchService();

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );

        while (true) {
            int finalSum = 0 ;
            try (Stream<Path> list = Files.list(path)) {
                Stream<String> files = list.filter((l) -> l.getFileName().toString().startsWith("score_") && l.getFileName().toString().endsWith(".txt"))
                        .map((f) -> f.getFileName().toString());

                CalculateScore calculateScore = new CalculateScore(files);
                finalSum = calculateScore.calculateSum();
                System.out.println("Sum: " + finalSum);
            }


            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                Path fileName = (Path) event.context();
                String name = fileName.toString();
            }


            boolean valid = key.reset();
            if (!valid) break;

        }
    }
}