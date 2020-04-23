package gui.country.combo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Country
{
    private String name;

    private String capital;

    private long people;

    private long area;

    public Country(String name, String capital, long people, long area)
    {
        this.name = name;
        this.capital = capital;
        this.people = people;
        this.area = area;
    }

    public String getName()
    {
        return name;
    }

    public String getCapital()
    {
        return capital;
    }

    public long getPeople()
    {
        return people;
    }

    public long getArea()
    {
        return area;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    /**
     * Gibt die Bevölkerungsdicht in int zurück
     * 
     * @return
     */
    public int getDensity()
    {
        double density = (double) this.people / (double) this.area; // Bevölkerungsdichte
        density = Math.round(density);
        return (int) density;
    }

    /**
     * Runded auf grobe Werte. Absolut Aids
     * 
     * @param number
     * @return
     */
    public String stringRound(double number)
    {
        int x = 0; // ganze Zahl zum Abschneiden der nicht erwünschten Stellen
        // prüft ob der Wert größer oder gleich 1.000 Mill.
        if (number >= 1000000000)
        {
            // die letzten 6 Stellen werden hinter das Komma verschoben
            double temp = number / 1000000;

            /*
             * Bei dem Return wird number ganzzahlig abgerundet und als String
             * mit "Mrd." konkateniert
             */
            temp = Math.round(temp);
            String tmp = String.valueOf((int) temp);
            String erg = "";
            for (int i = 0; i < tmp.length(); i++)
            {
                if (i == tmp.length() - 3)
                {
                    erg += ".";
                }
                erg += tmp.charAt(i);
            }
            return erg + " Mill.";
        }
        // prüft ob der Wert größer oder gleich 1 Mill.
        else if (number >= 1000000)
        {
            // die letzten 6 Stellen werden hinter das Komma verschoben
            double temp = number / 1000000;
            /*
             * Bei dem Return wird number ganzzahlig abgerundet und als String
             * mit "Mill." konkateniert
             */
            temp = Math.round(temp);
            return ((int) temp) + " Mill.";
        }
        // Prüft alle Werte größer oder gleich 1000
        else if (number >= 1000)
        {
            // die letzten 3 Stellen werden hinter das Komma verschoben
            double temp = number / 1000;
            temp = Math.round(temp);
            // Wird Ganzzahlig gemacht um Dezimalstellen zu eliminieren
            x = (int) temp;
            // an die Zahl wird ".000" angefügt
            DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
            dfs.setDecimalSeparator('.');
            DecimalFormat dFormat = new DecimalFormat(".000##", dfs);
            return dFormat.format(x);
        }
        // Zahl kleiner 1000
        else
        {
            int u = (int) number;
            return String.valueOf(u);
        }
    }
}
