package id.my.hendisantika.springboothateoassample1.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.hateoas.RepresentationModel;

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
@XmlRootElement(name = "employee-report")
public class EmployeeReportResult extends RepresentationModel<EmployeeReportResult> {
}
