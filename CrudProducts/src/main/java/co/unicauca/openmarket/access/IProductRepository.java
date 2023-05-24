
package co.unicauca.openmarket.access;



import co.unicauca.openmarket.domain.Product;
import java.util.List;


/**
 *
 * @author Libardo, Julio
 */
public interface IProductRepository {
    boolean save(Product newProduct);
    
    boolean edit(Product newProduct);
    
    boolean delete(Long id);

    Product findById(Long id);
    
   List<Product> findByName(String pname);
    List<Product> findByCategory(Long categoryName);
    List<Product> findAll();
    

}
