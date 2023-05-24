/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.presentation.commands;

import co.unicauca.openmarket.domain.Product;
import co.unicauca.openmarket.domain.service.ProductService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan majin,freider escobar
 */
public class OMDeleteProductCommand extends OMCommand{
    private Long idProduct;
    private Product pP;
    private ProductService pS;
    boolean result=false;
    public OMDeleteProductCommand(Long idProduct , ProductService pS){
        this.idProduct = idProduct;
        this.pS = pS;
    }

    @Override
    public void make() {
        try {
            pP = pS.findProductById(idProduct);
            result = pS.deleteProduct(idProduct);
        } catch (Exception ex) {
            Logger.getLogger(OMDeleteProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }

    @Override
    public void unmake() {
        try {
            result = pS.saveProduct(idProduct, pP.getName(), pP.getDescription() , pP.getCategoryId());
        } catch (Exception ex) {
            Logger.getLogger(OMDeleteProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public boolean result(){
        return result;
    } 
}
