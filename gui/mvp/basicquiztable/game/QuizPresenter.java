package gui.mvp.basicquiztable.game;

import gui.mvp.basicquiztable.model.Model;
import javafx.scene.layout.Pane;

public class QuizPresenter
{
    private QuizView view;

    private Model model;

    public QuizPresenter()
    {
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void setView(QuizView view)
    {
        this.view = view;
    }

    /**
     * ï¿½bergibt die Quizview
     * 
     * @return
     */
    public QuizView getQuizView()
    {
        this.view.setQuestion(model.getFirst());
        return this.view;
    }

    /**
     * Die nächste Frage wird an die QuizView weiter gegeben
     * 
     * @param question
     * @param answer
     */
    public void next(String question, String answer)
    {
        try
        {
            this.model.setStatistics(question, answer);
            this.view.setQuestion(model.getQuestions().get(view.getIndex()));
        }
        catch (IndexOutOfBoundsException e)
        {
            this.view.endQuiz();
        }
    }

    public Pane restartQuiz()
    {
        this.view.getChildren().clear();
        this.view.initView();
        this.view.setQuestion(model.getFirst());
        this.view.setIndex(1);
        return this.getQuizView();
    }

    public Pane continueQuiz()
    {
        return this.view;
    }
}
