package com.fzq.staffsystem.service;

import com.fzq.staffsystem.DAO.DepartmentDAO;
import com.fzq.staffsystem.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;

    public Map<String, Object> addDepartment(Department department) {
        Map<String, Object> result = new HashMap<>();
        if (departmentDAO.selectDepartmentById(department.getDepartmentId()) != null) {
            result.put("msg", "departmentID already exist");
            return result;
        } else {
            try {
                departmentDAO.addDepartment(department);
                return result;
            } catch (Exception e) {
                result.put("msg", "failed");
                return result;
            }
        }
    }

    public List<Department> getDepartmentByName(String name) {
        return departmentDAO.selectDepartmentByName(name);
    }

    public int deleteDepartment(String departmentId) {
        try {
            departmentDAO.deleteDepartment(departmentId);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }
}
