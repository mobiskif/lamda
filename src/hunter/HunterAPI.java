package hunter;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import common.CommonAPI;

public class HunterAPI extends CommonAPI {

	public HunterAPI() {
		super();
		heads = new Object[] { "Номер", "Дата", "Фирма", "Работа", "Доход", "Лого", "ID" };
		apiURL = "https://api.hh.ru/vacancies?area=2&only_with_salary=true&salary=120000&exclude_archived=true&exclude_closed=true&order_by=salary_desc&text=";
		items = "items";
	}

	public String getCompanyByID(String company_id) {
		try {
			URL url = new URL("https://api.hh.ru/employers/" + company_id);
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			return obj.get("description").toString().replace("\"", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public Object[][] parseJsonToArr(String text) {
		int page = 0;
		Object[][] tmprows = new Object[500][heads.length];
		int j = 0;
		try {
			text = URLEncoder.encode(text, "UTF-8");
			while (page >= 0) {
				URL uurl = new URL(apiURL + text + "&page="+page);
				//System.out.println(page + " = " + uurl);
				InputStream is = uurl.openStream();
				JsonReader rdr = Json.createReader(is);
				JsonObject obj = rdr.readObject();
				JsonArray arr = obj.getJsonArray(items);
				if (arr.size()>0) {
					for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
						tmprows[j][0] = result.get("id").toString().replace("\"", "");
						tmprows[j][1] = result.get("published_at").toString().substring(1, 11).replace("\"", "");
						tmprows[j][2] = result.getJsonObject("employer").get("name").toString().replace("\"", "");
						tmprows[j][3] = result.getJsonObject("snippet").get("responsibility").toString().replace("\"", "");
						tmprows[j][4] = (result.getJsonObject("salary").get("from").toString().replace("\"", "") + " .. "+ result.getJsonObject("salary").get("to").toString().replace("\"", "")).replace("null","");
						tmprows[j][5] = "*";
						try {tmprows[j][5] = result.getJsonObject("employer").getJsonObject("logo_urls").get("240").toString().replace("\"", "");}
						catch (Exception e) {}
						tmprows[j][6] = result.getJsonObject("employer").get("id").toString().replace("\"", "");
						j++;
					}
					page++;
				}
				else page=-1;
			}
			Object[][] rows = new Object[j][heads.length];
			for (int k = 0; k < j; k++) 
		        if (tmprows[k] != null )
		        	rows[k] = tmprows[k];
			return rows;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
