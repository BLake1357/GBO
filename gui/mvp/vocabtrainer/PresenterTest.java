package gui.mvp.vocabtrainer;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PresenterTest
{
    private View view;

    private Model model;

    @BeforeAll
    public void setView(View view)
    {
        this.view = view;
    }

    @BeforeAll
    public void setModel(Model model)
    {
        this.model = model;
    }

    @Test
    void testCorrectVocab()
    {
        fail("Not yet implemented");
    }

}
