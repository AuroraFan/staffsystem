package com.fzq.staffsystem.DAO;

import com.fzq.staffsystem.model.Staff;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StaffDAO {
    String TABLE_NAME = "staff";
    String INSET_FIELDS = " staff_id, name, head_url, gender, birth_year, address, phone_number, department_name";
    String SELECT_FIELDS = " id, "+INSET_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{staffId},#{name},#{headUrl},#{gender},#{birthYear},#{address},#{phoneNumber},#{departmentName})"})
    int addStaff(Staff staff);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME," where staff_id like '%${staffId}%'"})
    Staff selectStaff(@Param("staffId")String staffId);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME," where staff_id like '%${staffId}%'"})
    List<Staff> selectStaffById(@Param("staffId")String staffId);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME," where name like '%${name}%'"})
    List<Staff> selectStaffByName(@Param("name") String name);

    @Update({"update ",TABLE_NAME, " set name=#{staff.name},head_url=#{staff.headUrl},gender=#{staff.gender},birth_year=#{staff.birthYear}," +
            "address=#{staff.address},phoneNumber=#{staff.phoneNumber},department_name=#{staff.departmentName} where staff_id = #{staff.staffId}"})
    Staff updateStaff(Staff staff);

    @Delete({"delete from ", TABLE_NAME, "where staff_id = #{staffId}"})
    void deleteStaff(@Param("staffId")String staffId);
}
