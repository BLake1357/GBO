package gui.mvp.basicquiz.overview;

import java.util.List;

import gui.mvp.basicquiz.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox
{
    private OverviewPresenter presenter;

    private ListView<Question> lvQuestion;

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

        // Interaktionselemente
        lblUebersicht = new Label("�bersicht");
        lvQuestion = new ListView<>();
        lvQuestion.setId("overviewList");
        btnLoeschen = new Button("Ergebnisse l�schen");
        btnLoeschen.setId("deleteHistory");

        // Eventbehandlung
        btnLoeschen.setOnAction(e -> presenter.deleteHistory());

        // Elemente werden in die VBox eingef�gt
        vbQuestion.getChildren().addAll(lblUebersicht, lvQuestion, btnLoeschen);

        // VBox wird in die Haupt Pane der View eingef�gt
        this.getChildren().add(vbQuestion);
    }

    public void setPresenter(OverviewPresenter presenter)
    {
        this.presenter = presenter;
    }

    public void showQuestions(List<Question> question)
    {
        this.lvQuestion.getItems().setAll(question);
    }
}
