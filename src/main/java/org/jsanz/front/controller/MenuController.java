package org.jsanz.front.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.jsanz.front.view.MainWindows;
import org.jsanz.front.view.Menu;
import org.jsanz.front.view.fortressfinder.controller.FortressFinderViewController;
import org.jsanz.front.view.monumentfinder.controller.MonumentFinderViewController;
import org.jsanz.front.view.slimefinder.controller.SlimeFinderViewController;
import org.jsanz.front.view.slimefinder.view.SlimefinderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MenuController implements ActionListener{
	
	@Autowired
	private MainWindows mainWindows;
	
	@Autowired
	@Qualifier("slimeFinderViewController")
	private SlimeFinderViewController slimefinderController;
	
	@Autowired
	@Qualifier("fortressFinderViewController")
	private FortressFinderViewController fortressfinderController;
	
	@Autowired
	@Qualifier("monumentFinderViewController")
	private MonumentFinderViewController monumentfinderController;
	
	@Autowired
	@Qualifier("resultViewController")
	private ResultViewController resultController;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JMenuItem)e.getSource()).getActionCommand().equals(Menu.FRM_MENU_SLIME_FINDER_SEARCH)){	
			mainWindows.setPanel(slimefinderController.getPanel());

			mainWindows.repaint();
			mainWindows.revalidate();
			
		}else if(((JMenuItem)e.getSource()).getActionCommand().equals(Menu.FRM_MENU_FORTRESS_FINDER_SEARCH)){	
			mainWindows.setPanel(fortressfinderController.getPanel());

			mainWindows.repaint();
			mainWindows.revalidate();
			
		}else if(((JMenuItem)e.getSource()).getActionCommand().equals(Menu.FRM_MENU_MONUMENT_FINDER_SEARCH)){	
			mainWindows.setPanel(monumentfinderController.getPanel());

			mainWindows.repaint();
			mainWindows.revalidate();
			
		}else if(((JMenuItem)e.getSource()).getActionCommand().equals(Menu.FRM_MENU_SLIME_RESULT)){	
			mainWindows.setPanel(resultController.getPanel());
			resultController.setTipeFilter("slime");
			mainWindows.repaint();
			mainWindows.revalidate();
			
		}else if(((JMenuItem)e.getSource()).getActionCommand().equals(Menu.FRM_MENU_FORTRESS_RESULT)){	
			mainWindows.setPanel(resultController.getPanel());
			resultController.setTipeFilter("fortess");
			mainWindows.repaint();
			mainWindows.revalidate();
			
		}else if(((JMenuItem)e.getSource()).getActionCommand().equals(Menu.FRM_MENU_MONUMENT_RESULT)){	
			mainWindows.setPanel(resultController.getPanel());
			resultController.setTipeFilter("monument");
			mainWindows.repaint();
			mainWindows.revalidate();
			
		}else if(((JMenuItem)e.getSource()).getActionCommand().equals(Menu.FRM_MENU_CONFIGURACION_SEED)){
			System.out.println(Menu.FRM_MENU_CONFIGURACION_SEED);
		}
		
	}
	


}
