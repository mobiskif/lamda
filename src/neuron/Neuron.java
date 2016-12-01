package neuron;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class Neuron extends JPanel implements MouseMotionListener {
	int x0,y0;
	public int[] W = {1,2,1,0};
	public int[] dW = {0,0,0,0};
	public int[] X = {10,1,5,25}; 
	public int Y=0;
	int D=93; //31*3=93;
	
	public Neuron(int x, int y) {
		super();
		
		((FlowLayout) getLayout()).setAlignment(FlowLayout.RIGHT);
		setLocation(x, y);
		addMouseMotionListener(this);

		JLabel label = new JLabel("");
		setOpaque (false);
		try {label.setIcon(new ImageIcon(Neuron.class.getResource("/res/sphere2.png")));}
		catch (Exception e) {}
		add(label);
		setSize(new Dimension(label.getIcon().getIconWidth()+13,label.getIcon().getIconHeight()+8));

		Y=getS();
	}

	int getS() {
		int S=0;
		for (int i = 0; i < W.length; i++)
			S += W[i]*X[i];
		return S;
	}
	
	int[] getdW() {
		int[] dW = {0,0,0,0};
		for (int i = 0; i < W.length; i++)
			dW[i] = W[i]*X[i];
		return dW;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x1,y1,dx,dy;
		x1 = e.getX(); y1 = e.getY();
		dx = x1 - x0; dy = y1 - y0;
		Neuron n = (Neuron) e.getSource();
		n.setBounds(n.getX() + dx, n.getY() + dy, n.getWidth(), n.getHeight());
		n.getParent().repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x0 = e.getX();
		y0 = e.getY();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int dY = getHeight()/W.length;
		//g.drawString(getX()+" "+getY(), 10, getHeight());
		for (int i = 0; i < W.length; i++) {
			g.drawString(""+W[i], 0, 10+dY*i);
			//g.drawString(""+X[i], 10, 10+dY*i);
		}
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
		g.drawString(" "+Y, 13, getHeight()/2+4);
		//g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}

}
