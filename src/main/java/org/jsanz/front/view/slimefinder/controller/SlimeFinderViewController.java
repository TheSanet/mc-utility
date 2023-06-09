package org.jsanz.front.view.slimefinder.controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import org.jsanz.front.controller.PanelController;
import org.jsanz.front.view.Menu;
import org.jsanz.front.view.slimefinder.view.SlimefinderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("slimeFinderViewController")
public class SlimeFinderViewController extends PanelController{
	
	@Autowired
	private SlimefinderView slimefinderView;
	
	@Autowired
	private org.jsanz.core.controller.SlimeFinderController slimeFinderController;

	@Override
	public JPanel getPanel() {
		return slimefinderView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand().equals(SlimefinderView.FRM_BTN_SLIMEFINDER_SEARCH)){	

			Long seed=(Long) slimefinderView.getSeed().getValue();
			Integer x=(Integer)slimefinderView.getCenterX().getValue();
			Integer z=(Integer)slimefinderView.getCenterY().getValue();
			Integer radio=(Integer)slimefinderView.getRadio().getValue();
			slimeFinderController.search(seed,x,z,radio);
		}
		
	}







}
