package judge.util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class JavaScriptCodeExecutor {

    private static ScriptEngineManager manager = new ScriptEngineManager();
    private static ScriptEngine engine = manager.getEngineByName("JavaScript");

    private JavaScriptCodeExecutor() {
    }

    public static Object executeScript(String script) throws ScriptException, NoSuchMethodException {
        engine.eval(script);
        Invocable inv = (Invocable) engine;
        String functionName = script.substring(script.indexOf("function ") + "function ".length(), script.indexOf("("));
        return inv.invokeFunction(functionName);
    }
}
