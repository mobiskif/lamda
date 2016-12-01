package hunter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import common.CommonController;
import common.CommonModel;
import common.CommonPanel;

@SuppressWarnings("serial")
public class HunterPanel extends CommonPanel {

	JPanel panelVacanciesList = new JPanel();
	JPanel panelVacancyDetail = new JPanel();
	JPanel panelEmployerDetail = new JPanel();
	JScrollPane scrollPane_VacanciesList = new JScrollPane();
	JScrollPane scrollPane_EmployerDetail = new JScrollPane();
	JToolBar toolBar = new JToolBar();
	JTable table_VacanciesList = new JTable();
	JEditorPane editPane_VacancyDetail = new JEditorPane();
	JEditorPane editPane_EmployerDetail = new JEditorPane();
	JButton button = new JButton("Найти");
	JTextField textField_SearchPhrase = new JTextField("ИТ");
	JTextField textField_CitySelect = new JTextField("Санкт-Петербург");
	
	public HunterPanel(CommonModel commonModel) {
		super(commonModel);
		this.model = commonModel;

		setPreferredSize(new Dimension(940, 375));
		setMinimumSize(new Dimension(940, 375));
		setPreferredSize(new Dimension(940, 375));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panelVacanciesList.setBorder(null);
		panelVacanciesList.setMaximumSize(new Dimension(460, 32767));
		panelVacanciesList.setPreferredSize(new Dimension(460, 350));		
		panelVacanciesList.setMinimumSize(new Dimension(460, 350));
		panelVacanciesList.setLayout(new BorderLayout(0, 0));
		table_VacanciesList.setBorder(null);
		table_VacanciesList.setName("Таблица результатов");
		scrollPane_VacanciesList.setViewportBorder(null);
		scrollPane_VacanciesList.setBorder(null);
		scrollPane_VacanciesList.setViewportView(table_VacanciesList); 
		panelVacanciesList.add(scrollPane_VacanciesList, BorderLayout.CENTER);
		
		textField_CitySelect.setEnabled(false);
		toolBar.setBorder(null);
		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);
		toolBar.add(textField_CitySelect);
		textField_SearchPhrase.setName("Поле поиска");
		textField_SearchPhrase.setMargin(new Insets(2, 4, 2, 2));
		toolBar.add(textField_SearchPhrase);
		button.setName("Найти");
		toolBar.add(button);
		panelVacanciesList.add(toolBar, BorderLayout.SOUTH);
		add(panelVacanciesList);
		
		panelVacancyDetail.setLayout(new BoxLayout(panelVacancyDetail, BoxLayout.X_AXIS));
		panelVacancyDetail.setMinimumSize(new Dimension(260, 350));
		panelVacancyDetail.setMaximumSize(new Dimension(260, 32767));
		panelVacancyDetail.setPreferredSize(new Dimension(260, 350));
		editPane_VacancyDetail.setMargin(new Insets(3, 12, 3, 12));
		editPane_VacancyDetail.setContentType("text/html");
		editPane_VacancyDetail.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_VacancyDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelVacancyDetail.add(editPane_VacancyDetail);
		add(panelVacancyDetail);

		panelEmployerDetail.setLayout(new BoxLayout(panelEmployerDetail, BoxLayout.X_AXIS));	
		panelEmployerDetail.setMinimumSize(new Dimension(240, 350));
		panelEmployerDetail.setPreferredSize(new Dimension(240, 350));
		editPane_EmployerDetail.setMargin(new Insets(12, 12, 12, 12));
		editPane_EmployerDetail.setContentType("text/html");
		editPane_EmployerDetail.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editPane_EmployerDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_EmployerDetail.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_EmployerDetail.setBorder(null);
		scrollPane_EmployerDetail.setViewportView(editPane_EmployerDetail); panelEmployerDetail.add(scrollPane_EmployerDetail);
		add(panelEmployerDetail);
		
		refreshModel();

	}
	
	@Override
	public void setController(CommonController controller) {
		super.setController(controller);
		table_VacanciesList.addKeyListener(controller);
		table_VacanciesList.addMouseListener(controller);
		button.addKeyListener(controller);
		button.addMouseListener(controller);
		textField_SearchPhrase.addKeyListener(controller);
		textField_SearchPhrase.addActionListener(controller);
	}
	
	@Override
	public void refreshComponents() {
		String employer_id	= table_VacanciesList.getValueAt(table_VacanciesList.getSelectedRow(), 6).toString();
		editPane_VacancyDetail.setText(getVacancyDetail());
		editPane_VacancyDetail.setCaretPosition(0);
		editPane_EmployerDetail.setText(((HunterAPI)model.getAPI()).getCompanyByID(employer_id));
		editPane_EmployerDetail.setCaretPosition(0);
	}

	@Override
	public void refreshModel() {
		model.setApi("hunter.HunterAPI");
		model.setRows(textField_SearchPhrase.getText());
		table_VacanciesList.setModel(model);
		table_VacanciesList.getColumnModel().getColumn(1).setMinWidth(63);
	}
	
	String getVacancyDetail() {
		int row  =  table_VacanciesList.getSelectedRow();
		String s = ""
				+ "<p>" + table_VacanciesList.getValueAt (row, 0) + "</p>"
				+ "<p>" + table_VacanciesList.getValueAt (row, 1) + "</p>"
				+ "<h2>" + table_VacanciesList.getValueAt (row, 2)	+ "</h2>" 
				+ "<p>" + table_VacanciesList.getValueAt (row, 3) + "</p>"
				+ "<h3>" + table_VacanciesList.getValueAt (row, 4)+ "</h3>" 
				+ "<br/><img src=\"" + table_VacanciesList.getValueAt (row, 5) + "\" />"
				+ "<p>" + table_VacanciesList.getValueAt (row, 6) + "</p>";
				return s;
	}
	
	@Override
	public void openInBrowser() {
		String vacancy_id = table_VacanciesList.getValueAt(table_VacanciesList.getSelectedRow(), 0).toString();
		try {Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe https://hh.ru/vacancy/" + vacancy_id);}
		catch (IOException e1) {e1.printStackTrace();}

	}
	
}
