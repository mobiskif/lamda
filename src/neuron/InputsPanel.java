package neuron;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InputsPanel extends JPanel {

	public InputsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(new JCheckBox("12"));
		add(new JCheckBox("345"));
		add(new JCheckBox("64"));
	}
}
