package gui.exam;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View extends VBox
{
    // private AddDialog addDialog;

    private AddDialogB addDialogB;

    private Presenter presenter;

    private ListView<Match> lvMatch;

    private ListView<ScoreEntry> lvScoreEntry;

    public View(Presenter presenter)
    {
        this.presenter = presenter;
        initView();
    }

    // Initialisiert die View
    private void initView()
    {
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        HBox hBoxA = new HBox();
        hBoxA.setSpacing(10);

        HBox hBoxB = new HBox();
        hBoxB.setSpacing(10);

        // ListViews
        this.lvMatch = new ListView<Match>();
        this.lvScoreEntry = new ListView<ScoreEntry>();

        // Buttons
        Button btnNewScore = new Button("Neues Spieldergebnis");
        Button btnDelScore = new Button("Spielergebnis löschen");

        // Eventbehandlung
        btnNewScore.setOnAction(e -> newScore());
        btnDelScore.setOnAction(e -> delScore());

        // Interaktionselemente werden eingefügt
        // Listen
        hBoxA.getChildren().addAll(lvMatch, lvScoreEntry);
        // Buttons
        hBoxB.getChildren().addAll(btnNewScore, btnDelScore);
        // Gesamt
        this.getChildren().addAll(hBoxA, hBoxB);
    }

    // fügt ein neues Match in die ListView hinzu
    public void addMatch(Match aM)
    {
        this.presenter.addMatch(aM);
        lvMatch.getItems().add(aM);

    }

    // entfernt ein Match aus der ListView
    public void deleteMatch(Match dM)
    {
        this.presenter.deleteMatch(dM);
        lvMatch.getItems().removeAll(dM);
    }

    // Updated die Scores sobald neue Matches hinzugefügt werden
    public void updateScores(ScoreEntry[] scEntries)
    {
        this.lvScoreEntry.getItems().setAll(scEntries);
    }

    // Erzeugt einen neuen Score
    public void newScore()
    {
        try
        {
            addDialogB = new AddDialogB();
            addDialogB.setView(this);
            addDialogB.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // addDialog = new AddDialog();
        // addDialog.showAndWait().ifPresent(response ->
        // {
        // if (response.getButtonData() == ButtonData.OK_DONE)
        // {
        // addMatch(addDialog.add());
        // }
        // });
    }

    // entfernt einen Score
    public void delScore()
    {
        if (!lvMatch.getSelectionModel().isEmpty())
        {
            deleteMatch(lvMatch.getSelectionModel().getSelectedItem());
        }
        else
        {
            return;
        }
    }
}
