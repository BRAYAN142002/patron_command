/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.presentation.commands;


import co.unicauca.openmarket.domain.Category;
import co.unicauca.openmarket.domain.service.CategoryService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan majin, freider escobar
 */
public class OMEditCategoryCommand extends OMCommand{
    
    private Category cC, backupCategory;
    private CategoryService cS;
    boolean result=false;
    public OMEditCategoryCommand(Category cC, CategoryService cS){
        this.cC = cC;
        this.cS = cS;
    }

    @Override
    public void make() {
        try { 
            backupCategory = cS.findCategoryById(cC.getCategoryId());
            result = cS.editCategory(cC.getCategoryId(), cC);
        } catch (Exception ex) {
            Logger.getLogger(OMAddCategoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        try {
            result = cS.editCategory(cC.getCategoryId(),backupCategory);
        } catch (Exception ex) {
            Logger.getLogger(OMDeleteCategoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public boolean result(){
        return result;
    }
}
