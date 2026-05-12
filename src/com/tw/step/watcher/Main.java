package com.tw.step.watcher;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static void main() throws IOException {
        int sum = 0;

        try (FileReader fileReader = new FileReader("./resources/score_test.txt")) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNext()) {
                sum += Integer.parseInt(scanner.next());
            }

            System.out.println("Sum : " + sum);

        }
    }
}
