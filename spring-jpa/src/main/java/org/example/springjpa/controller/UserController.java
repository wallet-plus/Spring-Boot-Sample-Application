package org.example.springjpa.controller;

import org.example.springjpa.model.User;
import org.example.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService employeeService;

    // Endpoint to get employees with optional search parameters
    @GetMapping("/employees")
    public List<User> getEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String mobile) {

        // If both firstName and mobile are provided, search both
        if (firstName != null && mobile != null) {
            // Ensure we only take the first two characters for search
            firstName = firstName.length() >= 2 ? firstName.substring(0, 2) : firstName;
            mobile = mobile.length() >= 2 ? mobile.substring(0, 2) : mobile;
            return employeeService.searchEmployeesByFirstNameAndMobile(firstName, mobile);
        }

        // If only firstName is provided, search by first name
        else if (firstName != null) {
            firstName = firstName.length() >= 2 ? firstName.substring(0, 2) : firstName;
            return employeeService.searchEmployeesByFirstName(firstName);
        }

        // If only mobile is provided, search by mobile
        else if (mobile != null) {
            mobile = mobile.length() >= 2 ? mobile.substring(0, 2) : mobile;
            return employeeService.searchEmployeesByMobile(mobile);
        }

        // If neither parameter is provided, return all employees
        else {
            return employeeService.getAllEmployees();
        }
    }

    //    @GetMapping
    //    public List<User> getAllEmployees() {
    //        return employeeService.getAllEmployees();
    //    }

    @GetMapping("/{id}")
    public Optional<User> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public User createEmployee(@RequestBody User employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public User updateEmployee(@PathVariable Long id, @RequestBody User employeeDetails) {
        User employee = employeeService.getEmployeeById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setUsername(employeeDetails.getUsername());
        employee.setPassword(employeeDetails.getPassword());
        employee.setEmail(employeeDetails.getEmail());
        employee.setRole(employeeDetails.getRole());

        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
