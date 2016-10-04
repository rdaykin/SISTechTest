package com.rdaykin.sis.services;

import com.rdaykin.sis.model.team.FootballTeam;
import com.rdaykin.sis.model.team.player.TeamPlayer;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Rob on 29/09/2016.
 */
public class TeamService {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    private Map<String,FootballTeam> footballTeams = new HashMap<String,FootballTeam>();
    private final Logger logger = Logger.getLogger(TeamService.class);

    public boolean addNewTeam(FootballTeam team){
        if(!footballTeams.containsKey(team.getName())){
            team.setDateCreated(dateFormat.format(cal.getTime()));
            footballTeams.put(team.getName(),team);
            logger.info("Created new team with name: "+team.getName());
            return true;
        }else{
            logger.warn("Failed to create team with name: "+team.getName() +" a team with this name exists already ");
            return false;
        }
    }

    public Map<String,FootballTeam> getFootballTeams(){
        return footballTeams;
    }

    public boolean addPlayerToTeam(TeamPlayer player) {
        String team = player.getTeam();
        if(footballTeams.containsKey(team)){

            if(footballTeams.get(team).getPlayers()==null){
                logger.info("Creating the team players set for team "+team+".");
                footballTeams.get(team).setPlayers(new HashSet<String>());
            }
            boolean success = footballTeams.get(team).getPlayers().add(player.getPlayerName());
            if(success){
                logger.info("Added player " + player.getPlayerName()+ " for team "+team+".");
                return true;
            }else{
                logger.warn("Failed to add player - this player already exists.");
                return false;
            }

        }else{
            logger.warn("Could not add player to team "+team+" - this team does not exist.");
            return false;
        }
    }
}
