
package co.unicauca.openmarket.presentation.commands;

import co.unicauca.openmarket.domain.Category;
import co.unicauca.openmarket.domain.service.CategoryService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan majin, freider escobar
 */
public class OMAddCategoryCommand extends OMCommand {
    
    private Category cC;
    private CategoryService cS;
    boolean result=false;
    public OMAddCategoryCommand(Category cC, CategoryService cS){
        this.cC = cC;
        this.cS = cS;
    }

    @Override
    public void make() {
        try {          
            result = cS.saveCategory(cC.getCategoryId(), cC.getName());
        } catch (Exception ex) {
            Logger.getLogger(OMAddCategoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        result = cS.deleteCategory(cC.getCategoryId());
    }
    

    
    public boolean result(){
        return result;
    }
}
