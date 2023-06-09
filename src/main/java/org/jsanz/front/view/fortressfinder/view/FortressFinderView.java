package org.jsanz.front.view.fortressfinder.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

import org.jsanz.core.service.ConfigurationService;
import org.jsanz.front.view.fortressfinder.controller.FortressFinderViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class FortressFinderView extends JPanel{
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private FortressFinderViewController fortressfinderController;
	
	private GridLayout layout;
	private NumberFormatter formatterLong,formatterInteger;

	private JButton btnBuscar;
	public static final String FRM_BTN_FORTRESS_SEARCH = "frm.btn.fortressfinder.search";

	private JFormattedTextField seed;
	private static final String FRM_LBL_FORTRESS_SEED = "frm.lbl.fortressfinder.seed";

	private JFormattedTextField centerX, centerY;
	private static final String FRM_LBL_FORTRESS_CENTER_X = "frm.lbl.fortressfinder.center.x";
	private static final String FRM_LBL_FORTRESS_CENTER_Y = "frm.lbl.fortressfinder.center.y";

	private JFormattedTextField radio;
	private static final String FRM_LBL_FORTRESS_RADIO = "frm.lbl.fortressfinder.radio";

	@PostConstruct
	public void init() {
		formulario();
	}
	
	private void formulario() {
		// formato para el layout principal
		layout = new GridLayout(3, 1);
		this.setLayout(layout);

		formato();
		this.add(fila1());
		this.add(fila2());
		this.add(fila3());

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
		JLabel lblSeed = new JLabel(messageSource.getMessage(FRM_LBL_FORTRESS_SEED,
				new Object[] { FRM_LBL_FORTRESS_SEED }, Locale.getDefault()));

		// obtenemos la seed
		seed = new JFormattedTextField(formatterLong);
		seed.setValue(configurationService.getSeed());
		seed.setMaximumSize(new Dimension(300, 24));

		// boton buscar
		btnBuscar = new JButton(messageSource.getMessage(FRM_BTN_FORTRESS_SEARCH,
				new Object[] { FRM_BTN_FORTRESS_SEARCH }, Locale.getDefault()));
		btnBuscar.setActionCommand(FRM_BTN_FORTRESS_SEARCH);
		btnBuscar.addActionListener(fortressfinderController);

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

	private JPanel fila2() {

		// label center
		JLabel lblSCenterX = new JLabel(messageSource.getMessage(FRM_LBL_FORTRESS_CENTER_X,
				new Object[] { FRM_LBL_FORTRESS_CENTER_X }, Locale.getDefault()));
		JLabel lblSCenterY = new JLabel(messageSource.getMessage(FRM_LBL_FORTRESS_CENTER_Y,
				new Object[] { FRM_LBL_FORTRESS_CENTER_Y }, Locale.getDefault()));

		
		// If you want the value to be committed on each keystroke instead of focus lost
		centerX = new JFormattedTextField(formatterInteger);
		centerY = new JFormattedTextField(formatterInteger);

		// tamaño maximo
		centerX.setMaximumSize(new Dimension(40, 24));
		centerY.setMaximumSize(new Dimension(40, 24));

		// valores por defecto
		centerX.setValue(0);
		centerY.setValue(0);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(panel);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		panel.setLayout(groupLayout);
		SequentialGroup grupo = groupLayout.createSequentialGroup().addComponent(lblSCenterX).addComponent(centerX)
				.addComponent(lblSCenterY).addComponent(centerY);
		groupLayout.setHorizontalGroup(grupo);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(lblSCenterX).addComponent(centerX)
				.addComponent(lblSCenterY).addComponent(centerY));

		panel.setVisible(true);
		return panel;
	}

	private JPanel fila3() {
		// label center
		JLabel lblRadio = new JLabel(messageSource.getMessage(FRM_LBL_FORTRESS_RADIO,
				new Object[] { FRM_LBL_FORTRESS_RADIO }, Locale.getDefault()));

		radio = new JFormattedTextField(formatterInteger);

		radio.setMaximumSize(new Dimension(300, 24));
		radio.setValue(0);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(panel);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		panel.setLayout(groupLayout);
		SequentialGroup grupo = groupLayout.createSequentialGroup().addComponent(lblRadio).addComponent(radio);
		groupLayout.setHorizontalGroup(grupo);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(lblRadio).addComponent(radio));

		panel.setVisible(true);
		return panel;
	}

	public JFormattedTextField getSeed() {
		return seed;
	}

	public JFormattedTextField getCenterX() {
		return centerX;
	}

	public JFormattedTextField getCenterY() {
		return centerY;
	}

	public JFormattedTextField getRadio() {
		return radio;
	}

}
