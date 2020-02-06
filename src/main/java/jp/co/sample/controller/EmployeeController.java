package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * showList()メソッドの呼び出し、従業員一覧(List)を取得する.
	 * @param model
	 * @return 従業員一覧のページ
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}
	
	/**
	 * 従業員情報の呼び出し
	 * @param id
	 * @param model
	 * @return　従業員情報のページ
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/**
	 * 従業員詳細（扶養人数のみ）を更新する
	 * @param form
	 * @return 従業員リスト
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = new Employee();
		    employee.setId(Integer.parseInt(form.getId()));
		    employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		    return "redirect:/employee/showList";
	}
	

	
	
	
	
}
