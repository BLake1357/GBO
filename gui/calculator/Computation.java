package gui.calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Computation
{
    private ScriptEngine engine;

    public Computation()
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("js");
    }

    public Object evaluate(String expression) throws ScriptException
    {
        return engine.eval(expression);
    }
}