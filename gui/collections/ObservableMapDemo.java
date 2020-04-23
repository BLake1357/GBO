package gui.collections;

import java.util.*;
import javafx.collections.*;

public class ObservableMapDemo
{
    public static void main(String[] args)
    {
        Map<String, String> map = new HashMap<String, String>();
        ObservableMap<String, String> observableMap = FXCollections.observableMap(map);
        observableMap.addListener
        (
            new MapChangeListener<String, String>()
            {
                public void onChanged(MapChangeListener.Change<? extends String, ? extends String> change)
                {
                    System.out.println("Map wurde ge�ndert");
                }
            }
        );

        observableMap.put("Schl�ssel 1", "Wert 1");
        System.out.println("Gr��e: " + observableMap.size());

        map.put("Schl�ssel 2", "Wert 2");
        System.out.println("Gr��e: " + observableMap.size());
    }
}
