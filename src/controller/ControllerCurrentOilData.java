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
    
    public void addCurrentGdp(List<CurrentOilData>  c){
        for(CurrentOilData cc : c){
            em.getTransaction().begin();
            em.persist(cc);
            em.getTransaction().commit();
        }
        
    }
    
    public void deleteData(){
        clearTbl("CurrentOil.deleteAll");
    }
           
    @Override
        protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
