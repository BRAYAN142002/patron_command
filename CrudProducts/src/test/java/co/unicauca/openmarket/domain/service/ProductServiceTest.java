package co.unicauca.openmarket.domain.service;


import co.unicauca.openmarket.access.IProductRepository;
import co.unicauca.openmarket.domain.Category;
import co.unicauca.openmarket.domain.Product;
import co.unicauca.openmarket.presentation.commands.OMAddProductCommand;
import co.unicauca.openmarket.presentation.commands.OMInvoker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

  private ProductService service;
  private IProductRepository repository;
  private OMInvoker ominvoker;
  //@BeforeAll
  public void setUp() {
    List<Product> products = repository.findAll();
    products.forEach(product -> {
      repository.delete(product.getProductId());
    });
  }

  public ProductServiceTest() {
    repository = new MockProductRepository();
    service = new ProductService(repository);
    
  }

  @Test
  public void testSaveProduct() {
       
    boolean result =
  service.saveProduct(1L,"Test Product", "This is a test product.",1L);

    assertTrue(result);
  }

  @Test
  public void testSaveProductInvalidName() {
    boolean result =
      this.service.saveProduct(2L,"", "This is an invalid product.",1L);
    assertFalse(result);
  }

  @Test
  public void testFindAllProducts() {
    List<Product> products = this.service.findAllProducts();
    assertEquals(2, products.size());
  }

  @Test
  public void testFindProductById() {
    Product product = this.service.findProductById(1L);
    assertNotNull(product);
    assertEquals(1L, product.getProductId().longValue());
  }

  @Test
  public void testFindProductByIdNotFound() {
    Product product = this.service.findProductById(10L);
    assertNull(product);
  }

  @Test
  public void testDeleteProduct() {
    boolean result = this.service.deleteProduct(1L);
    assertTrue(result);
  }

  @Test
  public void testDeleteProductNotFound() {
    boolean result = this.service.deleteProduct(10L);
    assertFalse(result);
  }

 
  @Test
  public void testEditProduct() {
    Product product = new Product();
    Category category=new Category();
    product.setProductId(1L);
    product.setName("Test Product");
    product.setDescription("This is an updated product.");
    category.setCategoryId(1L);
    boolean result = this.service.editProduct(1L,product.getName(),product.getDescription(),1L);
    assertTrue(result);
  }

  @Test
  public void testEditProductNotFound() {
    Product product = new Product();
    product.setProductId(10L);
    product.setName("Test Product");
    product.setDescription("This is an updated product.");
     boolean result = this.service.editProduct(20L,"",product.getDescription(),1L);
    assertFalse(result);
  }

  private class MockProductRepository implements IProductRepository {

    private List<Product> products;

    public MockProductRepository() {
      products = new ArrayList<>();
      Product product1 = new Product();
      product1.setProductId(1L);
      product1.setName("Product 1");
      product1.setDescription("This is product 1.");
      product1.setCategoryId(1L);
      
      Product product2 = new Product();
      product2.setProductId(2L);
      product2.setName("Product 2");
      product2.setDescription("This is product 2.");
      product2.setCategoryId(2L);
      products.add(product1);
      products.add(product2);
    }

    @Override
    public boolean save(Product product) {
     
   
      products.add(product);
      return true;
    }

    @Override
    public List<Product> findAll() {
      return products;
    }

    @Override
    public Product findById(Long id) {
      for (Product product : products) {
        if (product.getProductId().equals(id)) {
          return product;
        }
      }
      return null;
    }

    @Override
    public boolean delete(Long id) {
      Product product = findById(id);
      if (product != null) {
        products.remove(product);
        return true;
      }
      return false;
    }

    @Override
    public boolean edit(Product product) {
      Product productToUpdate = findById(product.getCategoryId());
    
      if (productToUpdate != null) {
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategoryId(product.getCategoryId());
        return true;
      }
      return false;
    }

        @Override
        public List<Product> findByName(String pname) {
           List<Product>productos=new ArrayList<>();
            for(Product product:products){
               if(product.getName().equals(pname)){
                 productos.add(product);
               }
            }
        
         return productos;
        }

        @Override
        public List<Product> findByCategory(Long categoryName) {
            List<Product>productos=new ArrayList<>();
            for(Product product:products){
               if(product.getCategoryId().equals(categoryName)){
                 productos.add(product);
               }
            }
        
         return productos;
        }

        


       
  }
}