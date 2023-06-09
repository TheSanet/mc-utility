package org.jsanz.front.view.component.filter;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDateTime;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.SequentialGroup;

import org.springframework.context.MessageSource;

import com.github.lgooddatepicker.components.DateTimePicker;

public class DateFilter extends JPanel{
	
	private DateTimePicker fechaInicio;
	private DateTimePicker fechaFin;
	private MessageSource messageSource;
	
	public DateFilter(MessageSource messageSource) {
		super();
		this.messageSource=messageSource;
		this.fechaInicio = new DateTimePicker();
		this.fechaFin = new DateTimePicker();
		
		this.fechaInicio.setMaximumSize(new Dimension(40, 24));
		this.fechaFin.setMaximumSize(new Dimension(40, 24));
//		this.setMaximumSize(new Dimension(200, 24));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		this.setLayout(groupLayout);
		SequentialGroup grupo = groupLayout.createSequentialGroup().addComponent(fechaInicio).addComponent(fechaFin);
		groupLayout.setHorizontalGroup(grupo);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(fechaInicio).addComponent(fechaFin));
		
		this.setVisible(true);
	}



	public LocalDateTime getFechaInicio() {
		return fechaInicio.getDateTimePermissive();
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio.setDateTimeStrict(fechaInicio);;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin.getDateTimePermissive();
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin.setDateTimeStrict(fechaFin);;
	}

}
