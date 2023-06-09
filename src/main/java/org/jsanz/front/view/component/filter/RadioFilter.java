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

public class RadioFilter extends JPanel{
	
	private MessageSource messageSource = null;

	private JFormattedTextField radio = new JFormattedTextField();
	private static final String FRM_LBL_CIRCLE_FILTER_RADIO = "frm.lbl.filter.circle.radio";
	
	public RadioFilter(MessageSource messageSource) {
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
		JLabel lblRadio = new JLabel(messageSource.getMessage(FRM_LBL_CIRCLE_FILTER_RADIO,
				new Object[] { FRM_LBL_CIRCLE_FILTER_RADIO }, Locale.getDefault()));

		radio = new JFormattedTextField(formatterInteger);

		radio.setMaximumSize(new Dimension(300, 24));
		radio.setValue(0);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		this.setLayout(groupLayout);
		SequentialGroup grupo = groupLayout.createSequentialGroup().addComponent(lblRadio).addComponent(radio);
		groupLayout.setHorizontalGroup(grupo);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(lblRadio).addComponent(radio));

		this.setVisible(true);

	}

	public Integer getRadio() {
		return (Integer) radio.getValue();
	}
	
	public void setRadio(Integer radio) {
		this.radio.setValue(radio);
	}


}
