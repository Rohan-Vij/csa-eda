package com.csa.app;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmissionsCSVReader {
    public static void main(String[] args) {
        String fileName = "./src/main/resources/wastewater.csv";
        int maxLines = 100;

        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CSVReader csvReader = new CSVReader(reader);

            ColumnPositionMappingStrategy<EmissionRecord> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(EmissionRecord.class);
            String[] memberFieldsToBindTo = {"sourceId", "iso3Country", "originalInventorySector", "startTime", "endTime", "temporalGranularity", "gas", "emissionsQuantity", "emissionsFactor", "emissionsFactorUnits", "capacity", "capacityUnits", "capacityFactor", "activity", "activityUnits", "createdDate", "modifiedDate", "sourceName", "sourceType", "lat", "lon", "other1", "other2", "other3", "other4", "other1Def", "other2Def", "other3Def", "other4Def", "geometryRef"};
            strategy.setColumnMapping(memberFieldsToBindTo);

            CsvToBean<EmissionRecord> csvToBean = new CsvToBeanBuilder<EmissionRecord>(csvReader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmissionRecord> emissionRecords = new ArrayList<>();
            int lineCount = 0;
            for (EmissionRecord record : csvToBean) {
                emissionRecords.add(record);
                lineCount++;
                if (lineCount >= maxLines) {
                    break;
                }
            }

            emissionRecords.forEach(System.out::println);

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

