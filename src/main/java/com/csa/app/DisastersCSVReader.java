package com.csa.app;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DisastersCSVReader {
    public static void main(String[] args) {
        String fileName = "./src/main/resources/disasters.csv";
        int maxBatchSize = 100; // Adjust batch size as needed

        try {
            // Configure input format usinguni
            CsvParserSettings settings = new CsvParserSettings();
            settings.getFormat().setLineSeparator("\r\n");

            CsvParser parser = new CsvParser(settings);
            Iterator<String[]> iterator = parser.iterate(new File(fileName), "UTF-8").iterator();

            List<String[]> batch = new ArrayList<>(maxBatchSize);
            while (iterator.hasNext()) {
                batch.add(iterator.next());

                if (batch.size() == maxBatchSize) {
                    printAndClearBatch(batch);
                }
            }

            if (!batch.isEmpty()) {
                printAndClearBatch(batch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printAndClearBatch(List<String[]> batch) {
        for (String[] row : batch) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        batch.clear();
    }
}
