package gui.mvp.editquiz.editor;

import gui.mvp.editquiz.model.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DialogView extends Dialog<ButtonType>
{
    private EditorPresenter editorPresenter;

    private BorderPane bPane;

    private VBox vBox;

    private HBox hBox;

    private DialogPane dPane;

    private TextArea taQuestion;

    private ToggleGroup tgAnswers;

    private RadioButton[] rbAnswers = new RadioButton[0];

    private TextField[] tfAnswers = new TextField[0];

    private Button[] btnDelete = new Button[0];

    private GridPane answerPane;

    private Button btnAdd;

    private Button btnEdit;

    private Button btnCancel;

    /**
     * Allgemeiner Konstruktor mit initView Aufruf
     */
    public DialogView(EditorPresenter editorPresenter)
    {
        this.editorPresenter = editorPresenter;
        this.setTitle("Dialog");
    }

    /**
     * Pane zum editieren einer Frage
     * 
     * @param question
     */
    public void initEditView(Question question)
    {
        this.dPane = new DialogPane();
        this.dPane.setPadding(new Insets(10)); // 10 Pixel Abstand zum Rand
        setHeaderText(null);

        // BorderPane
        this.bPane = new BorderPane();

        // VBox
        this.vBox = new VBox();

        // HBox
        this.hBox = new HBox();

        // GridPane für die Antworten
        this.answerPane = new GridPane();
        this.answerPane.setPadding(new Insets(10));

        // Interaktionselemente
        Label lblQuestion = new Label("Frage:");
        this.taQuestion = new TextArea(question.getQuestion()); // TextArea mit
                                                                // der Frage
        this.taQuestion.setId("dialogQuestion");
        Button btnAddAnswer = new Button("Antwort hinzuf\u00fcgen");
        this.tgAnswers = new ToggleGroup();
        this.btnEdit = new Button("\u00c4ndern");
        this.btnCancel = new Button("Abbrechen");
        this.rbAnswers = new RadioButton[question.getPossibleAnswers().length];
        this.tfAnswers = new TextField[question.getPossibleAnswers().length];
        this.btnDelete = new Button[question.getPossibleAnswers().length];

        // Eventbehandlung
        btnAddAnswer.setOnAction(e -> addAnswer());
        this.btnEdit.setOnAction(e -> edit(question));
        this.btnCancel.setOnAction(e -> cancel());

        // Erzeugung der RadioButtons
        for (int i = 0; i < rbAnswers.length; i++)
        {
            this.rbAnswers[i] = new RadioButton();
            this.rbAnswers[i].setToggleGroup(tgAnswers);

            if (i == question.getIndexOfCorrectAnswer())
            {
                this.rbAnswers[i].setSelected(true);
            }
        }

        // Erzeugung der Textfelder
        for (int i = 0; i < tfAnswers.length; i++)
        {
            this.tfAnswers[i] = new TextField(question.getPossibleAnswers()[i]);
            this.tfAnswers[i].setEditable(true);
        }

        // Erzeugung der Buttons
        for (int i = 0; i < btnDelete.length; i++)
        {
            this.btnDelete[i] = new Button("L\u00f6schen");
            String s = tfAnswers[i].getText();
            int index = i;
            this.btnDelete[i].setOnAction(e -> deleteAnswer(s, index));
        }

        // Elemente werden in die GridPane eingefügt
        this.answerPane.addColumn(0, rbAnswers);
        this.answerPane.addColumn(1, tfAnswers);
        this.answerPane.addColumn(2, btnDelete);

        vBox.getChildren().addAll(lblQuestion, taQuestion, btnAddAnswer, this.answerPane);

        hBox.getChildren().addAll(btnEdit, btnCancel);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        bPane.setCenter(vBox);
        bPane.setBottom(hBox);

        this.dPane.setContent(bPane);
        this.setDialogPane(dPane);
    }

    /**
     * Pane zum hinzufügen einer neuen Frage
     */
    public void initAddView()
    {
        this.dPane = new DialogPane();
        this.dPane.setPadding(new Insets(10)); // 10 Pixel Abstand zum Rand

        this.answerPane = new GridPane();

        // BorderPane
        this.bPane = new BorderPane();

        // VBox
        this.vBox = new VBox();

        // HBox
        this.hBox = new HBox();

        // GridPane für die Antworten
        this.answerPane = new GridPane();
        this.answerPane.setPadding(new Insets(10));

        // Interaktionselemente
        Label lblQuestion = new Label("Frage:");
        this.taQuestion = new TextArea(); // TextArea für die Frage
        this.taQuestion.setId("dialogQuestion");
        Button btnAddAnswer = new Button("Antwort hinzuf\u00fcgen");
        this.btnAdd = new Button("Hinzuf\u00fcgen");
        this.tgAnswers = new ToggleGroup();
        this.btnEdit = new Button("\u00c4ndern");
        this.btnCancel = new Button("Abbrechen");

        // Erzeugung der RadioButtons
        for (int i = 0; i < rbAnswers.length; i++)
        {
            this.rbAnswers[i] = new RadioButton();
            this.rbAnswers[i].setToggleGroup(tgAnswers);
        }

        // Erzeugung der Textfelder
        for (int i = 0; i < tfAnswers.length; i++)
        {
            this.tfAnswers[i] = new TextField();
            this.tfAnswers[i].setEditable(true);
        }

        // Erzeugung der Buttons
        for (int i = 0; i < btnDelete.length; i++)
        {
            this.btnDelete[i] = new Button("L\u00f6schen");
            String s = tfAnswers[i].getText();
            int index = i;
            this.btnDelete[i].setOnAction(e -> deleteAnswer(s, index));
        }

        // Eventbehandlung
        btnAddAnswer.setOnAction(e -> addAnswer());
        this.btnAdd.setOnAction(e -> add());
        this.btnCancel.setOnAction(e -> cancel());

        // Elemente werden in die GridPane eingefügt
        this.answerPane.addColumn(0, rbAnswers);
        this.answerPane.addColumn(1, tfAnswers);
        this.answerPane.addColumn(2, btnDelete);

        // Obere Elemente werden in die VBox eingefügt
        vBox.getChildren().addAll(lblQuestion, taQuestion, btnAddAnswer, this.answerPane);

        // Cancel und add Button werden in die Hbox eingefügt
        hBox.getChildren().addAll(btnAdd, btnCancel);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        // Boxen werden in die BorderPane eigefügt
        bPane.setCenter(vBox);
        bPane.setBottom(hBox);

        // BorderPane wird in die DialogPane eigefügt
        this.dPane.setContent(bPane);
        this.setDialogPane(dPane);
    }

    /**
     * Editiert eine Frage
     */
    public void edit(Question question)
    {
        // indexOfCorrectAnswer
        int index = 0;

        // possibleAnswers
        String[] answers = new String[4];

        // For - Schleife zum erhalten der möglichen Antworten
        for (int i = 0; i < tfAnswers.length; i++)
        {
            // Antworten werden aus den Textfeldern initialisiert
            // answers[i] = tfAnswers[i].getText();
            answers[i] = this.tfAnswers[i].getText();
        }

        // For - Schleife zum initialisieren des indexOfCorrectAnswer
        for (int i = 0; i < rbAnswers.length; i++)
        {
            if (rbAnswers[i].isSelected())
            {
                // Selektierter Radio Button ist die korrekte Antwort
                index = i;
                break; // Beenden der Schleife
            }
        }

        // Question Objekt wird mit allen veränderten Attributen an den
        // EditorPresenter weitergegeben
        editorPresenter.editQuestion(question, taQuestion.getText(), answers, index);
    }

    /**
     * Fügt neue Frage hinzu
     */
    public void add()
    {
        // indexOfCorrectAnswer
        int index = 0;

        // possibleAnswers
        String[] answers = new String[4];

        // For - Schleife zum erhalten der möglichen Antworten
        for (int i = 0; i < tfAnswers.length; i++)
        {
            // Selektierter Radio Button ist die korrekte Antwort
            answers[i] = this.tfAnswers[i].getText();
        }

        // For - Schleife zum initialisieren des indexOfCorrectAnswer
        for (int i = 0; i < rbAnswers.length; i++)
        {
            if (rbAnswers[i].isSelected())
            {
                // Selektierter Radio Button ist die korrekte Antwort
                index = i;
                break; // Beenden der Schleife
            }
        }

        // Question Objekt wird mit allen veränderten Attributen an den
        // EditorPresenter weitergegeben
        this.editorPresenter.addQuestion(taQuestion.getText(), answers, index);
    }

    /**
     * Wenn der Cancel Button betätigt wird
     */
    public void cancel()
    {
        // Ruft die closeStage Methode des EditorPresenter auf
        this.hide();
    }

    /**
     * Fügt RadioButtons, Textfelder und die löschen Buttons hinzu
     * 
     * Übergibt den Antwort String an den EditorPresenter
     */
    public void addAnswer()
    {
        // Temporäre Variable für die Texte der Textfelder
        String[] tfTmp = new String[tfAnswers.length];

        // Texte der Textfelder werden in ein temporäres Array geschrieben
        if (tfTmp.length > 0 && tfAnswers.length > 0)
        {
            for (int i = 0; i < tfTmp.length; i++)
            {
                tfTmp[i] = tfAnswers[i].getText();
            }
        }

        // Arrays werden neu initialisiert
        rbAnswers = new RadioButton[rbAnswers.length + 1];
        tfAnswers = new TextField[tfAnswers.length + 1];
        btnDelete = new Button[btnDelete.length + 1];

        for (int i = 0; i < rbAnswers.length; i++)
        {
            rbAnswers[i] = new RadioButton();
            rbAnswers[i].setToggleGroup(tgAnswers);
        }

        for (int i = 0; i < tfAnswers.length; i++)
        {
            tfAnswers[i] = new TextField();
            if (tfTmp.length > 0 && tfAnswers.length > 0)
            {
                if (tfTmp[i] != null)
                {

                }
                tfAnswers[i].setText(tfTmp[i]);
            }
        }

        for (int i = 0; i < btnDelete.length; i++)
        {
            btnDelete[i] = new Button("L\u00f6schen");
        }

        initAddView();
    }

    /**
     * Löscht Textfeld, RadioButton und löschen Button
     * 
     * @param answer
     * @param index
     */
    public void deleteAnswer(String answer, int index)
    {

        editorPresenter.deleteAnswer(answer, index);
    }
}
