package gui.mvp.editquiz.editor;

import java.util.List;

import gui.mvp.editquiz.model.Question;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorView extends VBox
{
    private EditorPresenter editorPresenter;

    private Alert alert;

    private ListView<Question> lvQuestion; // ListView mit allen Fragen

    /**
     * Konstruktor mit initView aufruf
     */
    public EditorView()
    {
        initView();
    }

    /**
     * Aufbau der Oberfläche
     */
    public void initView()
    {
        this.setSpacing(10);

        // Hbox für die Buttons
        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(10)); // 10 Pixel Abstand zum Rand
        buttonBox.setSpacing(10); // Abstand zwischen den Elementen

        // Interaktionselemente
        Label lblEditor = new Label("Editor");
        lvQuestion = new ListView<Question>();
        lvQuestion.setId("editorList");
        Button btnAdd = new Button("Frage Hinzuf\u00fcgen");
        btnAdd.setId("addQuestion");
        Button btnEdit = new Button("Frage \u00c4ndern");
        btnEdit.setId("editQuestion");
        Button btnDelete = new Button("Frage l\u00f6schen");
        btnDelete.setId("deleteQuestion");

        // Buttons werden in die HBox eingefügt
        buttonBox.getChildren().addAll(btnAdd, btnEdit, btnDelete);

        // Alle Elemente werden in die VBox eingefügt
        this.getChildren().addAll(lblEditor, lvQuestion, buttonBox);

        // Eventbehandlung
        btnAdd.setOnAction(e -> addQuestion());
        btnEdit.setOnAction(e -> editQuestion());
        btnDelete.setOnAction(e -> deleteQuestion());
    }

    public void setPresenter(EditorPresenter editor)
    {
        this.editorPresenter = editor;
    }

    /**
     * Öffnet Dialog zum Hinzufügen neuer Fragen
     */
    public void addQuestion()
    {
        editorPresenter.showAddDialog();
    }

    /**
     * Öffnet Dialog zum editieren einer Frage
     */
    public void editQuestion()
    {
        // Dialog falls keine Frage ausgewählt wurde
        if (lvQuestion.getSelectionModel().getSelectedItem() == null)
        {
            // Dialog informiert Nutzer, dass eine Frage ausgewählt werden muss
            this.alert = new Alert(AlertType.INFORMATION);
            this.alert.setHeaderText(null);
            this.alert.setContentText("Es muss eine Frage ausgew\u00e4hlt werden!");
            this.alert.show();
        }
        else
        {
            // Ruft den Dialog der DialogView auf
            this.editorPresenter.showEditDialog(lvQuestion.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Öffnet Dialog zum löschen einer Frage
     */
    public void deleteQuestion()
    {
        // Dialog falls keine Frage ausgewählt wurde
        if (lvQuestion.getSelectionModel().getSelectedItem() == null)
        {
            // Dialog informiert Nutzer, dass eine Frage ausgewählt werden muss
            this.alert = new Alert(AlertType.INFORMATION);
            this.alert.setHeaderText(null);
            this.alert.setContentText("Es muss eine Frage ausgew\u00e4hlt werden!");
            this.alert.show();
        }
        else
        {
            this.alert = new Alert(AlertType.CONFIRMATION);
            this.alert.setHeaderText(null);
            this.alert.setContentText("Soll diese Frage wirklich gel\u00f6scht werden?");
            alert.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK)
                {
                    this.editorPresenter.delete(lvQuestion.getSelectionModel().getSelectedItem());
                    deleteFromLV(lvQuestion.getSelectionModel().getSelectedItem());
                }
            });
        }
    }

    /**
     * Fügt die Fragen in die ListView ein
     * 
     * @param questions
     */
    public void showQuestions(List<Question> questions)
    {
        lvQuestion.getItems().setAll(questions);
    }

    /**
     * Löscht eine Frage aus der ListView
     * 
     * @param question
     */
    private void deleteFromLV(Question question)
    {
        lvQuestion.getItems().remove(question);
    }
}
