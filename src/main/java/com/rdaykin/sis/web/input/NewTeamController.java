package com.rdaykin.sis.web.input;

import com.rdaykin.sis.model.team.FootballTeam;
import com.rdaykin.sis.services.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewTeamController {
	
	Logger logger = Logger.getLogger(NewTeamController.class);

	@Autowired
	private TeamService teamService;
	
	@RequestMapping(value="/newteam",method=RequestMethod.GET)
	public ModelAndView displayNewTeamForm(){
		logger.info("Setting up model for new team form");
		ModelAndView modelView = new ModelAndView("newteam");
		modelView.addObject("errorMessage","");
		modelView.addObject("footballTeam",new FootballTeam());
		return modelView;
	}
	
	@RequestMapping(value="/newteam",method=RequestMethod.POST)
	public ModelAndView createNewTeam(@ModelAttribute("footballTeam") FootballTeam team){
		ModelAndView modelAndView = displayNewTeamForm();
		boolean newTeamCreated = teamService.addNewTeam(team);
		if(!newTeamCreated) {
			logger.warn("Sending error message to model");
			modelAndView.addObject("errorMessage","Could not create team with name "+team.getName()+".  Team already exists");
		}
		return modelAndView;
	}
	
}
