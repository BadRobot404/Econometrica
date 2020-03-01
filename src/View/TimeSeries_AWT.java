package view;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import model.CountryDataset;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.Year; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.XYDataset; 


public class TimeSeries_AWT extends javax.swing.JFrame {
    
    String countryName;
    CountryDataset dataGDP,dataOil;

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setDataGDP(CountryDataset dataGDP) {
        this.dataGDP = dataGDP;
    }
    
/**
 * 
 * @param title the title of the Chart
 * @param countryName The Country name
 * @param dataGDP GDP data to be plotted
 * @param dataOil Oil data to be plotted
 */
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
       final JFreeChart chart = createChart( dataset, "GDP" );   
       //Add the second axis to the plot
       final XYPlot plot = chart.getXYPlot();
       final NumberAxis axis2 = new NumberAxis("Oil Consumption");
       //set number format to both axes
       axis2.setNumberFormatOverride(decimalFormat);
       NumberAxis axis1 =(NumberAxis)plot.getRangeAxis();
       axis1.setNumberFormatOverride(decimalFormat);
       //set renderer to spline
       final XYSplineRenderer renderer1 = new XYSplineRenderer();
       plot.setRenderer(0, renderer1);
       //set range of the second axis
       plot.setRangeAxis(1, axis2);
       //Create Oil timeseries from Data and add it to the chart 
       plot.setDataset(1, createDatasetOil());
       plot.mapDatasetToRangeAxis(1, 1);
       //set renderer to spline for the second plot
       final XYSplineRenderer renderer2 = new XYSplineRenderer();
       renderer2.setSeriesPaint(0, Color.blue);
       plot.setRenderer(1, renderer2);
       //set x axis format
       final DateAxis axis = (DateAxis) plot.getDomainAxis();
       axis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
       final ChartPanel chartPanel = new ChartPanel(chart);
       chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
       chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );         
       chartPanel.setMouseZoomable( true , false );         
       setContentPane( chartPanel );
   }
   /**
    * 
    * @param title title of the plot
    * @param countryName the Country name
    * @param dataGDP data to be plotted
    * @param name Type of data (GDP or Oil) present
    */
   public TimeSeries_AWT( final String title, String countryName,CountryDataset dataGDP, String name) {
       super( title );         
       this.countryName = countryName;
       this.dataGDP = dataGDP;
       this.dataOil = null;
       
        //Define the format of the numbers to be displayed
       DecimalFormat decimalFormat = new DecimalFormat("###,###.###");     
       
       //create GDP timeseries from Data
       
       final XYDataset dataset = createDatasetGDP( );         
       
       //create simple chart
       final JFreeChart chart = createChart( dataset , name);   
       //Add the second axis to the plot
       final XYPlot plot = chart.getXYPlot();

       NumberAxis axis1 =(NumberAxis)plot.getRangeAxis();
       axis1.setNumberFormatOverride(decimalFormat);
       
       //set renderer to spline
       final XYSplineRenderer renderer2 = new XYSplineRenderer();
       renderer2.setSeriesPaint(0, Color.blue);
       plot.setRenderer(0, renderer2);

       //set x axis format
       final DateAxis axis = (DateAxis) plot.getDomainAxis();
       axis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
       final ChartPanel chartPanel = new ChartPanel(chart);
       chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
       
       chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );         
       chartPanel.setMouseZoomable( true , false );         
       setContentPane( chartPanel );
   }

/**
 * 
 * @return a XYDataset containing GDP data in an appropriate format
 */      
   private XYDataset createDatasetGDP( ) {

      final TimeSeries series = new TimeSeries( "GDP" );         
      int startYear = Integer.parseInt(dataGDP.getEndYear());
      int size = dataGDP.getCountryDataList().size();
      Year current = new Year(startYear);         
      double value ;         
      
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
      double value;         
      
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

   private JFreeChart createChart( final XYDataset dataset , String title) {
      return ChartFactory.createTimeSeriesChart(             
         "Economic Data for " + countryName, 
         "Time",              
         title,              
         dataset,             
         true,              
         true,              
         false);
   }

}   