package gui.mvp.editquiz.editor;

import gui.mvp.editquiz.model.Model;
import gui.mvp.editquiz.model.Question;
import javafx.stage.Stage;

public class EditorPresenter
{
    private EditorView editorView;

    private Model model;

    private Stage secondaryStage;

    private DialogView dialogView;

    public EditorPresenter()
    {
    }

    public void setView(EditorView view)
    {
        this.editorView = view;
    }

    public EditorView getView()
    {
        return editorView;
    }

    public void setQuestionModel(Model m)
    {
        this.model = m;
    }

    /**
     * Die Fragen des Models werden an die OverviewView weitergegeben
     */
    public void questions()
    {
        this.editorView.showQuestions(this.model.getQuestions());
    }

    public void delete(Question selectedItem)
    {
        this.model.getQuestions().remove(selectedItem);
        System.out.println(selectedItem);
    }

    /**
     * Ruft den Dialog zum editieren einer Frage auf
     * 
     * @param question
     */
    public void showEditDialog(Question question)
    {
        // Initialisierung der DialoView
        this.dialogView = new DialogView(this);
        this.dialogView.initEditView(question);

        // Initialisierung der Stage
        // this.secondaryStage = new Stage();
        // this.secondaryStage.setScene(new Scene(dialogView, 700, 500));
        // this.secondaryStage.setTitle("Dialog");
        // this.secondaryStage.initModality(Modality.APPLICATION_MODAL);
        // this.secondaryStage.showAndWait();
    }

    /**
     * Ruft den Dialog zum hinzufügen einer Frage auf
     */
    public void showAddDialog()
    {
        // Initialisierung der DialoView
        this.dialogView = new DialogView(this);
        this.dialogView.initAddView();
        this.dialogView.showAndWait();

        // Initialisierung der Stage
        // this.secondaryStage = new Stage();
        // this.secondaryStage.setScene(new Scene(dialogView, 700, 500));
        // this.secondaryStage.setTitle("Dialog");
        // this.secondaryStage.initModality(Modality.APPLICATION_MODAL);
        // this.secondaryStage.showAndWait();
    }

    /**
     * Bei vorhandener Zeit implementieren
     * 
     * @param answer
     * @param index
     */
    public void deleteAnswer(String answer, int index)
    {

    }

    /**
     * Fügt neue Frage in die Liste der Model Klasse ein
     * 
     * @param question
     * @param possibleAnswers
     * @param indexOfCorrectAnswer
     */
    public void addQuestion(String question, String[] possibleAnswers, int indexOfCorrectAnswer)
    {
        model.addQuestion(new Question(question, possibleAnswers, indexOfCorrectAnswer));
        editorView.showQuestions(model.getQuestions());
        closeStage();
    }

    public void closeStage()
    {
        this.dialogView.close();
    }

    public void editQuestion(Question question, String questionString, String[] possibleAnswers, int indexOfCorrectAnswer)
    {
        model.editQuestion(question, questionString, possibleAnswers, indexOfCorrectAnswer);
        editorView.showQuestions(model.getQuestions());
        closeStage();
    }
}
