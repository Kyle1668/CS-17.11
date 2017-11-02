// Kyle O'Brien
// 10-5-17
// CS 17.11 - Assignment 4
// File Summary: This program implements a team sports team system of three teams
// for various alternative sports, SlamBall, Ultimate Frisbee, and Unicycle
// Hockey. The hierarchy follows that the three different sports teams are
// child classes of the "Team" base class. Each team has a roster of
// players which are instances of the  "TeamMember" class which
// inherits from the Person class.

package edu.srjc.OBrien.Kyle.sports_team_class_system;

public class Main {

    private static void teamTest1() {

        String ultimateFrisbeeDescription = "" +
                "Forget leisurely spinning a disc in the general direction of a friend (or your dog) " +
                "in the park. \"Ultimate\" takes it to a whole new level, with two teams of seven " +
                "trying to get the flying disc into the endzones of a field the size of a football " +
                "pitch. Often featuring mixed-gender teams, the game is non-contact. " +
                "- https://www.bhf.org.uk/heart-matters-magazine/activity/team-sports/10-weird-team-sports";

        String ultimateFrisbeeRules = "Scoring -- Each time the offense completes a pass in the defense's " +
                "endzone, the offense scores a point. Play is initiated after each score. Movement of the " +
                "Disc -- The disc may be advanced in any direction by completing a pass to a teammate. " +
                "Players may not run with the disc. The person with the disc (\"thrower\") has ten " +
                "seconds to throw the disc. The defender guarding the thrower (\"marker\") counts" +
                " out the stall count. - https://www.cs.rochester.edu/u/ferguson/ultimate/ultimate-simple.html";


        UltimateFrisbeeTeam theFireFrisbeees = new UltimateFrisbeeTeam(7, "Ultimate Frisbee", ultimateFrisbeeRules, ultimateFrisbeeDescription);

        theFireFrisbeees.addPlayer(new TeamMember("George", "Washington", 176, 5, 19, 1, "thrower"));
        theFireFrisbeees.addPlayer(new TeamMember("John", "Adams", 190, 5, 66, 3, "thrower"));
        theFireFrisbeees.addPlayer(new TeamMember("Thomas", "Jefferson", 112, 5, 87, 8, "thrower"));
        theFireFrisbeees.addPlayer(new TeamMember("James", "Madison", 100, 5, 20, 3, "thrower"));
        theFireFrisbeees.addPlayer(new TeamMember("James", "Monroe", 289, 5, 29, 10, "thrower"));
        theFireFrisbeees.addPlayer(new TeamMember("John Q", "Adams", 111, 5, 19, 30, "thrower"));
        theFireFrisbeees.addPlayer(new TeamMember("Martin", "Van Buren", 198, 5, 19, 37, "thrower"));

        // Should not allow to add John Tyler (10th president of the United States) since the team size has been reached.

        theFireFrisbeees.addPlayer(new TeamMember("William", "Harrison", 176, 5, 19, 98, "thrower"));

        theFireFrisbeees.print();

    }

    private static void teamTest2() {

        String slamBallDescription = "SlamBall is a form of basketball played with four trampolines in " +
                "front of each net and boards around the court edge. The name SlamBall is the " +
                "trademark of SlamBall, LLC. While SlamBall is based on basketball, it is a " +
                "contact sport, with blocks, collisions and rough physical play a part of " +
                "the game, similar to elements of football and hockey." +
                "- https://en.wikipedia.org/wiki/Slamball";

        String slamBallRules = "Scoring is achieved by putting the ball into the net at the opponent's " +
                "end of the court for points, while preventing the opposing team from doing the same at " +
                "one's own net. The aim is to have outscored the opposing team when the game ends. " +
                "A successful score can be worth two points if the ball is thrown through the hoop " +
                "without the offensive player touching the hoop. Slam dunks are scored three points." +
                "- https://en.wikipedia.org/wiki/Slamball#Rules_and_regulations";

        SlamBallTeam theSlamDunkSlamBallers = new SlamBallTeam(3, "Slam Ball", slamBallDescription, slamBallRules);

        theSlamDunkSlamBallers.addPlayer(new TeamMember("George", "Washington", 176, 5, 19, 1, "Handler"));
        theSlamDunkSlamBallers.addPlayer(new TeamMember("John", "Adams", 190, 5, 66, 3, "Handler"));
        theSlamDunkSlamBallers.addPlayer(new TeamMember("Thomas", "Jefferson", 112, 5, 87, 8, "Stopper"));

        // Should not allow to add John Tyler (11th president of the United States) since the team size has been reached.

        theSlamDunkSlamBallers.addPlayer(new TeamMember("James", "Polk", 176, 5, 19, 98, "Stopper"));

        theSlamDunkSlamBallers.print();

    }

    private static void teamTest3() {

        String unicycleHockeyDescription = "Unicycle hockey is a team sport, similar to roller or inline hockey, " +
                "except that each player must be mounted on a unicycle (with both feet on the pedals) to play " +
                "the ball. A team is composed of five players (plus substitutes), but there is no dedicated " +
                "goalkeeper role (although one player usually stays back in that position).[1]" +
                "- https://en.wikipedia.org/wiki/Unicycle_hockey";

        String unicycleHockeyRules = "The court used is between 35 and 45 metres in length, and 20 to 25 metres wide. " +
                "It should have either beveled or rounded corners, and barriers on all sides. The goals are also set " +
                "back from the end walls so that players can go behind them, similarly to ice hockey. Any stick " +
                "which is legal for ice hockey, other than that of a goalkeeper, can be used. The unicycles can " +
                "have a maximum wheel diameter of 24 inches (61 cm) and a tennis ball is typically used, " +
                "although street hockey balls are also permitted. - https://en.wikipedia.org/wiki/Unicycle_hockey";

        UnicycleHockeyTeam theOneWheeledRoadRagers = new UnicycleHockeyTeam(5, "Unicycle Hockey", unicycleHockeyDescription, unicycleHockeyRules);

        theOneWheeledRoadRagers.addPlayer(new TeamMember("George", "Washington", 176, 5, 19, 1, "Offence"));
        theOneWheeledRoadRagers.addPlayer(new TeamMember("John", "Adams", 190, 5, 66, 3, "Golly"));
        theOneWheeledRoadRagers.addPlayer(new TeamMember("Thomas", "Jefferson", 112, 5, 87, 8, "Offence"));
        theOneWheeledRoadRagers.addPlayer(new TeamMember("James", "Polk", 176, 5, 19, 98, "Offence"));

        theOneWheeledRoadRagers.print();

    }

    private static void printHorizontalRule() {
        System.out.println("\n--------------------------------------------------------------------------------\n");
    }

    public static void main(String[] args) {
        printHorizontalRule();
        teamTest1();
        printHorizontalRule();
        teamTest2();
        printHorizontalRule();
        teamTest3();
    }

}
