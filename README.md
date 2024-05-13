# spring-boot-hateoas-sample1

In this Spring Boot HATEOAS example, we will learn to add **HATEOAS** (Hypertext as the Engine of Application State)
links to resource representations returned from REST APIs in a Spring boot application.

### What is HATEOAS?

HATEOAS is one of the constraints of the REST architectural style first presented by Roy Fielding in his dissertation.
The term hypermedia refers to any content that contains links to other forms of media such as other APIs, images,
movies, and text. In HATEOAS, we insert the hypermedia links in the API response contents.

These hypermedia links allow the client to dynamically navigate to the appropriate resources by traversing the links.

### API Support

The HATEOAS module provides given three classes to add the links to the resource representations.

1. RepresentationModel – Base class for DTOs to collect links.
2. Link – Immutable value object for links. It stores both a hypertext reference and a link relation. It exposes other
   attributes as defined in RFC-8288.
3. WebMvcLinkBuilder – Builder to ease building Link instances pointing to Spring MVC controllers.

To use the RepresentationModel, we extend the model class (such as Employee or Item) with RepresentationModel, create
instances of the model class, populate the properties and add the desired links to it.

```java
public class Employee extends RepresentationModel<Employee> {

    //...
}
```

### Creating Link with Constructors

As stated earlier, the hateoas module works with an immutable Link type. Link.of() is an overloaded method that takes
various
types of arguments to create an immutable instance of Link.

```shell
Link Builder Methods
Link of(String href)
Link of(String href, LinkRelation relation)
Link of(String href, String relation)
Link of(UriTemplate template, LinkRelation relation)
Link of(UriTemplate template, String relation)
```

Let us see an example to see how Link instances are created.

```shell
Link link = Link.of("/employee-report");
assertThat(link.getHref()).isEqualTo("/employee-report");
assertThat(link.getRel()).isEqualTo(IanaLinkRelations.SELF);
link = Link.of("/employee-report", IanaLinkRelations.RELATED);
assertThat(link.getHref()).isEqualTo("/employee-report");
assertThat(link.getRel()).isEqualTo(LinkRelation.of(IanaLinkRelations.RELATED));
```

Please note that the href value can be a URI template as well and we can replace the placeholder values in runtime.

```shell
Create Links with URI Template
Link link = Link.of("/{department}/users/{?id}");
Map<String, Object> values = new HashMap<>();
values.put("department", "HR");
values.put("id", 123);
assertThat(link.expand(values).getHref()).isEqualTo("/HR/users?id=123");
```

### Creating Links with WebMvcLinkBuilder

Spring HATEOAS also provides a WebMvcLinkBuilder that lets us create links by pointing to controller classes.

For example, our controller class is:

```java
EmployeeController.java

@RestController

public class EmployeeController {
    @GetMapping("/employees")
    public EmployeeList getAllEmployees() {
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
    }

    @GetMapping("/employees/{id}/report")
    public ResponseEntity<EmployeeReport> getReportByEmployeeById(@PathVariable("id") int id) {
    }
}
```

We can create various links to the controller and its methods using WebMvcLinkBuilder.slash() method.

```java
Using /* Link to "/employees" */
Link link = linkTo(EmployeeController.class).withRel("employees");
/* Link to "/employees/{id}" */
Employee e = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
Link link = linkTo(EmployeeController.class).slash(e.getId()).withSelfRel();
```

We can also use create a Method instance and pass it to WebMvcLinkBuilder.methodOn().

```java
Using Method
Instance
Method method = EmployeeController.class.getMethod("getReportByEmployeeById", Integer.class);
Link link = linkTo(method, 111).withSelfRel();
//or
Link link = linkTo(methodOn(EmployeeController.class).getReportByEmployeeById(111)).withSelfRel();
```

### Adding Link to RepresentationModel

The RepresentationModel provides the overloaded add() methods that can accept either a single Link or a list of Link
instances.

```java
Employee employee = ...; //Get an instance from database
// Self link
Link selfLink = linkTo(EmployeeController.class)
        .slash(employee.getId()).
        withSelfRel();
// Method link
Link reportLink = linkTo(methodOn(EmployeeController.class)
        .getReportByEmployeeById(employee.getId()))
        .withRel("report");
employee.

add(selfLink);
employee.

add(reportLink);
```
