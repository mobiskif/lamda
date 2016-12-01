package wheather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import common.CommonAPI;

public class WheatherAPI extends CommonAPI {

	public WheatherAPI() {
		super();
		heads = new Object[] { "Темп.", "Дата", "Ветер" };
		apiURL = "http://api.openweathermap.org/data/2.5/forecast/daily?appid=a69baf63b91bac94bdabe9cace31c37b&mode=json&units=metric&lang=ru&q=";
		items = "list";
	}

	@Override
	public Object[][] parseJsonToArr(String text) {
		try {
			text = URLEncoder.encode(text, "UTF-8");
			URL uurl = new URL(apiURL + text);
			System.out.println(uurl);
			InputStream is = uurl.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arr = obj.getJsonArray(items);
			rows = new Object[arr.size()][heads.length];
			int j = 0;
			for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
				Date date = Date.from(Instant.ofEpochSecond(result.getInt("dt")));
				rows[j][1] = (new SimpleDateFormat("yy/MM/dd E")).format(date);
				rows[j][0] = result.getJsonObject("temp").get("day");
				rows[j][2] = result.get("speed");
				j++;
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
