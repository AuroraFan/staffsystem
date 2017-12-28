package com.fzq.staffsystem.controller;

import com.fzq.staffsystem.model.Department;
import com.fzq.staffsystem.model.ViewObject;
import com.fzq.staffsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/department", method = {RequestMethod.GET})
    public String department(){
        return"department";
    }

    @RequestMapping(value = "/addDepartment",method = {RequestMethod.POST})
    public String addDepartment(Model model, @RequestParam("departmentId") String departmentId,
                                @RequestParam("name") String name){
        Department department = new Department(departmentId,name);
        Map<String,Object> res = departmentService.addDepartment(department);
        if(res.containsKey("msg")){
            model.addAttribute("msg",res.get("msg"));
            return "department";
        }else{
            model.addAttribute("department",department);
            return "department";
        }
    }
    @RequestMapping(value = "/getDepartment",method = {RequestMethod.GET})
    public String getDepartments(Model model, @RequestParam("name") String name){
        List<Department> departmentList = departmentService.getDepartmentByName(name);
        List<ViewObject> result = new ArrayList<>();
        for(Department department:departmentList){
            ViewObject vo = new ViewObject();
            vo.set("departmentId",department.getDepartmentId());
            vo.set("name",department.getName());
            vo.set("stuffCount",department.getStuffCount());
            result.add(vo);
        }
        model.addAttribute("departments",result);
        return "department";
    }

    @RequestMapping(value = "/deleteDepartment", method = {RequestMethod.POST})
    public String deleteDepartment(Model model, @RequestParam("departmentId") String departmentId,
    @RequestParam("stuffCount") int stuffCount){
        if(stuffCount == 0) {
            if (departmentService.deleteDepartment(departmentId) > 0) {
                return "department";
            }
        }else {
            model.addAttribute("msg", "failed");
        }
        return "department";
    }
}
