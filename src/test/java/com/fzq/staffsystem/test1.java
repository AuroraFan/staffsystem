package com.fzq.staffsystem;

import com.fzq.staffsystem.DAO.DepartmentDAO;
import com.fzq.staffsystem.model.Department;
import com.fzq.staffsystem.model.Staff;
import com.fzq.staffsystem.service.DepartmentService;
import com.fzq.staffsystem.service.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StaffsystemApplication.class)
//@Sql("/init-schema.sql")
public class test1 {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    StaffService staffService;

    @Test
    public void departmentTest(){
        //Department department = new Department("500","test5");
        //departmentService.addDepartment(department);
        Staff staff = new Staff("50001", "updatename", "test.jqg", "男", "1800","更新地址","19929992929","depB");
        staffService.addStaff(staff);
    }
}
