package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private HttpSession session;
	
	/**
	 * InsertAdministratorFormをインスタンス化
	 * @return
	 */
	@ModelAttribute
	InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
		}
	
	/**
	 * LoginFormをインスタンス化
	 * @return LoginForm
	 */
	@ModelAttribute
	LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 次の画面(administrator/insert)に遷移
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
		
	/**
	 * 次の画面(administrator/login)に遷移
	 * @return
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	
	/**
	 * 管理者情報を挿入(insertメソッドを追加).
	 * @param form
	 * @return redirect
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
//		administrator.setName(form.getName());
//		administrator.setMailAddress(form.getMailAddress());
//		administrator.setPassword(form.getPassword());
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	/**
	 * loginメソッドを定義.
	 * @param form
	 * @param model
	 * @return /administrator/login
	 * 			forward:/employee/showList
	 */
	
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		if (administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です。");
			return toLogin();
		}
	
		session.setAttribute("administratorName", administrator);
		return "forward:/employee/showList";
	}
	
}

	
	
	



