/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.main;

import co.unicauca.openmarket.access.Factory;
import co.unicauca.openmarket.access.ICategoryRepository;
import co.unicauca.openmarket.access.IProductRepository;


import co.unicauca.openmarket.domain.service.CategoryService;
import co.unicauca.openmarket.domain.service.ProductService;
import co.unicauca.openmarket.presentation.GUICategoriesFind;
import co.unicauca.openmarket.presentation.GUICategory;
import co.unicauca.openmarket.presentation.GUIProducts;
import co.unicauca.openmarket.presentation.GUIProductsFind;
import reloj.frameworkobsobs.Observador;

/**
 *
 * @author brayan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       IProductRepository repository = Factory.getInstance().getRepository("default");
        ICategoryRepository repository2 =  Factory.getInstance().getCatRepository("default");
        ProductService productService = new ProductService(repository);
        CategoryService categoryService=new CategoryService(repository2);
        
        

        GUICategory instance1=new GUICategory(categoryService);
        instance1.setVisible(true);
        instance1.setSize(595, 380);
        instance1.setLocation(0,0);
        GUIProducts instance2 = new GUIProducts(productService);
        
        instance2.setVisible(true);
        instance2.setLocation(590, 0);
         
        GUIProductsFind instance3 = new GUIProductsFind(null,false,productService);
        instance3.setVisible(true);
        productService.addObservador(instance3);
        
        GUICategoriesFind instance4 = new GUICategoriesFind (null,false,categoryService);
        instance4.setVisible(true);
        categoryService.addObservador(instance4);
        
    }
    
}