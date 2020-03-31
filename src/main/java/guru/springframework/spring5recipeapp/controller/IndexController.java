package guru.springframework.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Viktoriya on 01-Mar-20
 */
@Controller
public class IndexController {

	@RequestMapping({"/", "", "/index"})
	public String getIndexPage() {

		System.out.println("Testing 123");
		return "index";
	}
}
