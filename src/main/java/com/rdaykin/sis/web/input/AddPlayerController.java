package com.rdaykin.sis.web.input;

import com.rdaykin.sis.model.team.player.TeamPlayer;
import com.rdaykin.sis.services.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Rob on 02/10/2016.
 */
@Controller
public class AddPlayerController {

    @Autowired
    private TeamService teamService;

    Logger logger = Logger.getLogger(AddPlayerController.class);

    @RequestMapping(value="/addplayer",method= RequestMethod.GET)
    public ModelAndView displayNewPlayerForm(){
        logger.info("Setting up model for new player form");
        String errorMessage = "";
        ModelAndView modelView = new ModelAndView("addplayer");
        TeamPlayer player = new TeamPlayer();
        modelView.addObject("footballTeams",teamService.getFootballTeams().keySet());
        modelView.addObject("teamPlayer",player);
        modelView.addObject("errorMessage",errorMessage);
        return modelView;
    }

    @RequestMapping(value="/addplayer",method=RequestMethod.POST)
	public ModelAndView addPlayerToTeam(@ModelAttribute("teamPlayer") TeamPlayer player){
        ModelAndView modelView = displayNewPlayerForm();
        System.out.println("method reached");
		boolean playerAdded = teamService.addPlayerToTeam(player);
		if(!playerAdded) {
            logger.info("Passing error message to model");
            modelView.addObject("errorMessage","Could not add player "+player.getPlayerName()+" to team "+player.getTeam()+".  This player may already be in this team");
        }
		return modelView;
	}
}
