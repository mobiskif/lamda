package common;

import java.util.Comparator;
import java.util.Vector;

public class VectorSortDesc implements Comparator<Vector> {

	public VectorSortDesc() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Vector o1, Vector o2) {
		return o2.toArray()[1].toString().compareTo(o1.toArray()[1].toString());
	}

}
