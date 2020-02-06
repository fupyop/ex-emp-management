package jp.co.sample.repository;


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
	administrator.setMailAddress(rs.getString("mail_address"));
	administrator.setPassword(rs.getString("password"));
	return administrator;	
	};
/**
 *　管理者情報を挿入し、insertメソッドを定義.
 * @param administrator
 * @return administrator
 */
	
	public Administrator save(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
			String insertSql = "INSERT INTO administrators(name,mail_address,password) VALUES(:name,:mailAddress,:password)";
			template.update(insertSql, param);

		return administrator;
	}
	/**
	 * メールアドレスとパスワードを検索（findByメソッドを定義）.
	 * @param mailAddress1
	 * @param password
	 * @return null
	 */
	
			
		public Administrator findByMailAddressAndPassword(String mailAddress1,String password) {
			String sql = "SELECT * FROM administrators WHERE mail_address=:mail_address AND password=:password";
			SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress1).addValue("password", password);
			try {
				return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
			}catch (Exception e) {
				return null;
			}
			
			
			//別の書き方
//			List<Administrator> administratorList = template.query(sql,param,ADMINISTRATOR_ROW_MAPPER);
//			if (administratorList.size() ==0){
//				return null;
//				}
//			return administratorList.get(0);
		}
	
	}
		
	
