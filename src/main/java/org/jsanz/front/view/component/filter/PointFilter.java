package org.jsanz.front.view.component.filter;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.text.NumberFormatter;

import org.springframework.context.MessageSource;

public class PointFilter extends JPanel{
	
	private MessageSource messageSource = null;

	private JFormattedTextField centerX = new JFormattedTextField();
	private JFormattedTextField centerY = new JFormattedTextField();
	static final String FRM_LBL_CIRCLE_FILTER_CENTER_X = "frm.lbl.filter.circle.center.x";
	static final String FRM_LBL_CIRCLE_FILTER_CENTER_Y = "frm.lbl.filter.circle.center.y";
	
	public PointFilter(MessageSource messageSource) {
		super();
		this.messageSource=messageSource;
		
		//formato
		NumberFormat formatInteger = NumberFormat.getInstance();
		NumberFormatter formatterInteger = new NumberFormatter(formatInteger);
		formatterInteger.setValueClass(Integer.class);
		formatterInteger.setMinimum(0);
		formatterInteger.setMaximum(Integer.MAX_VALUE);
		formatterInteger.setAllowsInvalid(false);
		formatterInteger.setCommitsOnValidEdit(true);
		
		// label center
		JLabel lblSCenterX = new JLabel(messageSource.getMessage(FRM_LBL_CIRCLE_FILTER_CENTER_X,
							new Object[] { FRM_LBL_CIRCLE_FILTER_CENTER_X }, Locale.getDefault()));
		JLabel lblSCenterY = new JLabel(messageSource.getMessage(FRM_LBL_CIRCLE_FILTER_CENTER_Y,
							new Object[] { FRM_LBL_CIRCLE_FILTER_CENTER_Y }, Locale.getDefault()));

					
		// If you want the value to be committed on each keystroke instead of focus lost
		centerX = new JFormattedTextField(formatterInteger);
		centerY = new JFormattedTextField(formatterInteger);

		// tamaño maximo
		centerX.setMaximumSize(new Dimension(40, 24));
		centerY.setMaximumSize(new Dimension(40, 24));

		// valores por defecto
		centerX.setValue(0);
		centerY.setValue(0);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		this.setLayout(groupLayout);
		SequentialGroup grupo = groupLayout.createSequentialGroup().addComponent(lblSCenterX).addComponent(centerX)
				.addComponent(lblSCenterY).addComponent(centerY);
		groupLayout.setHorizontalGroup(grupo);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(lblSCenterX).addComponent(centerX)
				.addComponent(lblSCenterY).addComponent(centerY));

		this.setVisible(true);
	}
	
	public Integer getCenterX() {
		return (Integer) this.centerX.getValue();
	}

	public void setCenterX(Integer centerX) {
		this.centerX.setValue(centerX);
	}

	public Integer getCenterY() {
		return (Integer) this.centerY.getValue();
	}

	public void setCenterY(Integer centerY) {
		this.centerY.setValue(centerY);
	}




}
