package gui.exam;

public class Presenter
{
    private Model model;

    private View view;

    public Presenter()
    {
    }

    public void addMatch(Match aMatch)
    {
        model.addMatch(aMatch);
        view.updateScores(model.getAllScores());
    }

    public void deleteMatch(Match dMatch)
    {
        model.deleteMatch(dMatch);
        view.updateScores(model.getAllScores());
    }

    public void setModel(Model m)
    {
        this.model = m;
    }

    public void setView(View v)
    {
        this.view = v;
    }
}
