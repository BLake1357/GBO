package gui.exam2;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Protokoll extends Stage
{
    private TextArea taProtokoll;

    public Protokoll()
    {
        initProtokoll();
    }

    private void initProtokoll()
    {
        // VBox als root Container
        VBox root = new VBox();

        // Interaktionselemente
        // TextArea
        taProtokoll = new TextArea();
        taProtokoll.setEditable(false);

        // TextArea wird in die VBox eingefügt
        root.getChildren().add(taProtokoll);

        this.setScene(new Scene(root, 500, 300));
        this.setTitle("Protokoll");
    }

    public void updateProtokoll(String text)
    {
        taProtokoll.appendText(text);
    }

    public void clearProtokoll()
    {
        taProtokoll.setText(null);
    }
}
