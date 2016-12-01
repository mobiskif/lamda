package common;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import neuron.NeuronPanel;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Main extends JFrame {

	public Main() {
		super("(c) 2016");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		NeuronPanel np = new NeuronPanel();
		tabbedPane.addTab("Neuron", null, np, null);
		
		CommonPanel cp = new CommonPanel(null);
		tabbedPane.addTab("Common", null, cp, null);
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		JButton btn1 = new JButton("Прямой");
		toolBar.add(btn1);
		JButton btn2 = new JButton("Обратный");
		toolBar.add(btn2);
		btn2.addActionListener(np::calculate);
		btn1.addActionListener(np::calculate);

		pack();
	}

	public static void main(String[] args) {
		new Main();
	}

}
