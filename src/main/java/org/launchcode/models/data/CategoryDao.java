package org.launchcode.models.data;
import org.launchcode.models.*;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ethan on 4/6/17.
 */
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
