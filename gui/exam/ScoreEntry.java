package gui.exam;

public class ScoreEntry
{
    private String team;

    private int score;

    private String goalpercentage;

    private int goalsA;

    private int goalsB;

    private int win;

    private int draw;

    private int loss;

    public ScoreEntry(String team, int goalsActive, int goalsPassive)
    {
        if (team == null || team.length() == 0)
        {
            throw new IllegalArgumentException("Ungültige Team-Kennung");
        }
        if (goalsActive < 0 || goalsPassive < 0)
        {
            throw new IllegalArgumentException("Negative Torezahl");
        }
        this.team = team;
        this.goalsA = goalsActive;
        this.goalsB = goalsPassive;
        this.goalpercentage = goalsActive + ":" + goalsPassive;
        update(goalsActive, goalsPassive);
    }

    private void update(int gA, int gP)
    {
        if (gA > gP)
        {
            win += 1;
            score += 3;
        }
        else if (gA == gP)
        {
            draw += 1;
            score += 1;
        }
        else
        {
            loss += 1;
        }
    }

    public void add(ScoreEntry se)
    {
        if (!team.equals(se.team))
        {
            return;
        }
        score += se.score;
        win += se.win;
        draw += se.draw;
        loss += se.loss;
        goalsA += se.goalsA;
        goalsB += se.goalsB;
        goalpercentage = goalsA + ":" + goalsB;
    }

    public void subtract(ScoreEntry se)
    {
        if (!team.equals(se.team))
        {
            return;
        }
        score -= se.score;
    }

    public String getTeam()
    {
        return team;
    }

    public int getScore()
    {
        return score;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof ScoreEntry))
        {
            return false;
        }
        return team.equals(((ScoreEntry) o).team);
    }

    @Override
    public String toString()
    {
        return team + ": " + score + " (" + goalpercentage + " " + win + "/" + draw + "/" + loss + ")";
    }
}
