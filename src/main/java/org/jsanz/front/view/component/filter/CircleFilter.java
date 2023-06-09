package org.jsanz.front.view.component.filter;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.springframework.context.MessageSource;

public class CircleFilter extends JPanel{
	
	private PointFilter pointFilter;
	private RadioFilter radioFilter;
	
	public CircleFilter(MessageSource messageSource) {
		super();
		this.pointFilter =new PointFilter(messageSource);
		this.radioFilter = new RadioFilter(messageSource);
		
		this.setLayout(new GridLayout(2, 1));
		this.add(pointFilter);
		this.add(radioFilter);
		this.setVisible(true);
	}


	public Integer getCenterX() {
		return pointFilter.getCenterX();
	}

	public Integer getCenterY() {
		return pointFilter.getCenterY();
	}

	public Integer getRadio() {
		return radioFilter.getRadio();
	}

}
