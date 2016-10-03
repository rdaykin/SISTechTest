package com.rdaykin.sis.model.team;

import java.util.Set;

/**
 * Created by Rob on 29/09/2016.
 */
public class FootballTeam {

    private String name;
    private String owner;
    private String city;
    private Set<String> players;
    private String competition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    @Override
    public String toString(){
        return getName();
    }
}
