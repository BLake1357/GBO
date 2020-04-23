package gui.mvp.basicquiz.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainView extends BorderPane
{
    private MainPresenter mainPresenter;

    private Button btnoverview;

    public MainView()
    {
        initView();
    }

    private void initView()
    {
        // Abstand der BorderPane zum Rand
        this.setPadding(new Insets(10));

        // Hbox fï¿½r den Oberen Bereich der BorderPane
        HBox topArea = new HBox(10);

        Button btnquizStart = new Button("Quiz starten!");
        Button btnquizContinue = new Button("Quiz fortsetzen!");
        btnoverview = new Button("Überblick!");
        btnoverview.setId("overview");

        // Einfï¿½gen der Buttons in die Hbox
        topArea.getChildren().addAll(btnquizStart, btnquizContinue, btnoverview);

        // Eventbehandlung
        btnquizStart.setOnAction(e -> mainPresenter.restartQuiz());
        btnquizContinue.setOnAction(e -> mainPresenter.continueQuiz());
        btnoverview.setOnAction(e -> mainPresenter.showOverviewView());

        // Hbox wird in den oberen Bereich der BorderPane eingefï¿½gt
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
