package com.rdaykin.sis.web.input;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {

	Logger logger = Logger.getLogger(HomePageController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		logger.info("Getting homepage.");
		return "homepage";
	}

}
