package gui.exam;

public class Match
{
    private String teamHome, teamGuest;

    private int goalsHome, goalsGuest;

    public Match(String teamHome, String teamGuest, int goalsHome, int goalsGuest)
    {
        if (teamHome.equals(teamGuest))
        {
            throw new IllegalArgumentException("Gastgeber und G�ste sind gleich");
        }
        if (goalsHome < 0 || goalsGuest < 0)
        {
            throw new IllegalArgumentException("Tore d�rfen nicht negativ sein");
        }
        this.teamHome = teamHome;
        this.teamGuest = teamGuest;
        this.goalsHome = goalsHome;
        this.goalsGuest = goalsGuest;
    }

    public String getTeamHome()
    {
        return teamHome;
    }

    public String getTeamGuest()
    {
        return teamGuest;
    }

    public int getGoalsHome()
    {
        return goalsHome;
    }

    public int getGoalsGuest()
    {
        return goalsGuest;
    }

    public String toString()
    {
        return teamHome + " - " + teamGuest + " " + goalsHome + ":" + goalsGuest;
    }
}
