package gui.mvp.vocabtrainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ModelTest
{
    private Model model;

    @BeforeAll
    private void init()
    {
        this.model = new Model();
    }

    @Test
    public void testCorrectTranslation()
    {
        assertTrue(model.isOkay("dog", "Hund"), "Test erfolgreich für Übersetzung");
    }

    @Test
    public void testWrongTranslation()
    {
        assertFalse(model.isOkay("cat", "Hund"), "Falsche Vokabel");
    }
}
