package neuron;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NeuronPanel extends JPanel {
	Component[] components;
	ArrayList<Map<Integer, Integer>> conn = new ArrayList<Map<Integer, Integer>>();
	NeuronModel model = new NeuronModel();
	BufferedImage img = null;
	
	public NeuronPanel() {
		setLayout(null);
		add(new Neuron(150,100));
		add(new Neuron(150,200));
		add(new Neuron(150,300));
		add(new Neuron(150,400));
		
		add(new Neuron(300,150));
		add(new Neuron(300,250));
		add(new Neuron(450,200));

		setPreferredSize(new Dimension(550, 500));
		components = getComponents();
		
		//setBackground(new Color(255, 127, 127));
		//setOpaque(true);
		try {img = ImageIO.read(new File("res/space.jpg"));} catch (IOException e) {}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
        //g.drawImage(img, 0,0,null);

		int ID_from = 0;
		for (int[] inputs : model.link) {
			//g.setColor(Color.WHITE);
			Neuron from = (Neuron) components[ID_from];
			int x0 = from.getX();
			int y0 = from.getY();
			int inputID=0;
			int dY = from.getHeight()/inputs.length;
			for (int ID_to : inputs) {
				if (ID_to!=ID_from) {
					Neuron to = (Neuron) components[ID_to];
					int x1 = to.getX() + to.getWidth();
					int y1 = to.getY() + to.getHeight() / 2;
					g.drawLine(x0-2, y0 + dY * inputID, x1, y1);
				}
				else {
					int xx=30;
					int yy=250 + 3*dY * inputID;
					g.drawLine(x0-2, y0 + dY * inputID, xx, yy);
					g.drawString(""+from.X[inputID], xx-15, yy);
					g.fillOval(xx-4, yy-4, 8, 8);
				};
				inputID++;
			}
			//g.drawString(""+ID_from, x0+from.getWidth(), y0);
			ID_from++;
		}

	}
	
	   @Override
       public void paintComponent( Graphics g ) {
           super.paintComponent( g );
           g.drawImage(img, 0,0,null);
           // Apply our own painting effect
           Graphics2D g2d = (Graphics2D) g.create();
           // 50% transparent Alpha
           g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

           g2d.setColor(getBackground());
           g2d.fillRect( 0, 0, getWidth(), getHeight() );

           g2d.dispose();
       }

	
	public void calculate(ActionEvent e) {
		if (e.getActionCommand().equals("Прямой")) {
			for (int currentID = 0; currentID < model.S.length; currentID++) {
				Neuron current = (Neuron) components[currentID];
				for (int j = 0; j < model.X[currentID].length; j++) {
					int sourceID = model.link[currentID][j];
					Neuron source = (Neuron) components[sourceID];
					if (currentID != sourceID) current.X[j] = source.Y;
				}
				current.Y=current.getS();
			}
			repaint();
		}
		else if (e.getActionCommand().equals("Обратный")) {
			//
		}
	}
	
}
