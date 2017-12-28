package com.fzq.staffsystem.DAO;

import com.fzq.staffsystem.model.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentDAO {
    String TABLE_NAME = "department";
    String INSET_FIELDS = " department_id, name, stuff_count ";
    String SELECT_FIELDS = " id, department_id, name, stuff_count ";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
    ") values (#{departmentId},#{name},0)"})
    int addDepartment(Department department);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME," where department_id = #{departmentId}"})
    Department selectDepartmentById(String departmentId);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME," where name like '%${name}%'"})
    List<Department> selectDepartmentByName(@Param("name") String name);

    @Delete({"delete from ", TABLE_NAME, "where department_id = #{departmentId}"})
    void deleteDepartment(String departmentId);

    @Update({"update ",TABLE_NAME," set stuff_count = stuff_count+1 where name = #{departmentName}"})
    void staffCountIncrease(String departmentName);

    @Update({"update ",TABLE_NAME," set stuff_count = stuff_count-1 where name = #{departmentName}"})
    void staffCountDecrease(String departmentName);
}
