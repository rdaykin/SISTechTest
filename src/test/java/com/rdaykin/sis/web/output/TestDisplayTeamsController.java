package com.rdaykin.sis.web.output;

import com.rdaykin.sis.model.team.FootballTeam;
import com.rdaykin.sis.services.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rob on 03/10/2016.
 */
public class TestDisplayTeamsController {

    @Mock
    TeamService teamService;

    @Mock
    Map<String,FootballTeam> footballTeamMap;

    @Mock
    Collection<FootballTeam> footballTeams;

    @Mock
    Set<String> footballTeamNames;

    @Mock
    FootballTeam team1;

    @InjectMocks
    DisplayTeamsController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(teamService.getFootballTeams()).thenReturn(footballTeamMap);
        when(footballTeamMap.values()).thenReturn(footballTeams);
        when(footballTeamMap.keySet()).thenReturn(footballTeamNames);
        when(footballTeamMap.get("Team1")).thenReturn(team1);
    }


    @Test
    public void testDisplayTeamsAsJSONReturnsValuesOfFootballTeams(){
        Collection<FootballTeam> footballTeamsOutput = controller.displayTeamsAsJSON();
        verify(teamService,times(1)).getFootballTeams();
        verify(footballTeamMap,times(1)).values();
        assertEquals(footballTeams,footballTeamsOutput);
    }

    @Test
    public void testDisplayTeamNamesAsJSONReturnsNamesOfFootballTeams(){
        Collection<String> teamNamesOutput = controller.displayTeamNamesAsJSON();
        verify(teamService,times(1)).getFootballTeams();
        verify(footballTeamMap,times(1)).keySet();
        assertEquals(footballTeamNames,teamNamesOutput);
    }

    @Test
    public void testDisplaySingleTeamDetailsAsJSONReturnsDetailsOfSpecifiedFootballTeam(){
        FootballTeam teamOutput = controller.displaySingleTeamAsJSON("Team1");
        verify(teamService,times(1)).getFootballTeams();
        verify(footballTeamMap,times(1)).get("Team1");
        assertEquals(team1,teamOutput);
    }
}
