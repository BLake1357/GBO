package gui.calculator;

import javax.script.ScriptException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculationController
{
    @FXML
    private TextField display;

    private Computation comp;

    /**
     * Konstruktor der ein Objekt vom Typ Computation erzugt.
     */
    public CalculationController()
    {
        comp = new Computation();
    }

    /**
     * Mehtode verarbietet Button Clicks.
     * 
     * @param e
     */
    @FXML
    public void compute(ActionEvent e)
    {
        // Variable vom Typ String, die den Text der Buttons enthält.
        var s = ((Button) e.getSource()).getText();

        // CLEAR Button
        if (s.equals("CLEAR"))
        {
            display.clear();
        }
        // DELETE Button
        else if (s.equals("DELETE") && display.getLength() > 0)
        {
            display.setText(display.getText().substring(0, display.getText().length() - 1));
        }
        // = Button
        else if (s.equals("="))
        {
            /**
             * Try Catch Block zum Abfangen der Script Exception, die von
             * evaluate geworfen wird.
             */
            try
            {
                display.appendText("=" + comp.evaluate(display.getText()).toString());
            }
            catch (ScriptException se)
            {
                System.out.println(se.getMessage() + " " + se.getStackTrace());
                display.appendText("=>FEHLER");
            }
        }
        else
        {
            if (!s.equals("CLEAR") && !s.equals("DELETE"))
            {
                display.appendText(((Button) e.getSource()).getText());
            }
        }
    }
}
