package gui.mvp.editquiz.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainView extends BorderPane
{
    private MainPresenter mainPresenter;

    public MainView()
    {
        initView();
    }

    private void initView()
    {
        // Abstand der BorderPane zum Rand
        this.setPadding(new Insets(10));

        // Hbox f�r den Oberen Bereich der BorderPane
        HBox topArea = new HBox(10);

        Button btnquizStart = new Button("Quiz starten!");
        Button btnquizContinue = new Button("Quiz fortsetzen!");
        Button btnoverview = new Button("�berblick!");
        btnoverview.setId("overview");
        Button btnEdit = new Button("Quiz editieren!");

        // Einf�gen der Buttons in die Hbox
        topArea.getChildren().addAll(btnquizStart, btnquizContinue, btnoverview, btnEdit);

        // Eventbehandlung
        btnquizStart.setOnAction(e ->
        {
            btnquizContinue.setDisable(false);
            mainPresenter.restartQuiz();
        });
        btnquizContinue.setOnAction(e -> mainPresenter.continueQuiz());
        btnoverview.setOnAction(e ->
        {
            btnquizContinue.setDisable(false);
            mainPresenter.showOverviewView();
        });
        btnEdit.setOnAction(e ->
        {
            btnquizContinue.setDisable(true);
            mainPresenter.showEditorView();
        });

        // Hbox wird in den oberen Bereich der BorderPane eingef�gt
        this.setTop(topArea);
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public void setContent(Pane content)
    {
        setCenter(content);
        setMargin(content, new Insets(20));
    }
}
