package com.csa.app;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DisastersCSVToDB {
    public static void main(String[] args) {
        String fileName = "./src/main/resources/disasters.csv";
        int maxBatchSize = 100;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Initializing db connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:disasters.db");
            Statement stmt = conn.createStatement();
            stmt.setQueryTimeout(30);

            stmt.executeUpdate("DROP TABLE IF EXISTS disasters");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS disasters (" +
                                    "year INTEGER, " +
                                    "seq TEXT, " +
                                    "glide TEXT, " +
                                    "disasterGroup TEXT, " +
                                    "disasterSubgroup TEXT, " +
                                    "disasterType TEXT, " +
                                    "disasterSubtype TEXT, " +
                                    "disasterSubsubtype TEXT, " +
                                    "eventName TEXT, " +
                                    "country TEXT, " +
                                    "iso TEXT, " +
                                    "region TEXT, " +
                                    "continent TEXT, " +
                                    "location TEXT, " +
                                    "origin TEXT, " +
                                    "associatedDis TEXT, " +
                                    "associatedDis2 TEXT, " +
                                    "ofdaResponse TEXT, " +
                                    "appeal TEXT, " +
                                    "declaration TEXT, " +
                                    "aidContribution TEXT, " +
                                    "disMagValue TEXT, " +
                                    "disMagScale TEXT, " +
                                    "latitude TEXT, " +
                                    "longitude TEXT, " +
                                    "localTime TEXT, " +
                                    "riverBasin TEXT, " +
                                    "startYear INTEGER, " +
                                    "startMonth INTEGER, " +
                                    "startDay INTEGER, " +
                                    "endYear INTEGER, " +
                                    "endMonth INTEGER, " +
                                    "endDay INTEGER, " +
                                    "totalDeaths INTEGER, " +
                                    "noInjured INTEGER, " +
                                    "noAffected INTEGER, " +
                                    "noHomeless INTEGER, " +
                                    "totalAffected INTEGER, " +
                                    "insuredDamages INTEGER, " +
                                    "totalDamages INTEGER, " +
                                    "cpi REAL, " +
                                    "admLevel TEXT, " +
                                    "admin1Code TEXT, " +
                                    "admin2Code TEXT, " +
                                    "geoLocations TEXT" +
                                    ")";
            stmt.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }



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
                    insertAndClearBatch(conn, batch);
                }
            }

            if (!batch.isEmpty()) {
                insertAndClearBatch(conn, batch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertAndClearBatch(Connection conn, List<String[]> batch) {
        String insertSQL = "INSERT INTO disasters (year, seq, glide, disasterGroup, disasterSubgroup, disasterType, disasterSubtype, disasterSubsubtype, eventName, country, iso, region, continent, location, origin, associatedDis, associatedDis2, ofdaResponse, appeal, declaration, aidContribution, disMagValue, disMagScale, latitude, longitude, localTime, riverBasin, startYear, startMonth, startDay, endYear, endMonth, endDay, totalDeaths, noInjured, noAffected, noHomeless, totalAffected, insuredDamages, totalDamages, cpi, admLevel, admin1Code, admin2Code, geoLocations) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            for (String[] row : batch) {
                for (int i = 0; i < row.length; i++) {
                    pstmt.setString(i + 1, row[i]);
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        batch.clear();
    }
}
