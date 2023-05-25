package co.unicauca.openmarket.domain.service;



import co.unicauca.openmarket.access.IProductRepository;
import co.unicauca.openmarket.domain.Product;
import java.util.ArrayList;
import java.util.List;

import reloj.frameworkobsobs.Observado;

/**
 *
 * @author Libardo, Julio
 */
public class ProductService extends Observado {
      
    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
   public ProductService(){
    
   }
   
   
    private IProductRepository repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductAccess
     */
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }
    

    public boolean saveProduct(Long id,String name, String description,Long categoryId){
        
        Product newProduct = new Product();
        newProduct.setProductId(id);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCategoryId(categoryId);
        
        
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }
        boolean result=repository.save(newProduct);
       this.notificar();
        return result;

    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();

        return products;
    }
    
    public Product findProductById(Long id){
        return repository.findById(id);
    }
    public List<Product> findProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        products = repository.findByName(name);

        return products;
    }
    public List<Product> findProductsByCategory(Long categoryId){
        List<Product> products = new ArrayList<>();
        products = repository.findByCategory(categoryId);

        return products;
    }
    public boolean deleteProduct(Long id){
        boolean result=repository.delete(id);
        this.notificar();
        return result;
       
    }

    public boolean editProduct(Long productId,String name, String description,Long categoryId){
          
        Product producto=new Product();
        producto.setProductId(productId);
        producto.setName(name);
        producto.setDescription(description);
        producto.setCategoryId(categoryId);
        //Validate product
        if (producto.getProductId() == null ||producto.getName().isBlank()) {
            return false;
        }
        boolean result= repository.edit(producto);
        this.notificar();
        return result;

    }

}