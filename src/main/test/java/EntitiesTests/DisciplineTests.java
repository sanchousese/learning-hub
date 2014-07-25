package EntitiesTests;

import org.junit.Test;
import ua.com.learninghub.model.dao.implementation.DisciplineDaoImpl;
import ua.com.learninghub.model.dao.implementation.SpecialtyDaoImpl;
import ua.com.learninghub.model.dao.interfaces.DisciplineDao;
import ua.com.learninghub.model.entities.Discipline;

import java.util.List;

/**
 * Created by vasax32 on 25.07.14.
 */
public class DisciplineTests {

    private DisciplineDao disciplineDao = new DisciplineDaoImpl();

    @Test
    public void selectAllTest(){
        List<Discipline> disciplines = disciplineDao.selectAll();
        for(Discipline discipline : disciplines)
            System.out.println(discipline);
    }

    @Test
    public void updateTest(){
        Discipline discipline = disciplineDao.selectById(1);
        discipline.setSpecialty((new SpecialtyDaoImpl()).selectById(2));
        disciplineDao.update(discipline);
    }

    @Test
    public void insertTest(){
        Discipline discipline = new Discipline();
        discipline.setName("Insert Disc OK");
        discipline.setDescription("Some awesome description");
        discipline.setSpecialty((new SpecialtyDaoImpl()).selectById(2));
        disciplineDao.insert(discipline);
    }
}
