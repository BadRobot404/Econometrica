package remote;
/*Κλάση δημιουργίας σύνδεσης στη βάση δεδομένων*/

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/** 
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛEΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */

public final class DbConnector
{
    private static final String PERSISTENCE_UNIT_NAME = "EconometricaPU";
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    //Messages
    private static final String errDBConn = "Αποτυχία σύνδεσης με τη Βάση Δεδομένων!";
    
    public static void connect()
    {
        if (emf == null)
        {
            try 
            {
                //Construction of an Entity Manager to be used in the application.
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                em = emf.createEntityManager(); 
            } 
            catch(Exception e) 
            {
                System.out.println(e); 
                JOptionPane.showMessageDialog(null, errDBConn, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static EntityManager getEm()
    {
        return em;
    }
}
