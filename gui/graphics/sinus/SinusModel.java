package gui.graphics.sinus;

public class SinusModel
{

    public SinusModel()
    {
    }

    public double[] sf(double amplitude, double frequency, double phase)
    {
        double[] values = new double[1000];
        for (int i = 0; i < 1000; i++)
        {
            System.out.println(Math.sin(i));
            values[i] = amplitude * Math.sin(frequency * i + phase);
        }

        return values;
    }
}
