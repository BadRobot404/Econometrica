/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.CountryData;


public class ControllerCountryData extends Controller{

    public ControllerCountryData()
    {
        super();
    }
    
    public void addCountryData(List<CountryData>  c){
        for(CountryData cc : c){
            em.getTransaction().begin();
            em.persist(cc);
            em.getTransaction().commit();
        }
        
    }
    
           
    @Override
        protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
