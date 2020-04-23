package gui.mvp.basicquiztable.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Question
{
    private String question;

    private String[] possibleAnswers;

    private int indexOfCorrectAnswer;

    private int answer = 0;

    private int correct = 0;

    // weitere Attribute nach Bedarf

    /**
     * Allgemeiner Konstruktor initialisiert die Attribute
     * 
     * @param question
     * @param possibleAnswers
     * @param indexOfCorrectAnswer
     */
    public Question(String question, String[] possibleAnswers, int indexOfCorrectAnswer)
    {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        // Überprüfung des indexOfCorrectAnswer auf Gültigkeit
        if (indexOfCorrectAnswer >= 0 && indexOfCorrectAnswer < 4)
        {
            this.indexOfCorrectAnswer = indexOfCorrectAnswer;
        }
        else
        {
            throw new IllegalArgumentException("Index der korrekten Antwort ist ungültig. Der Index darf nut zwischen 0 und 3 liegen");
        }
    }

    public String getQuestion()
    {
        return question;
    }

    public void setAnswer(int answer)
    {
        this.answer = answer;
    }

    public int getAnswer()
    {
        return this.answer;
    }

    public void setCorrect(int correct)
    {
        this.correct = correct;
    }

    public int getCorrect()
    {
        return this.correct;
    }

    public String[] getPossibleAnswers()
    {
        return possibleAnswers;
    }

    public int getIndexOfCorrectAnswer()
    {
        return indexOfCorrectAnswer;
    }

    public SimpleStringProperty getQuestionProperty()
    {
        return new SimpleStringProperty(this.question);
    }

    public SimpleIntegerProperty getAnswerProperty()
    {
        return new SimpleIntegerProperty(answer);
    }

    public SimpleIntegerProperty getCorrectAnswerProperty()
    {
        return new SimpleIntegerProperty(correct);
    }

    @Override
    public String toString()
    {
        return question + " (Antworten: " + answer + ", davon richtig: " + correct + ")";
    }
}
