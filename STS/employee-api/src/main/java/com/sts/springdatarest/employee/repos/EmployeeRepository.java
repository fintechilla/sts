package com.sts.springdatarest.employee.repos;

import org.springframework.data.repository.CrudRepository;

import com.sts.springdatarest.employee.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
