package common;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class CommonAPI {
	protected String apiURL="";
	protected String items="";
	
	public Object[][] rows = new Object[][] {
		{"Row 1", "Cell 1", "Cell 2"},
		{"Row 2", "Cell 1", "Cell 2"},
		{"Row 3", "Cell 1", "Cell 2"},
	};
	
	public Object[] heads = new Object[] {"Col 1", "Col 2", "Col 3"};
	
	public JsonObject getJsonObj(String text) {
		try {
			text = URLEncoder.encode(text, "UTF-8");
			URL uurl = new URL(apiURL + text);
			System.out.println(uurl);
			InputStream is = uurl.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};
	
	public Object[][] parseJsonToArr(String text) {
		return rows;
	};
}
