package id.my.hendisantika.springboothateoassample1;

import id.my.hendisantika.springboothateoassample1.model.Employee;
import id.my.hendisantika.springboothateoassample1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootHateoasSample1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHateoasSample1Application.class, args);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.saveAll(List.of(
                new Employee(null, "Itadori", "Yuji", "yuji@yopmail.com"),
                new Employee(null, "Megumi", "Fushihuro", "megumi@yopmail.com"),
                new Employee(null, "Gojo", "Satoru", "gojo@yopmail.com")));
    }
}
