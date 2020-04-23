package gui.mvp.basicquiz.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private ArrayList<Question> questions;

    public Model()
    {
        this.questions = new ArrayList<Question>();
    }

    public Question getFirst()
    {
        return questions.get(0);
    }

    /**
     * f�gt ein neues Question Objekt in die ArrayList ein
     * 
     * @param q
     */
    public void addQuestion(Question q)
    {
        this.questions.add(q);
    }

    /**
     * Gibt die ArrayList mit den Fragen zur�ck
     * 
     * @return questions
     */
    public List<Question> getQuestions()
    {
        return this.questions;
    }

    /**
     * Setzt die Statistik f�r jedes Element zur�ck
     */
    public void deleteHistory()
    {
        for (Question q : this.questions)
        {
            q.setAnswer(0);
            q.setCorrect(0);
        }
    }

    /**
     * Setzt pro Frage
     * 
     * @param question
     * @param answer
     */
    public void setStatistics(String question, String answer)
    {
        for (Question q : questions)
        {
            if (q.getQuestion().equals(question))
            {
                for (int i = 0; i < q.getPossibleAnswers().length; i++)
                {
                    if (q.getPossibleAnswers()[i].equals(answer))
                    {
                        q.setAnswer(q.getAnswer() + 1);
                        if (i == q.getIndexOfCorrectAnswer())
                        {
                            q.setCorrect(q.getCorrect() + 1);
                        }
                    }
                }
            }
        }
    }
}
