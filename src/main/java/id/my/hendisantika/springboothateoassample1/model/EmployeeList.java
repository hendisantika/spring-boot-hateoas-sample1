package id.my.hendisantika.springboothateoassample1.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-hateoas-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:10
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeList extends RepresentationModel<EmployeeList> {

    private List<Employee> employees = new ArrayList<Employee>();
}
