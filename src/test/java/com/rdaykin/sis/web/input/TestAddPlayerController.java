package com.rdaykin.sis.web.input;

import com.rdaykin.sis.model.team.FootballTeam;
import com.rdaykin.sis.model.team.player.TeamPlayer;
import com.rdaykin.sis.services.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Rob on 03/10/2016.
 */
public class TestAddPlayerController {

    @Mock
    TeamService teamService;

    @Mock
    HashMap<String,FootballTeam> footballTeams;

    @Mock
    Set<String> players1;

    @Mock
    FootballTeam team1;

    @Mock
    TeamPlayer player;

    @Mock
    Set<String> keySet;

    @InjectMocks
    AddPlayerController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(teamService.getFootballTeams()).thenReturn(footballTeams);
        when(footballTeams.keySet()).thenReturn(keySet);
        when(teamService.addPlayerToTeam(player)).thenReturn(true).thenReturn(false);
        when(player.getTeam()).thenReturn("Team1");
        when(player.getPlayerName()).thenReturn("Team1");
        when(footballTeams.get("Team1")).thenReturn(team1);
        when(team1.getPlayers()).thenReturn(players1);
    }

    @Test
    public void testDisplayNewPlayerFormAddsCorrectElementsToModelAndView(){
        ModelAndView modelAndView = controller.displayNewPlayerForm();
        assertEquals("addplayer",modelAndView.getViewName());
        assertEquals(keySet,modelAndView.getModel().get("footballTeams"));
        assertEquals("",modelAndView.getModel().get("errorMessage"));
        assertNotEquals(null,modelAndView.getModel().get("teamPlayer"));
    }

    @Test
    public void testAddPlayerToTeamAddsPlayerToTeam(){
        ModelAndView modelAndView = controller.addPlayerToTeam(player);
        assertEquals("addplayer",modelAndView.getViewName());
        assertEquals("",modelAndView.getModel().get("errorMessage"));
    }

    @Test
    public void testAddPlayerToTeamDoesNotAddPlayerToTeamWhenSamePlayerIsAddedTwice(){
        controller.addPlayerToTeam(player);
        ModelAndView modelAndView = controller.addPlayerToTeam(player);
        assertEquals("addplayer",modelAndView.getViewName());
        assertNotEquals("",modelAndView.getModel().get("errorMessage"));
    }

}
