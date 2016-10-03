package com.rdaykin.sis.web.input;

import com.rdaykin.sis.model.team.FootballTeam;
import com.rdaykin.sis.services.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rob on 03/10/2016.
 */
public class TestNewTeamController {

    @Mock
    private TeamService teamService;

    @InjectMocks
    NewTeamController newTeamController;

    @Mock
    Map<String,FootballTeam> footballTeamMap;

    @Mock
    FootballTeam team1;

    @Mock
    FootballTeam team2;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(teamService.getFootballTeams()).thenReturn(footballTeamMap);
        when(teamService.addNewTeam(team1)).thenReturn(true).thenReturn(false);
        when(teamService.addNewTeam(team2)).thenReturn(true).thenReturn(false);
        when(team1.getName()).thenReturn("Team1");
        when(team2.getName()).thenReturn("Team2");
    }

    @Test
    public void testDisplayNewTeamFormCorrectlyAddsElementsToModelAndView(){
        ModelAndView modelAndView = newTeamController.displayNewTeamForm();
        Map<String,Object> model = modelAndView.getModel();
        assertEquals("newteam",modelAndView.getViewName());
        assertEquals("",model.get("errorMessage"));
        assertNotEquals(null,model.get("footballTeam"));
    }

    @Test
    public void testCreateNewTeamCallsTeamServiceAddNewTeamAndDoesNotSetErrorMessageWithNewTeam(){
        ModelAndView modelAndView = newTeamController.createNewTeam(team1);
        Map<String,Object> model = modelAndView.getModel();
        verify(teamService,times(1)).addNewTeam(team1);
        assertEquals("",model.get("errorMessage"));
    }

    @Test
    public void testCreateNewTeamCallsTeamServiceAddNewTeamMultipleTimesForDifferentTeamsAndDoesNotSetErrorMessage(){
        newTeamController.createNewTeam(team1);
        ModelAndView modelAndView = newTeamController.createNewTeam(team2);
        Map<String,Object> model = modelAndView.getModel();
        verify(teamService,times(1)).addNewTeam(team1);
        verify(teamService,times(1)).addNewTeam(team2);
        assertEquals("",model.get("errorMessage"));
    }

    @Test
    public void testCreateNewTeamCallsTeamServiceAddNewTeamMultipleTimesForSameTeamAndDoesSetErrorMessage(){
        newTeamController.createNewTeam(team1);
        ModelAndView modelAndView = newTeamController.createNewTeam(team1);
        Map<String,Object> model = modelAndView.getModel();
        verify(teamService,times(2)).addNewTeam(team1);
        assertTrue(((String) model.get("errorMessage")).contains("Team1"));
    }

}
