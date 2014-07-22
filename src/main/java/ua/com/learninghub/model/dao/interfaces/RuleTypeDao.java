package ua.com.learninghub.model.dao.interfaces;

import ua.com.learninghub.model.entities.RuleType;

import java.util.List;

/**
 * Created by Max on 21.07.2014.
 */
public interface RuleTypeDao {
    List<RuleType > selectAll();

    RuleType  selectById(int id);

    int update(RuleType rule);

    void insert(RuleType ruleType);
}
