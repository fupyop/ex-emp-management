package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {

@Autowired	
private NamedParameterJdbcTemplate template;
	
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
	= (rs, i) -> {
	Employee employee = new Employee();
	employee.setId(rs.getInt("id"));
	employee.setName(rs.getString("name"));
	employee.setImage(rs.getString("image"));
	employee.setGender(rs.getString("gender"));
	employee.setHireDate(rs.getDate("hire_date"));
	employee.setMailAddress(rs.getString("mail_address"));
	employee.setTelephone(rs.getString("telephone"));
	employee.setSalary(rs.getInt("salary"));
	employee.setCharacteristics(rs.getString("characteristics"));
	employee.setDependentsCount(rs.getInt("dependents_count"));
	return employee;
};
	/**
	 * findAllメソッド
	 * @return
	 */
	public List<Employee> findAll(){
	String sql ="SELECT * FROM employees ORDER BY hire_date";
	List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
	return employeeList;
	
}
	/**
	 * loadメソッド
	 * @param id
	 * @return
	 */
	public Employee load(Integer id) {
	String sql ="SELECT * FROM employees WHERE id=:id";
	SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
	Employee employee = template.queryForObject(sql,param,EMPLOYEE_ROW_MAPPER);
	return employee;
	}
	
	/**
	 * updateメソッド
	 * @param employee
	 * @return
	 */
	public Employee save(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		if(employee.getId() == null) {
			String insertSql = "INSERT INTO emploees(id, name,image,gender,hire_date,mail_address,telephone,salary,characteristics,dependents_count) "
					+ "VALUES(:id, :name,:image,:gender,:hire_date,:mail_address,:telephone,:salary,:characteristics,:dependents_count)";
			template.update(insertSql, param);
		}else {
			String updateSql="UPDATE employees SET dependents_count=:dependents_count WHERE id=:id";
		template.update(updateSql, param);
		}
	return employee;
  }
}
