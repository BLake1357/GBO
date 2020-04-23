package gui.exam;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddDialogB extends Stage
{
    private TextField tfTeamA;

    private TextField tfTeamB;

    private TextField tfScoreA;

    private TextField tfScoreB;

    private Label lblError;

    private View viewA;

    public AddDialogB()
    {
        // Container der Elemente
        // Gesamt
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        // Erste Zeile
        HBox hBoxA = new HBox();
        // Zweite Zeile
        HBox hBoxB = new HBox();
        // Dritte Zeile
        HBox hBoxC = new HBox();
        hBoxC.setSpacing(10);

        // Titel der Stage
        this.setTitle("Dialog");

        // Interaktionselemente
        // Lables
        Label lblTeams = new Label("Teams:");
        Label lblResult = new Label("Ergebnis:");
        Label lblMinus = new Label(" - ");
        Label lblColon = new Label(" : ");
        this.lblError = new Label();
        // Textfelder
        this.tfTeamA = new TextField();
        this.tfTeamB = new TextField();
        this.tfScoreA = new TextField();
        this.tfScoreB = new TextField();
        // Buttons
        Button btnAdd = new Button("Hinzufügen");
        Button btnCancel = new Button("Abbrechen");

        // Eventbehandlung
        btnAdd.setOnAction(e -> add());
        btnCancel.setOnAction(e -> cancel());

        // Elemente werden in die Container eingefügt
        // erste Zeile
        hBoxA.getChildren().addAll(lblTeams, tfTeamA, lblMinus, tfTeamB);
        // zweite Zeile
        hBoxB.getChildren().addAll(lblResult, tfScoreA, lblColon, tfScoreB);
        // dritte Zeile
        hBoxC.getChildren().addAll(btnAdd, btnCancel);
        // Gesamt
        vBox.getChildren().addAll(hBoxA, hBoxB, hBoxC, lblError);

        this.setScene(new Scene(vBox));
        this.initModality(Modality.APPLICATION_MODAL);
    }

    public void add()
    {
        // Überprüft die Eingabe mit Regular Expressions
        if (tfTeamA.getText().matches("\\S[\\w a-zA-Z]*") && tfTeamB.getText().matches("\\S[\\w a-zA-Z]*") && tfScoreA.getText().matches("[0-9]*") && tfScoreB.getText().matches("[0-9]*"))
        {
            viewA.addMatch(new Match(tfTeamA.getText(), tfTeamB.getText(), Integer.parseInt(tfScoreA.getText()), Integer.parseInt(tfScoreB.getText())));
            this.close();
        }
        else
        {
            this.lblError.setText(": ungültige Eingabe");
        }
    }

    public void cancel()
    {
        this.close();
    }

    public void setView(View vA)
    {
        this.viewA = vA;
    }
}
