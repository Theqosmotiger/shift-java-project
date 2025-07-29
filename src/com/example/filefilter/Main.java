package com.example.filefilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String outputPath = null;
        String prefix = null;
        boolean append = false;
        boolean shortStats = false;
        boolean fullStats = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-o":
                    if (i + 1 < args.length) {
                        outputPath = args[i + 1];
                        i++;
                    } else {
                        System.err.println("Ошибка: отсутствует путь после опции -o");
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[i + 1];
                        i++;
                    } else {
                        System.err.println("Ошибка: отсутствует префикс после опции -p");
                    }
                    break;
                case "-a":
                    append = true;
                    break;
                case "-s":
                    shortStats = true;
                    break;
                case "-f":
                    fullStats = true;
                    break;
                default:
                    // Считаем, что это имя файла, только если это не опция и не значение опции
                    if (!arg.startsWith("-") && !arg.equals(outputPath) && !arg.equals(prefix)) {
                        System.out.println("Обработка файла: " + arg);
                        // Чтение файла
                        try (BufferedReader reader = new BufferedReader(new FileReader(arg))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println("  Строка из файла: " + line);
                            }
                        } catch (IOException e) {
                            System.err.println("Ошибка при чтении файла " + arg + ": " + e.getMessage());
                        }
                    }
                    break;
            }
        }

        System.out.println("outputPath: " + outputPath);
        System.out.println("prefix: " + prefix);
        System.out.println("append: " + append);
        System.out.println("shortStats: " + shortStats);
        System.out.println("fullStats: " + fullStats);
    }
}