/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;

import static controller.Controller.em;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import model.Country;


public class ControllerCountry extends Controller{

    public ControllerCountry()
    {
        super();
    }
    
    public void addCountry(Country c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    
    public boolean isInTheDatabase(Country c){
        
        Query query = em.createNamedQuery("Country.findByIsoCode");
        query.setParameter("isoCode", c.getIsoCode());
        
        return !query.getResultList().isEmpty();
    }
    
     @Override
        protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
