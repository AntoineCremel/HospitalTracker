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
        for(int i=0; i<stat.size();i++){
            dataset.setValue(stat.getCategorie(i),stat.getNumber(i));
        }
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
            "Répartition des médecins par catégorie",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.BOLD, 13));
        plot.setNoDataMessage("No data available");
        plot.setCircular(Boolean.TRUE);
        plot.setLabelGap(0.05);
        return chart;
        
    }
    
    /**
     * Créer un panel à retourner
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel(){
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
}
