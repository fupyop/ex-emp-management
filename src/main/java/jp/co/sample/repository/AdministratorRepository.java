package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
@Autowired
private NamedParameterJdbcTemplate template;
	

/**
 * RowMapperを定義
 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
	= (rs, i) -> {
	Administrator administrator = new Administrator();
	administrator.setId(rs.getInt("id"));
	administrator.setName(rs.getString("name"));
	administrator.setMail_address(rs.getString("mail_address"));
	administrator.setPassword(rs.getString("password"));
	return administrator;	
	};
/**
 * insertメソッドを定義
 * @param administrator
 * @return
 */
	
	public Administrator save(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		if (administrator.getId() == null) {
			String insertSql = "INSERT INTO administrators(id,name,mail_address,password) VALUES(:id,:name,:mail_address,password)";
			template.update(insertSql, param);
		} else {
			String updateSql = "UPDATE administrators SET id=:id,name=:name,mail_address=:mail_address,password=:password";
			template.update(updateSql, param);
		}
		return administrator;
	}
	/**
	 * findByメソッドを定義
	 * @param mailAddress1
	 * @param password
	 * @return
	 */
	
			
		public Administrator findByMailAddressAndPassword(String mailAddress1,String password) {
			String sql = "SELECT * FROM administrators WHERE mail_address=:mail_address,password=:password";
			SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress1).addValue("password", password);
			try {
				return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
			}catch (Exception e) {
				return null;
			}
			
			
			
//			List<Administrator> administratorList = template.query(sql,param,ADMINISTRATOR_ROW_MAPPER);
//			if (administratorList.size() ==0){
//				return null;
//				}
//			return administratorList.get(0);
		}
	
		
		
		
		
		
	
	}
		
	
