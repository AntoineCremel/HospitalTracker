/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;   

import controler.Reporting;
import controler.container.Statistiques;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import modele.Connexion;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Jean
 */
public class Camembert  {
    private String title;
    private static Connexion connex;
    /**
     * Default constructor.
     *
     * @param title  the frame title.
     * @param connex
     */
    public Camembert (String title, Connexion connex) {
        this.title = title;
        this.connex = connex;
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private static PieDataset createDataset(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        ArrayList<String> docteur = new ArrayList();
        ArrayList<String> nombre = new ArrayList();
        Statistiques stat = new Statistiques();
        try {
            stat = Reporting.repartitionSpecialitesDocteurs(connex);
        } catch (SQLException ex) {
            Logger.getLogger(Camembert.class.getName()).log(Level.SEVERE, null, ex);
        }
        docteur = stat.getElement(1);
        nombre = stat.getElement(0);
        for(int i=0; i<docteur.size();i++){
            double j;
            String test = docteur.get(i);
            String nbr = nombre.get(i);
            j = Double.parseDouble(nbr);
            System.out.println(test);
            System.out.println(j);
            dataset.setValue(test,j);
        }
        
        /*dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));*/
        return dataset;        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 1",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }
    
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel(){
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
}
