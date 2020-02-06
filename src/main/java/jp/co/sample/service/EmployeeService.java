package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員リストから全件検索
	 * employeeRepositoryのfindAllメソッドの呼び出し.
	 * @return employeeRepository
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 従業員情報の取得
	 * @param id
	 * @return
	 */
	public Employee showDetail (Integer id) {
		return employeeRepository.load(id);
	}
	
	
	
}


