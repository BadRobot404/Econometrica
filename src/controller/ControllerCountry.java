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


public class ControllerCountry extends Controller{

    public ControllerCountry()
    {
        super();
    }
    
    /**
     * 
     * @param c The Country to be added to the Database
     */
    public void addCountry(Country c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    /**
     * 
     * @param c a Country
     * @return True or False depending whether the country exists 
     *         of not in the database
     */
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
