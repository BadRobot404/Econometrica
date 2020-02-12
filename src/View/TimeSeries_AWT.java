package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CountryDataset;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.Year; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ShapeUtilities;

public class TimeSeries_AWT extends javax.swing.JFrame {
    
    String countryName;
    CountryDataset dataGDP,dataOil;

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setDataGDP(CountryDataset dataGDP) {
        this.dataGDP = dataGDP;
    }
    

   public TimeSeries_AWT( final String title, String countryName,CountryDataset dataGDP,CountryDataset dataOil) {
       super( title );         
       this.countryName = countryName;
       this.dataGDP = dataGDP;
       this.dataOil = dataOil;
       
        //Define the format of the numbers to be displayed
       DecimalFormat decimalFormat = new DecimalFormat("###,###.###");     
       
       //create GDP timeseries from Data
       
       final XYDataset dataset = createDatasetGDP( );         
       
       //create simple chart
       final JFreeChart chart = createChart( dataset );   
       //Add the second axis to the plot
       final XYPlot plot = chart.getXYPlot();
       final NumberAxis axis2 = new NumberAxis("Oil Consumption");
       //set number format to both axes
       axis2.setNumberFormatOverride(decimalFormat);
       NumberAxis axis1 =(NumberAxis)plot.getRangeAxis();
       axis1.setNumberFormatOverride(decimalFormat);
       //set range of the second axis
       plot.setRangeAxis(1, axis2);
       //Create Oil timeseries from Data and add it to the chart 
       plot.setDataset(1, createDatasetOil());
       plot.mapDatasetToRangeAxis(1, 1);

     
       final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
       renderer2.setSeriesPaint(0, Color.blue);
       renderer2.setSeriesShape(0,  new Ellipse2D.Double(-3, -3, 6, 6));
       plot.setRenderer(1, renderer2);


       final DateAxis axis = (DateAxis) plot.getDomainAxis();
       axis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
       final ChartPanel chartPanel = new ChartPanel(chart);
       chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
       
       chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );         
       chartPanel.setMouseZoomable( true , false );         
       setContentPane( chartPanel );
   }

      
   private XYDataset createDatasetGDP( ) {

      final TimeSeries series = new TimeSeries( "GDP" );         
      int startYear = Integer.parseInt(dataGDP.getEndYear());
      int size = dataGDP.getCountryDataList().size();
      Year current = new Year(startYear);         
      double value = 100.0;         
      
      for (int i = 0; i < size; i++) {
         
         try {
            value = Double.parseDouble(dataGDP.getCountryDataList().get(i).getValue());                 
            series.add(current, new Double( value ) );                 
            current = ( Year ) current.previous( ); 
         } catch ( SeriesException e ) {
            System.err.println("Error adding to series");
         }
      }
      
      return new TimeSeriesCollection(series);
   }     
   private XYDataset createDatasetOil( ) {

      final TimeSeries series = new TimeSeries( "Oil" );         
      int startYear = Integer.parseInt(dataOil.getEndYear());
      int size = dataOil.getCountryDataList().size();
      Year current = new Year(startYear);         
      double value = 100.0;         
      
      for (int i = 0; i < size; i++) {
         
         try {
            value = Double.parseDouble(dataOil.getCountryDataList().get(i).getValue());                 
            series.add(current, new Double( value ) );                 
            current = ( Year ) current.previous( ); 
         } catch ( SeriesException e ) {
            System.err.println("Error adding to series");
         }
      }
      
      return new TimeSeriesCollection(series);
   }     

   private JFreeChart createChart( final XYDataset dataset ) {
      return ChartFactory.createTimeSeriesChart(             
         "Economic Data for " + countryName, 
         "Time",              
         "GDP",              
         dataset,             
         true,              
         true,              
         false);
   }

   public static void main( final String[ ] args ) {
      final String title = "Time Series Management";         
      //final TimeSeries_AWT demo = new TimeSeries_AWT( title, "bla bla");         
      //demo.pack( );         
      //RefineryUtilities.positionFrameRandomly( demo );         
      //demo.setVisible( true );
   }
}   