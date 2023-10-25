/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Anthony
 */
public class Graphs {
        
    public static ChartPanel displayLaGraph(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(1,"","premier");
        dataset.setValue(7,"","deuxième");
        dataset.setValue(5,"","troisième");
        dataset.setValue(2,"","quatrième");
        
        JFreeChart chart = ChartFactory.createBarChart("","", "", dataset, PlotOrientation.HORIZONTAL, false, false, false);
        CategoryPlot catPlot =chart.getCategoryPlot();
        catPlot.setRangeGridlinePaint(Color.BLACK);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(new EmptyBorder(new Insets(10, 10,10, 10)));
        return chartPanel;
    }
    
    public static ChartPanel displayPieGraph(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("premier", 1);
        dataset.setValue("deuxième", 2);
        dataset.setValue("troisième", 7);
        dataset.setValue("quatrième", 3);
        
        JFreeChart chart = ChartFactory.createPieChart("pie chart", dataset, true, true, false);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(new EmptyBorder(new Insets(10, 10,10, 10)));
        return chartPanel;
    }
    
    public static ChartPanel displayLineGraph(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(1,"","premier");
        dataset.setValue(7,"","deuxième");
        dataset.setValue(5,"","troisième");
        dataset.setValue(2,"","quatrième");
        
        JFreeChart chart = ChartFactory.createLineChart("","", "", dataset, PlotOrientation.VERTICAL, false, false, false);
        CategoryPlot catPlot =chart.getCategoryPlot();
        catPlot.setRangeGridlinePaint(Color.BLACK);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(new EmptyBorder(new Insets(10, 10,10, 10)));
        return chartPanel;
    }
    
}
