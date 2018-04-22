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
 * Classe récupérée de http://www.java2s.com/Code/Java/Chart/JFreeChartPieChartDemo1.html et modifiée
 * Cette classe crée un camembert contenant un titre et les informations sur la répartion du nombre
 * de médecins par spécialité
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
     * Creates a sample dataset.On récupère un objet Statistiques qui contient 2 arraylist
     * avec les spécialités des médecins et le nombre de médecins par spécialité
     * On crée autant de dataset qu'il y a de spé
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
     * Crée un graphique avec un titre, les dataset et une légende
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
     * Crée un panel à retourner pour pouvoir être affiché
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel(){
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
}
