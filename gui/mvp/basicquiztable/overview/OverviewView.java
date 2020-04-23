package gui.mvp.basicquiztable.overview;

import java.util.List;

import gui.mvp.basicquiztable.model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox
{
    private OverviewPresenter presenter;

    private ObservableList<Question> data;

    private TableView<Question> tvQuestion;

    private Button btnLoeschen;

    private Label lblUebersicht;

    public OverviewView()
    {
        initView();
    }

    private void initView()
    {
        this.setSpacing(10);

        // VBox f�r die ListView und den l�schen Button
        VBox vbQuestion = new VBox(10);

        // ObservableList zum initialisieren der TableView
        this.data = FXCollections.observableArrayList();

        // Interaktionselemente
        this.lblUebersicht = new Label("�bersicht");
        this.tvQuestion = new TableView<Question>(data);
        this.tvQuestion.setId("overviewTable");
        this.btnLoeschen = new Button("Ergebnisse l�schen");
        this.btnLoeschen.setId("deleteHistory");

        // Konfiguration der TableView
        // Spalte f�r die Fragen
        TableColumn<Question, String> questionCol = new TableColumn<Question, String>("Frage:");
        // SimpleStringProperty wird ben�tigt
        questionCol.setCellValueFactory(cdf -> cdf.getValue().getQuestionProperty());
        tvQuestion.getColumns().add(questionCol);
        questionCol.setId("questionCol");

        // Spalte f�r die Anzahl der gegebenen Antworten
        TableColumn<Question, Number> answerCol = new TableColumn<Question, Number>("Antworten:");
        answerCol.setCellValueFactory(cdf -> cdf.getValue().getAnswerProperty());
        tvQuestion.getColumns().add(answerCol);
        answerCol.setId("totalAnswerCol");

        // Spalte f�r die Anzahl der korrekten Antworten
        TableColumn<Question, Number> correctAnswerCol = new TableColumn<Question, Number>("davon korrekt: ");
        correctAnswerCol.setCellValueFactory(cdf -> cdf.getValue().getCorrectAnswerProperty());
        tvQuestion.getColumns().add(correctAnswerCol);
        correctAnswerCol.setId("correctAnswerCol");

        // Leere Spalte wird entfernt
        tvQuestion.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Eventbehandlung
        this.btnLoeschen.setOnAction(e -> presenter.deleteHistory());
        // Elemente werden in die VBox eingef�gt
        vbQuestion.getChildren().addAll(lblUebersicht, tvQuestion, btnLoeschen);

        // VBox wird in die Haupt Pane der View eingef�gt
        this.getChildren().add(vbQuestion);
    }

    public void setPresenter(OverviewPresenter presenter)
    {
        this.presenter = presenter;
    }

    /**
     * Fragen werden in die TableView eingef�gt
     * 
     * @param question
     */
    public void showQuestions(List<Question> question)
    {
        this.data.setAll(question);
    }

    /**
     * L�scht fragen aus der ObservableList
     */
    public void deleteQuestions()
    {
        this.data.clear();
    }
}
