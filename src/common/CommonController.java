package common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.ZonedDateTime;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class CommonController implements KeyListener, MouseListener, ActionListener, Runnable {
	protected CommonPanel panel;

	public CommonController(CommonPanel v) {
		panel = v;
		panel.setController(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == 40 || e.getKeyCode() == 38) && e.getComponent().getName().equals("Таблица результатов")) {
			panel.refreshComponents();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1 && e.getComponent().getName().equals("Найти"))
			panel.refreshModel();
		else if (e.getButton() == 1 && e.getComponent().getName().equals("Таблица результатов"))
			panel.refreshComponents();
		else if (e.getButton() == 2 && e.getComponent().getName().equals("Таблица результатов")) {
			panel.openInBrowser();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Class src = e.getSource().getClass();
		System.out.println(src);
		if (src.equals(JToggleButton.class)) {
			new Thread(this).start();
		}
		else if (src.equals(JComboBox.class) || src.equals(JTextField.class))
			panel.refreshModel();
	}

	@Override
	public void run() {
		while (panel.threadBtn.isSelected()) {
			try {
				Thread.sleep(1000);
				String s = ZonedDateTime.now().toLocalTime().toString().substring(0, 8);
				panel.threadBtn.setText(s);
				//System.out.println(s);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
