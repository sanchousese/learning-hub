package ua.com.learninghub.model.interfaces;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created by vasax32 on 22.07.14.
 */
@javax.persistence.Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public interface HibernateL2Cache {

}
