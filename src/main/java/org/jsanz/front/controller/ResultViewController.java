package org.jsanz.front.controller;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jsanz.core.batch.entity.SlimeResult;
import org.jsanz.front.view.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("resultViewController")
public class ResultViewController extends PanelController{
	
	@Autowired
	private ResultView resultView;
	
	@Autowired
	private org.jsanz.core.controller.ResultController resultController;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand().equals(ResultView.FRM_BTN_RESULT_SEARCH)){	
			String jobName=resultView.getJobName();
			LocalDateTime inicio=resultView.getInicio();
			LocalDateTime fin=resultView.getFin();
			Long seed=(Long) resultView.getSeed().getValue();
			Integer x=resultView.getCenterX();
			Integer z=resultView.getCenterY();
			Integer radio=resultView.getRadio();
			
			List<SlimeResult> l=resultController.search(jobName,inicio,fin,seed,x,z,radio);
			
			resultView.actualizaTabla(l);
		}
		
	}

	@Override
	public JPanel getPanel() {
		return this.resultView;
	}

	public void setTipeFilter(String string) {
		resultView.setJobName(string);
		
	}

}
