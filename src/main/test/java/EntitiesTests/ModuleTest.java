package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.implementation.ModuleDaoImpl;
import ua.com.learninghub.model.dao.interfaces.ModuleDao;
import ua.com.learninghub.model.entities.Course;
import ua.com.learninghub.model.entities.Module;

import java.util.List;

/**
 * Created by vasax32 on 01.08.14.
 */
public class ModuleTest {
    private ModuleDao moduleDao = new ModuleDaoImpl();

    @Test
    public void selectAll(){
        List<Module> modules = moduleDao.selectAll();
        for(Module module : modules)
            System.out.println(module);
    }

    @Test
    public void insert(){
        Module module = new Module();
        module.setName("Insert OK");
        module.setCourse((new CourseDaoImpl()).selectById(2));
        moduleDao.insert(module);
    }

    @Test
    public void update(){
        Module module = moduleDao.selectById(2);
        module.setDescription("Bla OK");
        module.setCourse((new CourseDaoImpl()).selectById(1));
        moduleDao.update(module);
    }
}
