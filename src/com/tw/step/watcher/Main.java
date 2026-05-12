package com.tw.step.watcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;
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

        System.out.println("Watching scores...");


        while (true) {
            AtomicInteger finalSum = new AtomicInteger();
            try (Stream<Path> list = Files.list(path)) {
                list.filter((l) -> l.getFileName().toString().startsWith("score_") && l.getFileName().toString().endsWith(".txt"))
                        .forEach((f) -> {
                            CalculateScore calculateScore = new CalculateScore(f.getFileName().toString());
                            try {
                                finalSum.addAndGet(calculateScore.calculate());
                            } catch (IOException e) {
                                System.out.println("File not found :" + e.getMessage());
                            }

                        });
            }
            System.out.println("Sum: " + finalSum);


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