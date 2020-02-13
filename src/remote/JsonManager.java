package remote;
/*Κλάση κλήσης API του quandl και διαχείρησης δομής δεδομένων JSON*/

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import controller.*;
import java.util.List;


/** 
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛEΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */

public class JsonManager
{
    private static String apiKey;
    
    //Constructor 
    public JsonManager()
    {
        this.apiKey = "f3uK5mxjS8rPxPFyJ8fy";
    }
    
    /*Makes an API Call and parses GDP Data*/
    /**
     * @param c Country 
     * @return GDP Data for the Country c in a CountryDataset Object format
     */
    public CountryDataset fetchGDP(Country c)
    {
        CountryDataset cd = new CountryDataset();
               
        List<CountryData> listCountryData = new ArrayList<CountryData>();
        List<CurrentGdp> listCurrentGdp = new ArrayList<CurrentGdp>();
        ControllerCurrentGDP controllerGDP = new ControllerCurrentGDP();
                
        try
        {
            /*Construction of URL to be used for the API Call*/
            URL url = new URL(createUrlString(c.getIsoCode(),"GDP"));
            
            /*Make a connection and save data to stream is */          
            InputStream is = url.openStream(); 
            
            /*Read stream */
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Parse read data as a JSON Element*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*Get the main JSON Object to be parsed*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
                   
            //Get the root of the tree as a starting point to the parsing            
             mainJsonObject = mainJsonObject.get("dataset").getAsJsonObject();
             //Parse the name of the dataset
             cd.setName(mainJsonObject.get("name").getAsString());
             //Parse the start date of the dataset
             cd.setStartYear(mainJsonObject.get("start_date").getAsString().substring(0, 4));
             //Parse the end date of the dataset
             cd.setEndYear(mainJsonObject.get("end_date").getAsString().substring(0, 4));
             //Set the foreign key 
             cd.setCountryCode(c);
             // Parse the Description
             cd.setDescription(mainJsonObject.get("description").getAsString());
             
             //Parse Dataset entries
             for (JsonElement e : mainJsonObject.get("data").getAsJsonArray()){
                 
                 //Parse Country Data entry
                 CountryData cData = new CountryData();
                 cData.setDataYear(e.getAsJsonArray().get(0).getAsString().substring(0, 4));
                 cData.setValue(e.getAsJsonArray().get(1).getAsString());    
                 cData.setDataset(cd);
                 listCountryData.add(cData);
                 
                 //Update table that stores current GDP
                 CurrentGdp cGdp = new CurrentGdp();
                 cGdp.setDataYear(e.getAsJsonArray().get(0).getAsString().substring(0, 4));
                 cGdp.setValue(e.getAsJsonArray().get(1).getAsString());
                 listCurrentGdp.add(cGdp);
            }
             cd.setCountryDataList(listCountryData);
             
             //Delete all entries in table currentGDP  
             controllerGDP.deleteData();
             //Commint new data to table CurrentGDP
             controllerGDP.addCurrentGdp(listCurrentGdp);
            
            } 
        catch (MalformedURLException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            controllerGDP.deleteData();
            return new CountryDataset();
        }

        return cd;
    }

public CountryDataset fetchOil(Country ct)
    {
        CountryDataset cd = new CountryDataset();
                
        List<CountryData> listCountryData = new ArrayList<CountryData>();
        List<CurrentOilData> listCurrentOil = new ArrayList<CurrentOilData>();
        ControllerCurrentOilData controllerOil = new ControllerCurrentOilData();
        
        try
        {
            /*Construction of URL to be used for the API Call*/
            URL url = new URL(createUrlString(ct.getIsoCode(),"Oil"));
            
            /*Make a connection and save data to stream is */          
            InputStream is = url.openStream(); 
            
            /*Read stream */
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Parse read data as a JSON Element*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*Get the main JSON Object to be parsed*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
            
            //Get the root of the tree as a starting point to the parsing                       
             mainJsonObject = mainJsonObject.get("dataset").getAsJsonObject();
             //Parse the name of the dataset
             cd.setName(mainJsonObject.get("name").getAsString());
             //Parse the start date of the dataset
             cd.setStartYear(mainJsonObject.get("start_date").getAsString().substring(0, 4));
             //Parse the end date of the dataset
             cd.setEndYear(mainJsonObject.get("end_date").getAsString().substring(0, 4));
             //Set the foreign key 
             cd.setCountryCode(ct);
             // Parse the Description
             cd.setDescription(mainJsonObject.get("description").getAsString());
             
             for (JsonElement e : mainJsonObject.get("data").getAsJsonArray()){
                //Parse Data entry 
                 CountryData cData = new CountryData();
                 cData.setDataYear(e.getAsJsonArray().get(0).getAsString().substring(0, 4));
                 cData.setValue(e.getAsJsonArray().get(1).getAsString());    
                 cData.setDataset(cd);
                 listCountryData.add(cData);
                 //Update Table Current Oil
                 CurrentOilData cOil = new CurrentOilData();
                 cOil.setDataYear(e.getAsJsonArray().get(0).getAsString().substring(0, 4));
                 cOil.setValue(e.getAsJsonArray().get(1).getAsString());
                 listCurrentOil.add(cOil);
             }
             cd.setCountryDataList(listCountryData);
             
             //Delete all entries in Table Current Oil
             controllerOil.deleteData();
             //Commit new Data to table Current Oil
             controllerOil.addCurrentOil(listCurrentOil);            
                        
            } 
        catch (MalformedURLException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            controllerOil.deleteData();
            return new CountryDataset();
        }
      
        return cd;
    }
        
   
    /*Construct URL for API Request*/
    /**
     * 
     * @param isoCode A string containing the alpha 3 iso code of the Country
     * @param type A string defining the type of data, GDP or Oil to be requested
     * @return A string containing the URL to be used in the API Call
     */
    private String createUrlString(String isoCode,String type)
    {
        String prefix = "";
        String postfix = "";
        if(type.equals("GDP")){
            prefix = "https://www.quandl.com/api/v3/datasets/WWDI/";
            postfix = "_NY_GDP_MKTP_CN.json?api_key=";
        }else if(type.equals("Oil")){
            prefix = "https://www.quandl.com/api/v3/datasets/BP/OIL_CONSUM_";
            postfix = ".json?api_key=";
        }
                
        return (prefix + isoCode + postfix + apiKey);
    }
    
    /*Setters and Getters*/
    
    public static void setApiKey(String _apiKey)
    {
        apiKey = _apiKey;
    }
    
    public static String getApiKey()
    {
        return apiKey;
    }
    
    
}
