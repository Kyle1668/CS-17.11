// Kyle O'Brien
// 10-5-17
// CS 17.11 - Assignment 4
// File Summary: The team member class is a child of the "Person" class.
// Because of it, it inherits ass the attributes and behaviors of its
// parent as well as adds additional relevant attributes as an
// athlete. These attributes include the playerID
// and role (position) on the team.

package edu.srjc.OBrien.Kyle.sports_team_class_system;

public class TeamMember extends Person
{
    private int playerID = 0;
    private String role = "";

    public TeamMember(int playerID, String role)
    {
        this.playerID = playerID;
        this.role = role;
    }

    public TeamMember(String firstName, String lastName, float weight, float height, int age, int playerID, String role)
    {
        super(firstName, lastName, weight, height, age);
        this.playerID = playerID;
        this.role = role;
    }

    public int getPlayerID()
    {
        return playerID;
    }

    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

}
