package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Tạo mới
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Lấy tất cả
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Lấy theo ID
    @GetMapping("/{eid}")
    public ResponseEntity<Employee> getById(@PathVariable Integer eid) {
        Optional<Employee> employee = employeeRepository.findById(eid);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Xoá
    @DeleteMapping("/{eid}")
    public ResponseEntity<Void> delete(@PathVariable Integer eid) {
        if (!employeeRepository.existsById(eid)) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.deleteById(eid);
        return ResponseEntity.noContent().build();
    }

    // Cập nhật
    @PutMapping("/{eid}")
    public ResponseEntity<Employee> update(@PathVariable Integer eid, @RequestBody Employee newData) {
        return employeeRepository.findById(eid)
                .map(employee -> {
                    employee.setEname(newData.getEname());
                    employee.setBirthday(newData.getBirthday());
                    employee.setZid(newData.getZid());
                    employeeRepository.save(employee);
                    return ResponseEntity.ok(employee);
                }).orElse(ResponseEntity.notFound().build());
    }
}
