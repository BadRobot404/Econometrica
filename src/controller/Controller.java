package controller;
/*γενική κλάση διαχείρησης των entities*/

import javax.persistence.EntityManager;
import javax.persistence.Query;
import remote.DbConnector;

/** 
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */

public abstract class Controller
{
    
    protected static EntityManager em;
    
    public Controller()
    {
        if (em == null)
        {
            /*Make a database connection, create an entity factory and an entity manager*/
            DbConnector.connect();
            em = DbConnector.getEm();
        }
    }
    
    //Method to delete a table though a namedQuery.
    /**
     * 
     * @param namedQuery The Query to be executed
     */
    protected void clearTbl(String namedQuery)
    {
        try 
        { 
            em.getTransaction().begin();
            Query query1 = em.createNamedQuery(namedQuery);
            query1.executeUpdate();
            em.getTransaction().commit();
        } 
        catch (Exception e) 
        { 
            em.getTransaction().rollback();
        }  
    }
    
    protected abstract void clearTable();
}


