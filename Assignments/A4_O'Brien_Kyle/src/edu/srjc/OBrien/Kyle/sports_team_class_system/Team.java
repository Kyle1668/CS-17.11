// Kyle O'Brien
// 10-5-17
// CS 17.11 - Assignment 4
// File Summary: The Team class implements common data members for sports teams.
// Data members have a private scope but can be accessed and set using their
// corresponding methods. Besides of the basic members describing the team
// and rules of the team's sport, the team also has a
// roster which contains "TeamMember" instances.

package edu.srjc.OBrien.Kyle.sports_team_class_system;

import java.util.ArrayList;

public class Team
{
    private int playerCount = 0;
    private int maxPlayerCount = 0;
    private ArrayList<TeamMember> playerRoster = null;

    private String sportName = "";
    private String sportRules = "";
    private String sportDescription = "";

    public Team()
    {
        // Necessary for inheritance
    }

    public Team(int maxPlayerCount, String sportName, String sportRules, String sportDescription)
    {
        this.maxPlayerCount = maxPlayerCount;
        this.sportName = sportName;
        this.sportRules = sportRules;
        this.sportDescription = sportDescription;
    }

    public int getPlayerCount()
    {
        return playerCount;
    }

    public void setPlayerCount(int playerCount)
    {
        if (playerCount >= 0)
        {
            this.playerCount = playerCount;
        }
    }

    public int getMaxPlayerCount()
    {
        return maxPlayerCount;
    }

    public void setMaxPlayerCount(int maxPlayerCount)
    {
        if (maxPlayerCount >= 1)
        {
            this.maxPlayerCount = maxPlayerCount;
        }
    }

    public ArrayList<TeamMember> getPlayerRoster()
    {
        return playerRoster;
    }

    public void setPlayerRoster(ArrayList<TeamMember> playerRoster)
    {
        this.playerRoster = playerRoster;
    }

    public String getSportName()
    {
        return sportName;
    }

    public void setSportName(String sportName)
    {
        this.sportName = sportName;
    }

    public String rules()
    {
        return sportRules;
    }

    public void setSportRules(String sportRules)
    {
        this.sportRules = sportRules;
    }

    public String getSportDescription()
    {
        return sportDescription;
    }

    public void setSportDescription(String sportDescription)
    {
        this.sportDescription = sportDescription;
    }

    void addPlayer(TeamMember newPlayer)
    {
        if (playerRoster == null) {
            playerRoster = new ArrayList<TeamMember>();
            playerRoster.add(newPlayer);
            playerCount++;
        }
        else if (playerRoster.size() < maxPlayerCount)
        {
            playerRoster.add(newPlayer);
            playerCount++;
        }
        else
        {
            System.out.println("Max player count met. \n");
        }
    }

    void removePlayer(int targetPlayerID)
    {
        if (playerRoster != null && playerRoster.size() > 0)
        {
            for (TeamMember player : playerRoster)
            {
                if (player.getPlayerID() == targetPlayerID)
                {
                    playerRoster.remove(player);
                    playerCount--;
                }
                break;
            }
        }
    }

    public void print() {
        System.out.println("Sport: " + sportName);
        System.out.println("Sport Description: " + sportDescription);
        System.out.println("Rules of the sport: " + sportRules);
        System.out.println("Maximum player count: " + maxPlayerCount);
        System.out.println("Current player count: " + playerCount);
    }
}
