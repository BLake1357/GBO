package gui.mvp.vocabtrainer;

public class Presenter
{
    private View view;

    private Model model;

    public Presenter()
    {
    }

    public void setView(View view)
    {
        this.view = view;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    /**
     * Pr�ft die Eingabe des Users
     * 
     * @param german
     * @param english
     */
    public void study(String german, String english)
    {
        if (english.isEmpty())
        {
            view.showInputError();
        }
        else if (model.isOkay(german, english))
        {
            view.showOkayMessage();
        }
        else
        {
            view.showTranslationError();
        }
    }

    /**
     * �bergibt die n�chste Vokabel an die View
     */
    public void nextVocable()
    {
        view.setVocab(model.getNextVocab());
    }
}
