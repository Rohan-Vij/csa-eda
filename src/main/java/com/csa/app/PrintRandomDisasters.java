package com.csa.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintRandomDisasters {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String url = "jdbc:sqlite:disasters.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            List<DisasterRecord> randomRecords = getRandomRecords(conn, 5);
            for (DisasterRecord record : randomRecords) {
                System.out.println(record);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<DisasterRecord> getRandomRecords(Connection conn, int numberOfRows) throws SQLException {
        List<DisasterRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM disasters ORDER BY RANDOM() LIMIT " + numberOfRows;

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                DisasterRecord record = new DisasterRecord(
                    rs.getInt("year"), rs.getString("seq"), rs.getString("glide"), rs.getString("disasterGroup"),
                    rs.getString("disasterSubgroup"), rs.getString("disasterType"), rs.getString("disasterSubtype"),
                    rs.getString("disasterSubsubtype"), rs.getString("eventName"), rs.getString("country"),
                    rs.getString("iso"), rs.getString("region"), rs.getString("continent"), rs.getString("location"),
                    rs.getString("origin"), rs.getString("associatedDis"), rs.getString("associatedDis2"),
                    rs.getString("ofdaResponse"), rs.getString("appeal"), rs.getString("declaration"),
                    rs.getString("aidContribution"), rs.getString("disMagValue"), rs.getString("disMagScale"),
                    rs.getString("latitude"), rs.getString("longitude"), rs.getString("localTime"),
                    rs.getString("riverBasin"), rs.getInt("startYear"), rs.getInt("startMonth"), rs.getInt("startDay"),
                    rs.getInt("endYear"), rs.getInt("endMonth"), rs.getInt("endDay"), rs.getInt("totalDeaths"),
                    rs.getInt("noInjured"), rs.getInt("noAffected"), rs.getInt("noHomeless"), rs.getInt("totalAffected"),
                    rs.getInt("insuredDamages"), rs.getInt("totalDamages"), rs.getDouble("cpi"), rs.getString("admLevel"),
                    rs.getString("admin1Code"), rs.getString("admin2Code"), rs.getString("geoLocations")
                );
                records.add(record);
            }
        }
        return records;
    }
}
