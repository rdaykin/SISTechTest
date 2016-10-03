package com.rdaykin.sis.services;

import com.rdaykin.sis.model.team.FootballTeam;
import com.rdaykin.sis.model.team.player.TeamPlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rob on 03/10/2016.
 */
public class TestTeamService {

    @Mock
    FootballTeam team1;

    @Mock
    FootballTeam team2;

    @Spy
    FootballTeam spyTeam1;

    @Spy
    FootballTeam spyTeam2;

    @Mock
    TeamPlayer player1;

    @Mock
    TeamPlayer player2;

    @Mock
    TeamPlayer player3;

    @Mock
    TeamPlayer player4;

    TeamService teamService = new TeamService();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(team1.getName()).thenReturn("Team1");
        when(team2.getName()).thenReturn("Team2");
        when(player1.getTeam()).thenReturn("SpyTeam1");
        when(player1.getPlayerName()).thenReturn("Player1");
        when(player2.getTeam()).thenReturn("SpyTeam1");
        when(player2.getPlayerName()).thenReturn("Player2");
        when(player3.getTeam()).thenReturn("SpyTeam2");
        when(player3.getPlayerName()).thenReturn("Player3");
        when(player4.getTeam()).thenReturn("WrongTeam");
        when(player4.getPlayerName()).thenReturn("Player4");
        spyTeam1.setName("SpyTeam1");
        spyTeam2.setName("SpyTeam2");
    }


    @Test
    public void testAddNewTeamAddsTeamToEmptyTeamListAndReturnsTrue(){
        boolean teamAdded = teamService.addNewTeam(team1);
        assertTrue(teamAdded);
        assertEquals(team1,teamService.getFootballTeams().get("Team1"));
    }

    @Test
    public void testAddNewTeamDoesNotAddTeamWithDuplicateNameToEmptyTeamList(){
        teamService.addNewTeam(team1);
        boolean teamAdded = teamService.addNewTeam(team1); //should return false as team with name "Team1" already in TeamList
        assertFalse(teamAdded);
        assertEquals(team1,teamService.getFootballTeams().get("Team1"));
        assertTrue(teamService.getFootballTeams().size()==1);
    }

    @Test
    public void testAddNewTeamAddsDifferentTeamsToTeamList(){
        teamService.addNewTeam(team1);
        boolean teamAdded = teamService.addNewTeam(team2); //should return true as team with name "Team2" not yet in TeamList
        assertTrue(teamAdded);
        assertEquals(team1,teamService.getFootballTeams().get("Team1"));
        assertEquals(team2,teamService.getFootballTeams().get("Team2"));
        assertTrue(teamService.getFootballTeams().size()==2);
    }

    @Test
    public void testAddPlayerToTeamCreatesNewPlayerListForNullPlayerListAndAddsPlayer(){
        teamService.addNewTeam(spyTeam1);
        boolean playerAdded = teamService.addPlayerToTeam(player1);
        verify(spyTeam1,times(1)).setPlayers(anySet());
        assertTrue(playerAdded);
        assertTrue(teamService.getFootballTeams().get("SpyTeam1").getPlayers().contains("Player1"));
    }

    @Test
    public void testAddPlayerToTeamDoesNotCreateNewPlayerListWhenPlayerListExistsAndAddsPlayer(){
        teamService.addNewTeam(spyTeam1);
        teamService.addPlayerToTeam(player1);
        boolean playerAdded = teamService.addPlayerToTeam(player2);
        verify(spyTeam1,times(1)).setPlayers(anySet());
        assertTrue(playerAdded);
        assertTrue(teamService.getFootballTeams().get("SpyTeam1").getPlayers().contains("Player1"));
        assertTrue(teamService.getFootballTeams().get("SpyTeam1").getPlayers().contains("Player2"));
    }

    @Test
    public void testAddPlayerToTeamAddsPlayersToCorrectTeams(){
        teamService.addNewTeam(spyTeam1);
        teamService.addNewTeam(spyTeam2);
        teamService.addPlayerToTeam(player1);
        teamService.addPlayerToTeam(player2);
        teamService.addPlayerToTeam(player3);
        assertTrue(teamService.getFootballTeams().get("SpyTeam1").getPlayers().contains("Player1"));
        assertTrue(teamService.getFootballTeams().get("SpyTeam1").getPlayers().contains("Player2"));
        assertTrue(teamService.getFootballTeams().get("SpyTeam2").getPlayers().contains("Player3"));
        assertFalse(teamService.getFootballTeams().get("SpyTeam2").getPlayers().contains("Player1"));
        assertFalse(teamService.getFootballTeams().get("SpyTeam2").getPlayers().contains("Player2"));
        assertFalse(teamService.getFootballTeams().get("SpyTeam1").getPlayers().contains("Player3"));
    }

    @Test
    public void testAddPlayerToTeamDoesNotAddDuplicatePlayersToTeam(){
        teamService.addNewTeam(spyTeam1);
        teamService.addPlayerToTeam(player1);
        boolean playerAdded = teamService.addPlayerToTeam(player1);
        assertFalse(playerAdded);
        assertTrue(teamService.getFootballTeams().get("SpyTeam1").getPlayers().contains("Player1"));
        assertEquals(1,teamService.getFootballTeams().get("SpyTeam1").getPlayers().size());
    }

    @Test
    public void testAddPlayerToTeamReturnsFalseForInvalidTeam(){
        teamService.addNewTeam(spyTeam1);
        boolean playerAdded = teamService.addPlayerToTeam(player4);
        assertFalse(playerAdded);
    }


}
