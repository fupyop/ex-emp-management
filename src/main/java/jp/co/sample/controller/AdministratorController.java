package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * InsertAdministratorFormをインスタンス化
	 * @return
	 */
	@ModelAttribute
	InsertAdministratorForm setUpForm() {
		return new InsertAdministratorForm();
	}
	/*
	 * 次の画面に遷移
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	

	
	/**
	 * insertメソッドを追加
	 * @param form
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	
	
	
}

	
	
	



