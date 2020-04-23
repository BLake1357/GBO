/**
 * 
 * 
 * 
 * 
 * Kompletter Bullshit
 * 
 * 
 * 
 * 
 * 
 * 
 */

package gui.exam;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddDialog extends Dialog<ButtonType>
{
    private TextField tfTeamA;

    private TextField tfTeamB;

    private TextField tfScoreA;

    private TextField tfScoreB;

    public AddDialog()
    {
        VBox vBox = new VBox();
        HBox hBoxA = new HBox();
        HBox hBoxB = new HBox();

        this.setHeaderText(null);

        // Interaktionselemente
        // Lables
        Label lblTeams = new Label("Teams:");
        Label lblResult = new Label("Ergebnis:");
        Label lblMinus = new Label(" - ");
        Label lblColon = new Label(" : ");
        Label lblError = new Label();
        // Textfelder
        this.tfTeamA = new TextField();
        this.tfTeamB = new TextField();
        this.tfScoreA = new TextField();
        this.tfScoreB = new TextField();

        // Interaktionselemente werden einfgefügt
        // erste Zeile
        hBoxA.getChildren().addAll(lblTeams, tfTeamA, lblMinus, tfTeamB);
        // zweite Zeile
        hBoxB.getChildren().addAll(lblResult, tfScoreA, lblColon, tfScoreB);
        // Gesamt
        vBox.getChildren().addAll(hBoxA, hBoxB);

        // ButtonsTypes
        ButtonType btnTAdd = new ButtonType("Hinzufügen", ButtonData.OK_DONE);
        ButtonType btnTCancel = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

        // ButtonTypes werde in die DialogPane eingefügt
        this.getDialogPane().getButtonTypes().addAll(btnTAdd, btnTCancel);

        // Das ErrorLabel werden in die Vbox eingefügt
        vBox.getChildren().add(lblError);

        // VBox wird in die DialogPane hinzugefügt
        this.getDialogPane().setContent(vBox);
    }

    public Match add()
    {
        try
        {
            return new Match(tfTeamA.getText(), tfTeamB.getText(), Integer.parseInt(tfScoreA.getText()), Integer.parseInt(tfScoreB.getText()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void cancel()
    {
        this.close();
    }
}
