package gui.graphics.sinus;

public class SinusPresenter
{
    private SinusView sinusView;

    private SinusModel sinusModel;

    public SinusPresenter()
    {
    }

    public void setSinusModel(SinusModel model)
    {
        this.sinusModel = model;
    }

    public void setSinusView(SinusView view)
    {
        this.sinusView = view;
    }

    public SinusView getSinusView()
    {
        return sinusView;
    }

    public void calcSin()
    {
        // sinusModel.sf();
    }
}
