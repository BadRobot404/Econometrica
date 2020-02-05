/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;

import static controller.Controller.em;
import javax.persistence.Query;
import model.Country;
import model.CountryDataset;

/**
 *
 * @author Bill
 */
public class ControllerCountryDataset extends Controller{
    
    public ControllerCountryDataset()
    {
        super();
    }
    public void addCountryDataset(CountryDataset cd){
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
    }
    
    public boolean isInTheDatabase(Country c){
        
        Query query = em.createNamedQuery("CountryDataset.findByCountryCode");
        query.setParameter("countryCode", c);
        
        return !query.getResultList().isEmpty();
    }
    
    @Override
    protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
