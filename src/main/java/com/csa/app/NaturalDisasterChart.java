package com.csa.app;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.scene.text.Font;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class NaturalDisasterChart extends JFrame {

    private static final long serialVersionUID = 1L;

    public NaturalDisasterChart(String title, String country) {
        super(title);
        // Create dataset
        DefaultCategoryDataset dataset = createDataset(country);
        // Create chart
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Natural Disasters in " + country + " Over Time",
                "Year",
                "Occurrences",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        
        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private DefaultCategoryDataset createDataset(String country) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, Map<String, Integer>> data = loadData(country);

        for (Map.Entry<Integer, Map<String, Integer>> entry : data.entrySet()) {
            Integer year = entry.getKey();
            Map<String, Integer> disasters = entry.getValue();
            for (Map.Entry<String, Integer> disasterEntry : disasters.entrySet()) {
                dataset.addValue(disasterEntry.getValue(), disasterEntry.getKey(), year);
            }
        }

        return dataset;
    }

    private Map<Integer, Map<String, Integer>> loadData(String country) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<Integer, Map<String, Integer>> data = new HashMap<>();
        String url = "jdbc:sqlite:disasters.db";
        String query = "SELECT year, disasterType, COUNT(*) as occurrences FROM disasters WHERE country = ? GROUP BY year, disasterType ORDER BY year";

        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, country);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int year = rs.getInt("year");
                    String disasterType = rs.getString("disasterType");
                    int occurrences = rs.getInt("occurrences");

                    System.out.println(year + " " + disasterType + " " + occurrences);

                    data.computeIfAbsent(year, k -> new HashMap<>()).put(disasterType, occurrences);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from database", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String country = JOptionPane.showInputDialog("Enter country name:");
            if (country != null && !country.trim().isEmpty()) {
                NaturalDisasterChart chart = new NaturalDisasterChart("Natural Disaster Analysis", country.trim());
                chart.setSize(800, 600);
                chart.setLocationRelativeTo(null);
                chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chart.setVisible(true);
            }
        });
    }
}
