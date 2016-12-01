package wheather;

import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import common.CommonAPI;
import common.CommonController;
import common.CommonModel;
import common.CommonPanel;
import javax.swing.JEditorPane;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class WheatherPanel extends CommonPanel {

	private JTextField queryField = new JTextField("IT", 6);
	private JTable resultTable = new JTable();
	private JComboBox<String> listAPI = new JComboBox<String>();
	private JLabel resultPhoto = new JLabel("");
	private JEditorPane editorPane = new JEditorPane();
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("");

	public WheatherPanel(CommonModel commonModel) {
		super(commonModel);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		panel.add(listAPI);
		listAPI.setModel(new DefaultComboBoxModel(new String[] {"wheather.WheatherAPI", "hh.HunterAPI"}));
		panel.add(queryField);
		panel.add(editorPane);
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		panel.add(label);
		//add(resultPhoto);
		add(panel);
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		scrollPane.setViewportView(resultTable);
		resultPhoto.setIcon(new ImageIcon(WheatherPanel.class.getResource("/res/img.png")));
		CommonAPI api = new CommonAPI(); resultTable.setModel(new DefaultTableModel(api.rows, api.heads));
		resultTable.setName("Таблица результатов");
		resultTable.setPreferredScrollableViewportSize(new Dimension(230, 80));
		refreshModel();
	}

	@Override
	public void setController(CommonController controller) {
		super.setController(controller);
		listAPI.addActionListener(controller);
		queryField.addActionListener(controller);
		resultTable.addKeyListener(controller);
		resultTable.addMouseListener(controller);
		//threadBtn.addActionListener(controller);
	}

	@Override
	public void refreshModel() {
		model.setApi(listAPI.getSelectedItem().toString());
		model.setRows(queryField.getText());
		resultTable.setModel(model);
	}
	
	@Override
	public void refreshComponents() {
		String logo_url = resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString();
		editorPane.setText("<em>" + logo_url + "</em>");
	}

}
