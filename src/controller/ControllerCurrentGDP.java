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
import model.CurrentGdp;


public class ControllerCurrentGDP extends Controller{

    public ControllerCurrentGDP()
    {
        super();
    }
    
    public void addCurrentGdp(List<CurrentGdp>  c){
        for(CurrentGdp cc : c){
            em.getTransaction().begin();
            em.persist(cc);
            em.getTransaction().commit();
        }
        
    }
    
    public void deleteData(){
        clearTbl("CurrentGdp.deleteAll");
    }
           
    @Override
        protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
