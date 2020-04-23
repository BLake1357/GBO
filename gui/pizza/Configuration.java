package gui.pizza;

public class Configuration
{
    private String[] pizzaGroessen;

    private int[] grundPreise;

    private String[] zutaten;

    private int[] preiseZutaten;

    private int numberOfDefaultToppings;

    /**
     * Allgemeiner Konstruktor
     * 
     * @param pizzaGroessen
     * @param grundPreise
     * @param zutaten
     * @param preiseZutaten
     * @param numberOfDefaultToppings
     * @throws IllegalArgumentException
     */
    public Configuration(String[] pizzaGroessen, int[] grundPreise, String[] zutaten, int[] preiseZutaten, int numberOfDefaultToppings) throws IllegalArgumentException
    {
        if (pizzaGroessen.length == grundPreise.length && zutaten.length == preiseZutaten.length && numberOfDefaultToppings <= zutaten.length)
        {
            this.pizzaGroessen = pizzaGroessen;
            this.grundPreise = grundPreise;
            this.zutaten = zutaten;
            this.preiseZutaten = preiseZutaten;
            this.numberOfDefaultToppings = numberOfDefaultToppings;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public String[] getSizeNames()
    {
        return pizzaGroessen;
    }

    public int[] getSizePrices()
    {
        return grundPreise;
    }

    public String[] getToppingNames()
    {
        return zutaten;
    }

    public int[] getToppingPrices()
    {
        return preiseZutaten;
    }

    public int getNumberOfDefaultToppings()
    {
        return numberOfDefaultToppings;
    }
}
