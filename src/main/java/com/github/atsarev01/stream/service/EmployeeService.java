package com.github.atsarev01.stream.service;

import com.github.atsarev01.stream.exeption.EmployeeAlreadyAddedExeption;
import com.github.atsarev01.stream.exeption.EmployeeNotFoundExeption;
import com.github.atsarev01.stream.exeption.EmployeeStorageIsFullExeption;
import com.github.atsarev01.stream.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private static final int SIZE = 3;

    private Map <String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    private String getKey(String firstName, String lastName) {
        return firstName + "|" + lastName;
    }

    public Employee add(String firstName, String lastName, int department, int salary) {
        if (employees.size() < SIZE) {
            Employee employee = new Employee(firstName, lastName, department, salary);
            if (employees.containsKey(employee.getFullName() )) {
                throw new EmployeeAlreadyAddedExeption();
            }
            employees.put(employee.getFullName(), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullExeption();

    }
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundExeption();
        }
        employees.remove(employee.getFullName());
        return employee;
    }
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundExeption();
        }
        return employees.get(employee.getFullName());

    }

    public Collection<Employee> employees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

}
