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
import java.util.Date;
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
    
    
    public JsonManager()
    {
        this.apiKey = "f3uK5mxjS8rPxPFyJ8fy";
    }
    
    /*Επιστρέφει τα στοιχεία για το ΑΕΠ χώρα*/
    public CountryDataset fetchGDP(String isoCode)
    {
        CountryDataset cd = new CountryDataset();
        
        Country ct = new Country();
        
        List<CountryData> listCountryData = new ArrayList<CountryData>();
        List<CurrentGdp> listCurrentGdp = new ArrayList<CurrentGdp>();
        ControllerCurrentGDP controllerGDP = new ControllerCurrentGDP();
        
        
        try
        {
            /*κατασκευή ενός URL για το ερώτημα JSON weather now*/
            URL url = new URL(createUrlString(isoCode,"GDP"));
            
            /*Ξεκινάει τη σύνδεση με τον server και αποθηκεύει τα δεδομένα στη ροή δεδομένων "is".*/          
            InputStream is = url.openStream(); 
            
            /*Διαβάζει τη ροή και μετατρέπει τα εισερχόμενα bytes σε χαρακτήρες.*/
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Αναλύει την αρχική δομή του json που βρίσκεται στο isr, και επιστρέφει ένα JsonElement το οποίο μπορεί να είναι
            ένα  JsonObject, JsonArray, JsonPrimitive ή ένα JsonNull.*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*εμείς γνωρίζουμε οτι είναι ένα JsonObject οπότε το αποθηκεύουμε σε μια αναφορά mainJsonObject*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
                   
            
                                
            
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
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        return cd;
    }

public CountryDataset fetchOil(String isoCode)
    {
        CountryDataset cd = new CountryDataset();
        Country ct = new Country();
        
        List<CountryData> listCountryData = new ArrayList<CountryData>();
        List<CurrentOilData> listCurrentOil = new ArrayList<CurrentOilData>();
        ControllerCurrentOilData controllerOil = new ControllerCurrentOilData();
        
        try
        {
            /*κατασκευή ενός URL για το ερώτημα JSON weather now*/
            URL url = new URL(createUrlString(isoCode,"Oil"));
            
            /*Ξεκινάει τη σύνδεση με τον server και αποθηκεύει τα δεδομένα στη ροή δεδομένων "is".*/          
            InputStream is = url.openStream(); 
            
            /*Διαβάζει τη ροή και μετατρέπει τα εισερχόμενα bytes σε χαρακτήρες.*/
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Αναλύει την αρχική δομή του json που βρίσκεται στο isr, και επιστρέφει ένα JsonElement το οποίο μπορεί να είναι
            ένα  JsonObject, JsonArray, JsonPrimitive ή ένα JsonNull.*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*εμείς γνωρίζουμε οτι είναι ένα JsonObject οπότε το αποθηκεύουμε σε μια αναφορά mainJsonObject*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
            
            
            
                       
            
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
             
             //Delete all entries in Table Current Oil
             controllerOil.deleteData();
             //Commit new Data to table Current Oil
             controllerOil.addCurrentGdp(listCurrentOil);            
                          
              
             
            
            } 
        catch (MalformedURLException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        
        return cd;
    }
        
   
    /*Κατασκευή κατάλληλου URL String για το API Request*/
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
        
        String request = prefix + isoCode + postfix + apiKey;
        
        return (request);
    }
    
    public static void setApiKey(String _apiKey)
    {
        apiKey = _apiKey;
    }
    
    public static String getApiKey()
    {
        return apiKey;
    }
    
    
}
