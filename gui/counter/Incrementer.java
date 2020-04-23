package gui.counter;

public class Incrementer
{
    private int value;

    public Incrementer(int value)
    {
        this.value = value;
    }

    public void increment()
    {
        value++;
    }

    public int getValue()
    {
        return value;
    }
}
