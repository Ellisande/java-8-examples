package sf.examples;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScript {

	public static void main(String[] args) throws ScriptException, IOException{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");


		String strangeWork = "Hello world!";
		engine.put("strangeWork", strangeWork);
		
		String js = new String(Files.readAllBytes(Paths.get("src/test/resources/javascript.js")), StandardCharsets.UTF_8);

		engine.eval(js);
	}
}
