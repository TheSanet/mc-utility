package org.jsanz.front.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainWindows extends JFrame{
	
	@Autowired
	private Menu menu;
	
	private JPanel panel;
	
	@PostConstruct
	public void init() {

//        this.setJMenuBar(menu);
        
        panel=new JPanel();
        panel.add(new JButton("kk"));
        this.add(panel);
        
        
//        this.getContentPane().add(BorderLayout.SOUTH, panel);       
        this.getContentPane().add(BorderLayout.NORTH, menu);       
        this.getContentPane().add(BorderLayout.CENTER, panel);   
        
        this.setBounds(10,20,500,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.getContentPane().removeAll();
		this.panel = panel;
        this.getContentPane().add(BorderLayout.NORTH, menu);  
		this.getContentPane().add(BorderLayout.CENTER, panel);   
		this.revalidate();
		this.repaint();
	}

}
