package common;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class CommonPanel extends JPanel {
	protected JToggleButton threadBtn = new JToggleButton("Старт Поток");
	protected CommonModel model = null;
	
	public CommonPanel(CommonModel m) {
		this.model = m;
		add(threadBtn);
		//add (new PhotoPanel("res/faceDetection.png"));
	}

	public void refreshComponents() {
		System.out.println("Обновить компоненты");
		// TODO Auto-generated method stub
	}

	public void refreshModel() {
		System.out.println("Обновить модель");
		model.setApi("common.CommonAPI");
		model.setRows("");
	}

	public void openInBrowser() {
		System.out.println("Внешний броузер");
		// TODO Auto-generated method stub
	}

	public void setController(CommonController controller) {
		// TODO Auto-generated method stub
		threadBtn.addActionListener(controller);
	}


}
