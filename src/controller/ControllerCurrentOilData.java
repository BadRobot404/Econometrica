/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;

import java.util.List;
import model.CurrentOilData;


public class ControllerCurrentOilData extends Controller{

    public ControllerCurrentOilData()
    {
        super();
    }
    /**
     * 
     * @param c a list of CyrrentOilData to be inserted into the Database
     */
    public void addCurrentOil(List<CurrentOilData>  c){
        for(CurrentOilData cc : c){
            em.getTransaction().begin();
            em.persist(cc);
            em.getTransaction().commit();
        }
        
    }
    /**
     * A method to delete all data in the Table
     */
    public void deleteData(){
        clearTbl("CurrentOil.deleteAll");
    }
           
    @Override
        protected void clearTable() {
        clearTbl("CurrentOil.deleteAll");
    }
    
}
