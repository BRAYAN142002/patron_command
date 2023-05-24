
package co.unicauca.openmarket.domain.service;


import co.unicauca.openmarket.access.ICategoryRepository;
import co.unicauca.openmarket.domain.Category;



import java.util.List;
import javax.swing.JOptionPane;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author brayan majin, freider escobar
 */
public class CategoryService extends Observado{
    
    
    public CategoryService(){
        
    }
    private ICategoryRepository repository;
    
    public CategoryService(ICategoryRepository repository){
        this.repository=repository;
    }
    public boolean saveCategory (Long id,String name){
        Category newCategory=new Category();
        newCategory.setCategoryId(id);
        newCategory.setName(name);
        if(newCategory.getName().isBlank()){
            return false;
        }
        
        boolean result=repository.save(newCategory);
        this.notificar();
        return result;
    }
    public boolean editCategory(Long categoryId,Category cat) {
        
        //Validate product
        if(cat==null || cat.getName().isBlank()){
            return false;
        }
      
       boolean result=repository.edit(categoryId,cat);
       this.notificar();
       return result;
    }
    
   public boolean deleteCategory(Long id){
       boolean result =repository.delete(id);
       this.notificar();
        return result;
    }  
    public Category findCategoryById(Long id){
        return repository.findById(id);
    }
       public List<Category> findAllCategories(){
        return repository.findAll();
    }
       
       public List<Category> findCategoriesByName(String name){
        return repository.findByName(name);
    }
      
}  
        
