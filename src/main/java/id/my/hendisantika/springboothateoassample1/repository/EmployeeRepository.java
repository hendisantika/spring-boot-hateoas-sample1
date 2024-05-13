package id.my.hendisantika.springboothateoassample1.repository;

import id.my.hendisantika.springboothateoassample1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-hateoas-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:11
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
