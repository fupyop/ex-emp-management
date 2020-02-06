package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 * 
 * @author fuka
 *
 */
@Repository
public class AdministratorRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * RowMapperを定義
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	/**
	 * 管理者情報を挿入する.
	 * 
	 * @param administrator 管理者情報
	 * @return　管理者情報
	 */
	public Administrator save(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String insertSql = "INSERT INTO administrators(name,mail_address,password) VALUES(:name,:mailAddress,:password)";
		template.update(insertSql, param);

		return administrator;
	}

	/**
	 * メールアドレスとパスワードを検索.
	 * 
	 * @param mailAddress1　メールアドレス
	 * @param password　パスワード
	 * @return 管理者情報(１件も存在しない場合はnullが返る)
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress1, String password) {
		String sql = "SELECT * FROM administrators WHERE mail_address=:mail_address AND password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress1)
				.addValue("password", password);
		try {
			Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
			return administrator;
		} catch (Exception e) {
			return null;
		}

		// 別の書き方
//			List<Administrator> administratorList = template.query(sql,param,ADMINISTRATOR_ROW_MAPPER);
//			if (administratorList.size() ==0){
//				return null;
//				}
//			return administratorList.get(0);
	}

}
