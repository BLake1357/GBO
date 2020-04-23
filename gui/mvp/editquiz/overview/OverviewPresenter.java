package gui.mvp.editquiz.overview;

import gui.mvp.editquiz.model.Model;

public class OverviewPresenter
{
    private OverviewView overviewView;

    private Model questionModel;

    public OverviewPresenter()
    {
    }

    public void setView(OverviewView view)
    {
        this.overviewView = view;
    }

    public OverviewView getView()
    {
        return overviewView;
    }

    public void setQuestionModel(Model questionModel)
    {
        this.questionModel = questionModel;
    }

    /**
     * Die Fragen des Models werden an die OverviewView weitergegeben
     */
    public void questions()
    {
        this.overviewView.showQuestions(this.questionModel.getQuestions());

    }

    /**
     * Ergebnisse Werden Gel�scht
     */
    public void deleteHistory()
    {
        this.questionModel.deleteHistory();
        this.overviewView.deleteQuestions();
        this.questions();
    }
}
