package org.jsanz.front.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.text.NumberFormatter;

import org.hibernate.internal.TableGroupFilterAliasGenerator;
import org.jsanz.core.batch.entity.SlimeResult;
import org.jsanz.core.service.ConfigurationService;
import org.jsanz.front.controller.ResultViewController;
import org.jsanz.front.view.component.filter.CircleFilter;
import org.jsanz.front.view.component.filter.DateFilter;
import org.jsanz.front.view.component.filter.RadioFilter;
import org.jsanz.front.view.component.result.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ResultView extends JPanel{

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	@Qualifier("resultViewController")
	private ResultViewController resultViewController;
	
	//componentes
	
	private GridLayout layout;
	private NumberFormatter formatterLong,formatterInteger;
	
	private JTable jTable;
	
	private JButton btnBuscar;
	public static final String FRM_BTN_RESULT_SEARCH = "frm.btn.result.search";
	
	//filtros
	private JFormattedTextField jobName;
	private static final String FRM_LBL_RESULT_JOB_NAME = "frm.lbl.result.job.name";
	
//	private JFormattedTextField inicio,fin;
//	private static final String FRM_LBL_RESULT_INICIO = "frm.lbl.result.inicio";
//	private static final String FRM_LBL_RESULT_FIN = "frm.lbl.result.fin";
	
	private JFormattedTextField seed;
	private static final String FRM_LBL_RESULT_SEED = "frm.lbl.result.seed";

	private CircleFilter circleFilter;

	private DateFilter dateFilter;
	
	private Table<SlimeResult> tabla;
	
	@PostConstruct
	public void init() {
		formulario();
		
		jobName=new JFormattedTextField();
	}

	private void formulario() {
		// formato para el layout principal
		layout = new GridLayout(3, 1);
		this.setLayout(layout);

		formato();
		this.add(fila1());
		
		circleFilter=new CircleFilter(messageSource);
		this.add(circleFilter);
		
		dateFilter=new DateFilter(messageSource);
		this.add(dateFilter);
		
		tabla=new Table<SlimeResult>(new ArrayList<>());
		
		this.setVisible(true);

	}

	private void formato() {
		NumberFormat format = NumberFormat.getInstance();
		formatterLong = new NumberFormatter(format);
		formatterLong.setValueClass(Long.class);
		formatterLong.setAllowsInvalid(false);
		formatterLong.setCommitsOnValidEdit(true);
		
		NumberFormat formatInteger = NumberFormat.getInstance();
		formatterInteger = new NumberFormatter(formatInteger);
		formatterInteger.setValueClass(Integer.class);
		formatterInteger.setMinimum(0);
		formatterInteger.setMaximum(Integer.MAX_VALUE);
		formatterInteger.setAllowsInvalid(false);
		formatterInteger.setCommitsOnValidEdit(true);
	}

	private JPanel fila1() {
		// label seed
		JLabel lblSeed = new JLabel(messageSource.getMessage(FRM_LBL_RESULT_SEED,
				new Object[] { FRM_LBL_RESULT_SEED }, Locale.getDefault()));

		// obtenemos la seed
		seed = new JFormattedTextField(formatterLong);
		seed.setValue(configurationService.getSeed());
		seed.setMaximumSize(new Dimension(300, 24));

		// boton buscar
		btnBuscar = new JButton(messageSource.getMessage(FRM_BTN_RESULT_SEARCH,
				new Object[] { FRM_BTN_RESULT_SEARCH }, Locale.getDefault()));
		btnBuscar.setActionCommand(FRM_BTN_RESULT_SEARCH);
		btnBuscar.addActionListener(resultViewController);

		// creamos un panel para la fila
		JPanel panelSeed = new JPanel();
		GroupLayout groupLayout = new GroupLayout(panelSeed);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		panelSeed.setLayout(groupLayout);
		SequentialGroup grupo = groupLayout.createSequentialGroup().addComponent(lblSeed).addComponent(seed)
				.addComponent(btnBuscar);
		groupLayout.setHorizontalGroup(grupo);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup().addComponent(lblSeed).addComponent(seed).addComponent(btnBuscar));

		panelSeed.setVisible(true);
		return panelSeed;
	}
	
	public void actualizaTabla(List<SlimeResult> l) {
		this.tabla=new Table<SlimeResult>(l);
		
	}

	//getter y setters
	public String getJobName() {
		return (String) jobName.getValue();
	}

	public LocalDateTime getInicio() {
		return dateFilter.getFechaInicio();
	}

	public void setInicio(LocalDateTime inicio) {
		this.dateFilter.setFechaInicio(inicio);
	}

	public LocalDateTime getFin() {
		return dateFilter.getFechaFin();
	}

	public void setFin(LocalDateTime fin) {
		this.dateFilter.setFechaFin(fin);
	}

	public JFormattedTextField getSeed() {
		return seed;
	}

	public void setSeed(JFormattedTextField seed) {
		this.seed = seed;
	}

	public Integer getCenterX() {
		return circleFilter.getCenterX();
	}
	
	public Integer getCenterY() {
		return circleFilter.getCenterY();
	}
	
	public Integer getRadio() {
		return circleFilter.getRadio();
	}

	public void setJobName(String string) {
		jobName.setValue(string);
		
	}




	

}
