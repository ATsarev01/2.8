package com.github.atsarev01.stream.service;

import com.github.atsarev01.stream.StreamApplication;
import com.github.atsarev01.stream.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void changeDepartment(Employee employee, int department) {
        employeeService.getAll().stream()
                .filter(emp -> Objects.equals(employee, emp))
                .forEach(emp -> emp.setDepartment(department));

    }

    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public void indexSalariesForDepartment(double index, int department) {
        employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> employee.setSalary((int)(employee.getSalary() + employee.getSalary() * index / 100)));
    }

    public double averageSalaryForDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0);
    }

    public Employee findEmployeeWithMinSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }
    public Employee findEmployeeWithMaxSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }
    public double totalSalariesForDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public List<Employee> getAllEmployeesFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(null);
    }
}


