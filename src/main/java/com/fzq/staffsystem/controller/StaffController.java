package com.fzq.staffsystem.controller;

import com.fzq.staffsystem.model.Staff;
import com.fzq.staffsystem.model.ViewObject;
import com.fzq.staffsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StaffController {

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "/staff", method = {RequestMethod.GET})
    public String staff() {
        return "staff";
    }

    @RequestMapping(value = "/addStaff", method = {RequestMethod.POST})
    public String addStaff(Model model, @RequestParam("staffId") String staffId,
                           @RequestParam("name") String name,
                           @RequestParam("headUrl") String headUrl,
                           @RequestParam("gender") String gender,
                           @RequestParam("birthYear") String birthYear,
                           @RequestParam("address") String address,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("departmentName") String departmentName
                           ) {
        Staff staff = new Staff(staffId, name, headUrl, gender, birthYear, address, phoneNumber, departmentName);
        staffService.addStaff(staff);
        model.addAttribute("staff", staff);
        return "detail";
    }

    @RequestMapping(value = "/getStaffByName", method = {RequestMethod.GET})
    public String getStaffByName(Model model, @RequestParam("name") String name) {
        List<Staff> staffList = staffService.selectStaffByName(name);
        List<ViewObject> result = new ArrayList<>();
        for (Staff staff : staffList) {
            ViewObject vo = new ViewObject();
            vo.set("staffId", staff.getStaffId());
            vo.set("name", staff.getName());
            //vo.set("headUrl", staff.getHeadUrl());
            //vo.set("gender", staff.getGender());
            //vo.set("birthYear", staff.getBirthYear());
            //vo.set("addreass", staff.getAddress());
            //vo.set("phoneNumber", staff.getPhoneNumber());
            vo.set("departmentName", staff.getDepartmentName());
            result.add(vo);
        }
        model.addAttribute("staffs", result);
        return "staff";
    }

    @RequestMapping(value = "/getStaffById", method = {RequestMethod.GET})
    public String getStaffById(Model model, @RequestParam("staffId") String staffId) {
        List<Staff> staffList = staffService.selectStaffById(staffId);
        List<ViewObject> result = new ArrayList<>();
        for (Staff staff : staffList) {
            ViewObject vo = new ViewObject();
            vo.set("staffId", staff.getStaffId());
            vo.set("name", staff.getName());
            vo.set("departmentName", staff.getDepartmentName());
            result.add(vo);
        }
        model.addAttribute("staffs", result);
        return "staff";
    }

    @RequestMapping(value = "/getStaff", method = {RequestMethod.GET})
    public String getStaff(Model model, @RequestParam("staffId") String staffId) {
        Staff staff = staffService.selectStaff(staffId);
        model.addAttribute("staff", staff);
        return "detail";
    }

    @RequestMapping(value = "/updateStaff", method = {RequestMethod.POST})
    public String updateStaff(Model model,@RequestParam("staffId") String staffId,
                              @RequestParam("name") String name,
                              @RequestParam("headUrl") String headUrl,
                              @RequestParam("gender") String gender,
                              @RequestParam("birthYear") String birthYear,
                              @RequestParam("address") String address,
                              @RequestParam("phoneNumber") String phoneNumber,
                              @RequestParam("departmentName") String departmentName
                              ) {
        Staff staff = new Staff(staffId, name, headUrl, gender, birthYear, address, phoneNumber, departmentName);
        staff = staffService.updateStaff(staff);
        model.addAttribute("staff", staff);
        return "detail";
    }

    @RequestMapping(value = "/deleteStaff", method = {RequestMethod.POST})
    public String deleteStaff(Model model, @RequestParam("staffId") String staffId,
                              @RequestParam("departmentName") String departmentName){
        staffService.deleteStuff(staffId, departmentName);
        return "staff";
    }
}
