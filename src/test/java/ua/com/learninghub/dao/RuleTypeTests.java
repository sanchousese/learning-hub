package ua.com.learninghub.dao;

import org.junit.Test;
import ua.com.learninghub.model.dao.RuleTypeDao;
import ua.com.learninghub.model.entities.RuleType;

import java.util.List;

/**
 * Created by vasax32 on 17.07.14.
 */
public class RuleTypeTests {
    private RuleTypeDao ruleTypeDao = new RuleTypeDao();

    @Test
    public void SelectAllTest(){
        List<RuleType> ruleTypes = ruleTypeDao.selectAll();
        System.out.println(ruleTypes);
    }

    @Test
    public  void SelectById(){
        RuleType ruleType = ruleTypeDao.selectById(1);
        System.out.println(ruleType);
    }

    @Test
    public void UpdateTest(){
        RuleType ruleType = ruleTypeDao.selectById(1);
        ruleType.setEnabled(false);
        ruleTypeDao.update(ruleType);
    }

    @Test
    public void insertTest(){
        RuleType rule = new RuleType();
        rule.setName("Insert OK");
        rule.setEnabled(true);
        ruleTypeDao.insert(rule);
    }
}
