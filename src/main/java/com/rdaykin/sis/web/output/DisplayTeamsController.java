package com.rdaykin.sis.web.output;

import com.rdaykin.sis.model.team.FootballTeam;
import com.rdaykin.sis.services.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by Rob on 02/10/2016.
 */
@Controller
public class DisplayTeamsController {

    @Autowired
    TeamService teamService;

    Logger logger = Logger.getLogger(DisplayTeamsController.class);

    @RequestMapping(value="/teamnames",method=RequestMethod.GET,produces="application/json" )
    public @ResponseBody Collection<String> displayTeamNamesAsJSON() {
        logger.info("Getting list of team names as JSON");
        return teamService.getFootballTeams().keySet();
    }

    @RequestMapping(value="/teamdetails/all",method= RequestMethod.GET,produces="application/json")
    public @ResponseBody Collection<FootballTeam> displayTeamsAsJSON(){
        logger.info("Getting details of all teams as JSON");
        return teamService.getFootballTeams().values();
    }

    @RequestMapping(value="/teamdetails/{team}",method= RequestMethod.GET,produces ="application/json" )
    public @ResponseBody FootballTeam displaySingleTeamAsJSON(@PathVariable String team) {
        logger.info("Getting details for team "+team);
        return teamService.getFootballTeams().get(team);
    }
}
