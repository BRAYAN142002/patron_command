package co.unicauca.openmarket.presentation.commands;


import co.unicauca.openmarket.domain.Product;
import co.unicauca.openmarket.domain.service.ProductService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan majin, freider escobar
 */
public class OMAddProductCommand extends OMCommand{
    private Product pP;
    private ProductService pS;
    boolean result=false;
    public OMAddProductCommand(Product pP, ProductService pS){
        this.pP = pP;
        this.pS = pS;
    }
    
    
    @Override
    public void make() {
        try {
            result = pS.saveProduct(pP.getProductId(), pP.getName(), pP.getDescription(),pP.getCategoryId());
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        try {
            result = pS.deleteProduct(pP.getProductId());
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean result(){
        return result;
    }
}
