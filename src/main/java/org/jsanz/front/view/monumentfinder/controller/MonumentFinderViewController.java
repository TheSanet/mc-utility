package org.jsanz.front.view.monumentfinder.controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jsanz.front.controller.PanelController;
import org.jsanz.front.view.fortressfinder.view.FortressFinderView;
import org.jsanz.front.view.monumentfinder.view.MonumentFinderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("monumentFinderViewController")
public class MonumentFinderViewController extends PanelController{
	
	@Autowired
	private MonumentFinderView monumentFinderView;
	
	@Autowired
	private org.jsanz.core.controller.MonumentFinderController monumentFinderController;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand().equals(FortressFinderView.FRM_BTN_FORTRESS_SEARCH)){	

			Long seed=(Long) monumentFinderView.getSeed().getValue();
			Integer x=(Integer)monumentFinderView.getCenterX().getValue();
			Integer z=(Integer)monumentFinderView.getCenterY().getValue();
			Integer radio=(Integer)monumentFinderView.getRadio().getValue();
			Integer occurrences=null;//TODO
			monumentFinderController.search(seed,x,z,radio,occurrences);
		}
		
	}

	@Override
	public JPanel getPanel() {
		return monumentFinderView;
	}

}
