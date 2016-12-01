package common;

import java.util.Collections;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class CommonModel extends DefaultTableModel implements Cloneable {
	protected CommonAPI api = null;

	@Override
	protected CommonModel clone() throws CloneNotSupportedException {
		return (CommonModel) super.clone();
	}

	public CommonModel(CommonAPI a) {
		super();
		api = a;
		dataVector = convertToVector(api.rows);
		//System.out.println(dataVector);
		columnIdentifiers = convertToVector(api.heads);	
	}

	@SuppressWarnings("unchecked")
	public void setRows(String query) {
		Object[][] obj = api.parseJsonToArr(query);
		dataVector = convertToVector(obj);
		//Collections.sort(dataVector, (left, right) -> right.toArray()[1].toString().compareTo(left.toArray()[1].toString()));
		Collections.sort(dataVector, new VectorSortDesc());
		fireTableDataChanged();
	}

	public void setApi(String apiName) {
		try {
			Class cls = Class.forName(apiName);
			api = (CommonAPI) cls.newInstance();
			columnIdentifiers = convertToVector(api.heads);
			fireTableStructureChanged();
			System.out.println(apiName);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public CommonAPI getAPI() {
		return this.api;
	}

}
