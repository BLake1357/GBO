package gui.mvp.vocabtrainer;

import java.util.ArrayList;
import java.util.HashMap;

public class Model
{
    // Beinhaltet Tupe mit Vokabel und Übersetzung
    private HashMap<String, String> vocabList;

    // Beinhaltet die Übersetzungen der Vokabeln
    private ArrayList<String> germanList;

    // Counter dient als index
    private int counter = 0;

    /**
     * Allgemeiner Konstruktor
     * 
     * Änderungen notwendig
     */
    public Model()
    {
        vocabList = new HashMap<>();
        vocabList.put("cat", "Katze");
        vocabList.put("dog", "Hund");
        vocabList.put("table", "Tisch");
        vocabList.put("chair", "Stuhl");
        vocabList.put("door", "Tür");
        vocabList.put("lamp", "Lampe");
        vocabList.put("voice", "Stimme");

        germanList = new ArrayList<String>();
        germanList.addAll(vocabList.keySet());
    }

    /**
     * Verifikationsmethode
     * 
     * Änderungen notwendig
     * 
     * @param loginName
     * @param password
     * @return
     */
    public boolean isOkay(String translation, String vocab)
    {
        return vocab.equalsIgnoreCase(vocabList.get(translation));
    }

    /**
     * Übergibt die nächste Vokabel an den Presenter
     * 
     * @return
     */
    public String getNextVocab()
    {
        counter = counter % germanList.size();
        return String.valueOf(vocabList.get(germanList.get(counter)));
    }
}
