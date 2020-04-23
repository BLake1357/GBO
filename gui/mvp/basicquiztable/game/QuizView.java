package gui.mvp.basicquiztable.game;

import gui.mvp.basicquiztable.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuizView extends VBox
{
    private QuizPresenter quizPresenter;

    private Label lblQuestion;

    private RadioButton[] rbuttons = new RadioButton[4];

    private RadioButton rbAnswerA;

    private RadioButton rbAnswerB;

    private RadioButton rbAnswerC;

    private RadioButton rbAnswerD;

    private Button btnNExt;

    private int index = 1;

    private VBox radioBox;

    private VBox pane;

    public QuizView()
    {
        initView();
    }

    public void initView()
    {
        this.setSpacing(10);

        // VBox f�r den Text
        this.pane = new VBox(10);

        // VBox f�r die Radio Buttons mit padding 10
        this.radioBox = new VBox(10);

        // Interaktionselemente
        this.lblQuestion = new Label();
        this.lblQuestion.setId("question");
        this.rbAnswerA = new RadioButton();
        this.rbAnswerB = new RadioButton();
        this.rbAnswerC = new RadioButton();
        this.rbAnswerD = new RadioButton();
        ToggleGroup tGroup = new ToggleGroup();
        btnNExt = new Button("=>");

        // Radio Buttons werden zur ToggleGroup hinzugef�gt
        this.rbAnswerA.setToggleGroup(tGroup);
        this.rbAnswerB.setToggleGroup(tGroup);
        this.rbAnswerC.setToggleGroup(tGroup);
        this.rbAnswerD.setToggleGroup(tGroup);

        // Array wird bef�llt
        this.rbuttons[0] = rbAnswerA;
        this.rbuttons[1] = rbAnswerB;
        this.rbuttons[2] = rbAnswerC;
        this.rbuttons[3] = rbAnswerD;

        // Elemente werden in die Pane eingef�gt
        this.radioBox.getChildren().addAll(rbAnswerA, rbAnswerB, rbAnswerC, rbAnswerD);

        this.pane.getChildren().addAll(lblQuestion, radioBox);

        // Vbox wird eingef�gt
        this.getChildren().addAll(pane, btnNExt);

        // Eventbehandlung des Weiter Buttons
        btnNExt.setOnAction(e -> handle());
    }

    /**
     * Behandlung wenn der Weiter Button gedr�ckt wird
     */
    private void handle()
    {
        for (int i = 0; i < this.rbuttons.length; i++)
        {
            if (this.rbuttons[i].isSelected())
            {
                this.quizPresenter.next(this.lblQuestion.getText(), this.rbuttons[i].getText());
                rbuttons[i].setSelected(false);
            }
        }
        this.quizPresenter.next(this.lblQuestion.getText(), "");
        this.index++;
    }

    public int getIndex()
    {
        return this.index;
    }

    /**
     * Label und Radio Buttons erhalten den Text
     * 
     * @param question
     */
    public void setQuestion(Question question)
    {
        this.lblQuestion.setText(question.getQuestion());
        this.lblQuestion.setFont(new Font(30));
        for (int i = 0; i < rbuttons.length; i++)
        {
            rbuttons[i].setText(question.getPossibleAnswers()[i]);
        }
    }

    /**
     * Anzeige am Ende des Quiz
     */
    public void endQuiz()
    {
        this.pane.getChildren().remove(this.radioBox);
        this.lblQuestion.setText("Ende des Quiz");
        this.btnNExt.setDisable(true);

    }

    /**
     * Presenter wird gesetzt
     * 
     * @param quizPresenter
     */
    public void setQuizPresenter(QuizPresenter quizPresenter)
    {
        this.quizPresenter = quizPresenter;
    }

    public void setIndex(int i)
    {
        this.index = i;
    }
}
