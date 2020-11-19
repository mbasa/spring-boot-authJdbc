/**
 * パッケージ名：org.authjdbc.repository
 * ファイル名  ：EmployeeRepository.java
 * 
 * @author mbasa
 * @since Nov 9, 2020
 */
package org.authjdbc.repository;

import java.util.List;

import org.authjdbc.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * 説明：
 *
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value="select * from employee",nativeQuery=true)
    List<Employee> listEmployees();
    
    List<Employee> findByName(@Param("name") String name);
    
    List<Employee> findAll();    
}
