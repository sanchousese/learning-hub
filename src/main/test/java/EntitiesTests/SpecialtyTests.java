package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.SpecialtyDaoImpl;
import ua.com.learninghub.model.dao.interfaces.SpecialtyDao;
import ua.com.learninghub.model.entities.Specialty;

import java.util.List;

/**
 * Created by vasax32 on 25.07.14.
 */
public class SpecialtyTests {
    private SpecialtyDao specialtyDao = new SpecialtyDaoImpl();

    @Test
    public void selectAll(){
        List<Specialty> specialties = specialtyDao.selectAll();
        for(Specialty s : specialties)
            System.out.println(s);
    }

    @Test
    public void updateTest(){
        Specialty specialty = specialtyDao.selectById(1);
        specialty.setName(specialty.getName() + " OK");
        specialtyDao.update(specialty);
    }

    @Test
    public void insertTest(){
        Specialty specialty = new Specialty();
        specialty.setName("Insert OK");
        specialty.setDescription("Try it now");
        specialtyDao.insert(specialty);
    }
}
