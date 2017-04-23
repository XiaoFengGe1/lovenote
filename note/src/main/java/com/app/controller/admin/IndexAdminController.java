package com.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexAdminController {

	@RequestMapping(value = "/index")
	public String list() {System.out.println(11);
		return "/admin/index";
	}
	
}
