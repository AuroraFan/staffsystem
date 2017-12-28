package com.fzq.staffsystem.service;


import com.fzq.staffsystem.DAO.DepartmentDAO;
import com.fzq.staffsystem.DAO.StaffDAO;
import com.fzq.staffsystem.model.Department;
import com.fzq.staffsystem.model.Staff;
import com.fzq.staffsystem.util.JedisAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StaffService {
    @Autowired
    StaffDAO staffDAO;
    @Autowired
    DepartmentDAO departmentDAO;
    @Autowired
    JedisAdapter jedisAdapter;
   public int addStaff(Staff staff){
       if (staffDAO.selectStaff(staff.getStaffId()) != null){
           return -1;
       }else{
           try{
               addStaffRedis(staff);
               staffDAO.addStaff(staff);
               departmentDAO.staffCountIncrease(staff.getDepartmentName());
               return 1;
           }catch (Exception e){
               return -1;
           }
       }
   }
    public void addStaffRedis(Staff staff){
        jedisAdapter.hset(staff.getStaffId(),"name",staff.getName());
        jedisAdapter.hset(staff.getStaffId(),"headUrl",staff.getHeadUrl());
        jedisAdapter.hset(staff.getStaffId(),"gender",staff.getGender());
        jedisAdapter.hset(staff.getStaffId(),"birthYear",staff.getBirthYear());
        jedisAdapter.hset(staff.getStaffId(),"address",staff.getAddress());
        jedisAdapter.hset(staff.getStaffId(),"phoneNumber",staff.getPhoneNumber());
        jedisAdapter.hset(staff.getStaffId(),"departmentName",staff.getDepartmentName());
    }
    public Staff selectStaff(String staffId){
       if(jedisAdapter.hlen(staffId) > 0){
           Map<String, String> map = jedisAdapter.hgetAll(staffId);
           Staff staff = new Staff(staffId,map.get("name"),map.get("headUrl"),map.get("gender"),
                   map.get("birthYear"),map.get("address"),map.get("phoneNumber"),map.get("departmentName"));
           return staff;
       }else{
           Staff staff = staffDAO.selectStaff(staffId);
           addStaffRedis(staff);
           return staff;
       }
    }

    public List<Staff> selectStaffById(String staffId){
        return staffDAO.selectStaffById(staffId);
    }

    public List<Staff> selectStaffByName(String name){
        return staffDAO.selectStaffByName(name);
    }

    public Staff updateStaff(Staff staff){
        try{
            Staff origion = selectStaff(staff.getStaffId());
            if(!staff.getDepartmentName().equals(origion.getDepartmentName())){
                departmentDAO.staffCountDecrease(origion.getDepartmentName());
                departmentDAO.staffCountIncrease(staff.getDepartmentName());
            }
            addStaffRedis(staff);
            return staffDAO.updateStaff(staff);
        }catch(Exception e){
            return null;
        }
    }

    public void deleteStuff(String staffId, String departmentName){
        jedisAdapter.hdel(staffId,"name");
        jedisAdapter.hdel(staffId,"headUrl");
        jedisAdapter.hdel(staffId,"gender");
        jedisAdapter.hdel(staffId,"birthYear");
        jedisAdapter.hdel(staffId,"address");
        jedisAdapter.hdel(staffId,"phoneNumber");
        jedisAdapter.hdel(staffId,"departmentName");
        staffDAO.deleteStaff(staffId);
        departmentDAO.staffCountDecrease(departmentName);
    }
}
