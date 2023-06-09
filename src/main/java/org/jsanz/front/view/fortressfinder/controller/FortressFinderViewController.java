package org.jsanz.front.view.fortressfinder.controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jsanz.front.controller.PanelController;
import org.jsanz.front.view.fortressfinder.view.FortressFinderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fortressFinderViewController")
public class FortressFinderViewController extends PanelController{
	
	@Autowired
	private FortressFinderView fortressFinderView;
	
	@Autowired
	private org.jsanz.core.controller.FortressFinderController fortressFinderController;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand().equals(FortressFinderView.FRM_BTN_FORTRESS_SEARCH)){	

			Long seed=(Long) fortressFinderView.getSeed().getValue();
			Integer x=(Integer)fortressFinderView.getCenterX().getValue();
			Integer z=(Integer)fortressFinderView.getCenterY().getValue();
			Integer radio=(Integer)fortressFinderView.getRadio().getValue();
			fortressFinderController.search(seed,x,z,radio);
		}
		
	}

	@Override
	public JPanel getPanel() {
		return fortressFinderView;
	}

}
