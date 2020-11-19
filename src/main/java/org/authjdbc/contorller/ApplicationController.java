/**
 * パッケージ名：org.authjdbc.contorller
 * ファイル名  ：ApplicationController.java
 * 
 * @author mbasa
 * @since Nov 9, 2020
 */
package org.authjdbc.contorller;

import java.util.List;

import org.authjdbc.entity.Employee;
import org.authjdbc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 説明：
 *
 */
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    EmployeeRepository empRepository;
    
    public ApplicationController() {
    }

    @GetMapping("/testAdmin")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public String testAdmin() {        
        return "{\"result\":\"ADMIN User \"}";
    }
    
    @GetMapping("/employee")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<Employee> getEmployees() {        
        return empRepository.findAll();
    }
    
    @PostMapping(value = "/employee",
            consumes = "application/json", 
            produces = "application/json" )
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Employee createEmp(@RequestBody Employee emp) {
        return  empRepository.save(emp);        
    }
    
    @DeleteMapping(value = "/employee",
            consumes = "application/json", 
            produces = "application/json" )
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public void deleteEmp(@RequestBody Employee emp) {
        empRepository.delete(emp);
    }
}    