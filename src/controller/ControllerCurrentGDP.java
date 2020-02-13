/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;


import java.util.List;
import model.CurrentGdp;


public class ControllerCurrentGDP extends Controller{

    public ControllerCurrentGDP()
    {
        super();
    }
    /**
     * 
     * @param c A list of CurrentGDP objects to be inserted to the Database
     */
    public void addCurrentGdp(List<CurrentGdp>  c){
        for(CurrentGdp cc : c){
            em.getTransaction().begin();
            em.persist(cc);
            em.getTransaction().commit();
        }
        
    }
    /**
     * Method to delete all Data in the Table
     */
    public void deleteData(){
        clearTbl("CurrentGdp.deleteAll");
    }
           
    @Override
        protected void clearTable() {
        clearTbl("CurrentGdp.deleteAll");
    }
    
}
