package co.unicauca.openmarket.presentation.commands;

import co.unicauca.openmarket.access.ICategoryRepository;
import co.unicauca.openmarket.domain.Category;
import co.unicauca.openmarket.domain.service.CategoryService;
import co.unicauca.openmarket.presentation.commands.OMInvoker;
import com.sun.tools.javac.util.Assert;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author brayan majin, freider escobar
 */
public class OMInvokerTest {

    private OMInvoker invoker;
    private CategoryService service;
    private ICategoryRepository repository;

    //@BeforeAll
    public void setUp() {
        List<Category> categories = repository.findAll();
        categories.forEach(category -> {
            repository.delete(category.getCategoryId());
        });
    }

    public OMInvokerTest() {
        invoker = new OMInvoker();
        repository = new OMInvokerTest.MockCategoryRepository();
        service = new CategoryService(repository);
    }

    @Test
    public void testExecuteCommand() {
        Category category = new Category(1L, "Test Add Category command");
        OMAddCategoryCommand comm = new OMAddCategoryCommand(category, service);
        invoker.addCommand(comm);
        invoker.execute();
        boolean result = invoker.hasMoreCommands();
        assertTrue(result);
    }
    
    @Test
    public void testUnexecuteCommand(){
        Category category = new Category(1L, "Test Add Category command");
        OMAddCategoryCommand comm = new OMAddCategoryCommand(category, service);
        invoker.addCommand(comm);
        invoker.execute();
        boolean result1 = invoker.hasMoreCommands();
        assertTrue(result1);
        invoker.unexecute();
        boolean result2 = invoker.hasMoreCommandsRedo();
        result1 = invoker.hasMoreCommands();
        assertFalse(result1);
        assertTrue(result2);
    }
    
    @Test
    public void testReExecuteCommand(){
        Category category = new Category(1L, "Test Add Category command");
        OMAddCategoryCommand comm = new OMAddCategoryCommand(category, service);
        invoker.addCommand(comm);
        invoker.execute();
        boolean result1 = invoker.hasMoreCommands();
        assertTrue(result1);
        invoker.unexecute();
        boolean result2 = invoker.hasMoreCommandsRedo();
        result1 = invoker.hasMoreCommands();
        assertFalse(result1);
        assertTrue(result2);
        invoker.reExecuted();
        result1 = invoker.hasMoreCommands();
        result2 = invoker.hasMoreCommandsRedo();
        assertTrue(result1);
        assertFalse(result2);
    }
    
    private class MockCategoryRepository implements ICategoryRepository {

        private List<Category> categories;

        public MockCategoryRepository() {
            categories = new ArrayList<>();
            Category category1 = new Category();
            category1.setCategoryId(1L);
            category1.setName("Category 1");
            Category category2 = new Category();
            category2.setCategoryId(2L);
            category2.setName("Category 2");
            categories.add(category1);
            categories.add(category2);
        }

        @Override
        public boolean save(Category category) {
            categories.add(category);
            return true;
        }

        @Override
        public List<Category> findAll() {
            return categories;
        }

        @Override
        public Category findById(Long id) {
            for (Category category : categories) {
                if (category.getCategoryId().equals(id)) {
                    return category;
                }
            }
            return null;
        }

        @Override
        public boolean delete(Long id) {
            Category category = findById(id);
            if (category != null) {
                categories.remove(category);
                return true;
            }
            return false;
        }

        @Override
        public boolean edit(Long id, Category category) {
            Category categoryToUpdate = findById(id);
            if (categoryToUpdate != null) {
                categoryToUpdate.setName(category.getName());
                return true;
            }
            return false;
        }

        @Override
        public List<Category> findByName(String name) {
            List<Category> categorias = new ArrayList<>();
            for (Category cat : categories) {
                if (cat.getName().equals(name)) {
                    categorias.add(cat);
                }
            }
            return categorias;
        }
    }
}
