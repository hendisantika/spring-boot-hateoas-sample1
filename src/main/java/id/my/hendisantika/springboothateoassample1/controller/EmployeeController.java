package id.my.hendisantika.springboothateoassample1.controller;

import id.my.hendisantika.springboothateoassample1.model.Employee;
import id.my.hendisantika.springboothateoassample1.model.EmployeeList;
import id.my.hendisantika.springboothateoassample1.model.EmployeeReportResult;
import id.my.hendisantika.springboothateoassample1.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public EmployeeList getAllEmployees() {
        EmployeeList employeesList = new EmployeeList();

        for (Employee employee : employeeRepository.findAll()) {
            addLinkToEmployee(employee);
            employeesList.getEmployees().add(employee);
        }

        // Adding self link employee collection resource
        Link selfLink = linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel();
        employeesList.add(selfLink);

        return employeesList;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {

        Optional<Employee> employeeOpt = employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Employee employee = employeeOpt.get();
        addLinkToEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}/report")
    public ResponseEntity<EmployeeReportResult> getReportByEmployeeById(@PathVariable("id") int id) {
        // Do some operation and return report
        return null;
    }

    private void addLinkToEmployee(Employee employee) {
        // Adding self link employee 'singular' resource
        Link link = linkTo(EmployeeController.class).slash(employee.getId()).withSelfRel();
        employee.add(link);

        // Adding method link employee 'singular' resource
        ResponseEntity<EmployeeReportResult> methodLinkBuilder =
                methodOn(EmployeeController.class).getReportByEmployeeById(employee.getId());
        Link reportLink = linkTo(methodLinkBuilder).withRel("employee-report");
        employee.add(reportLink);
    }
}
