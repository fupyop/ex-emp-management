package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * insertメソッドを追加
	 */
	public void insert(Administrator administrator) {
		administratorRepository.save(administrator);
	}
	
	/**
	 * ログイン処理の記述
	 * findByMailAddressAndPasswordメソッドの呼び出し
	 * @param mailAddress
	 * @param password
	 * @return
	 */
	public Administrator login(String mailAddress,String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
	
	
	
}
