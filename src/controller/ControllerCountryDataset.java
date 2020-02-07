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
import java.util.List;
import model.CountryData;


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
        for(CountryData c : cd.getCountryDataList()){
            em.persist(c);
        }
        em.getTransaction().commit();
    }
    
    public boolean isInTheDatabase(Country c){
        
        Query query = em.createNamedQuery("CountryDataset.findByCountryCode");
        query.setParameter("countryCode", c);
        
        return !query.getResultList().isEmpty();
    }
    public List<CountryDataset> selectByCountryName(Country c){
        
        Query query = em.createNamedQuery("CountryDataset.findByCountryCode");
        query.setParameter("countryCode", c);
        
        return query.getResultList();
    }
    
    public void deleteData(){
        clearTbl("CountryDataset.deleteAll");
    }
    
    @Override
    protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
