package org.jsanz.front.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jsanz.front.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Menu extends JMenuBar {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MenuController menuController;

	private FlowLayout layout;

	private JMenu menuFinder;
	private String FRM_MENU_FINDER = "menuFinder";

	private JMenuItem menuItemSlimeFinderSearch;
	public static String FRM_MENU_SLIME_FINDER_SEARCH = "menuItemSlimeFinderSearch";

	private JMenuItem menuItemFortressFinderSearch;
	public static String FRM_MENU_FORTRESS_FINDER_SEARCH = "menuItemFortressFinderSearch";

	private JMenuItem menuItemMonumentFinderSearch;
	public static String FRM_MENU_MONUMENT_FINDER_SEARCH = "menuItemMonumentFinderSearch";
	
	private JMenu menuResult;
	private String FRM_MENU_RESULT = "menuResult";

	private JMenuItem menuItemSlimeResult;
	public static String FRM_MENU_SLIME_RESULT = "menuItemSlimeResult";

	private JMenuItem menuItemFortressResult;
	public static String FRM_MENU_FORTRESS_RESULT = "menuItemFortressResult";

	private JMenuItem menuItemMonumentResult;
	public static String FRM_MENU_MONUMENT_RESULT = "menuItemMonumentResult";

	private JMenu menuConfiguracion;
	private String FRM_MENU_CONFIGURACION = "menuConfiguracion";

	private JMenuItem menuItemSeed;
	public static String FRM_MENU_CONFIGURACION_SEED = "menuConfiguracionSeed";

	@PostConstruct
	private void init() {
		//finder
		menuFinder = new JMenu(messageSource.getMessage(FRM_MENU_FINDER, new Object[] { FRM_MENU_FINDER }, Locale.getDefault()));
		this.add(menuFinder);
		
		menuItemSlimeFinderSearch = new JMenuItem(messageSource.getMessage(FRM_MENU_SLIME_FINDER_SEARCH, new Object[] { FRM_MENU_SLIME_FINDER_SEARCH }, Locale.getDefault()));
		menuItemSlimeFinderSearch.addActionListener(menuController);
		menuItemSlimeFinderSearch.setActionCommand(FRM_MENU_SLIME_FINDER_SEARCH);
		menuFinder.add(menuItemSlimeFinderSearch);

		menuItemFortressFinderSearch = new JMenuItem(messageSource.getMessage(FRM_MENU_FORTRESS_FINDER_SEARCH, new Object[] { FRM_MENU_FORTRESS_FINDER_SEARCH }, Locale.getDefault()));
		menuItemFortressFinderSearch.addActionListener(menuController);
		menuItemFortressFinderSearch.setActionCommand(FRM_MENU_FORTRESS_FINDER_SEARCH);
		menuFinder.add(menuItemFortressFinderSearch);

		menuItemMonumentFinderSearch = new JMenuItem(messageSource.getMessage(FRM_MENU_MONUMENT_FINDER_SEARCH, new Object[] { FRM_MENU_MONUMENT_FINDER_SEARCH }, Locale.getDefault()));
		menuItemMonumentFinderSearch.addActionListener(menuController);
		menuItemMonumentFinderSearch.setActionCommand(FRM_MENU_MONUMENT_FINDER_SEARCH);
		menuFinder.add(menuItemMonumentFinderSearch);
		
		//result
		menuResult = new JMenu(messageSource.getMessage(FRM_MENU_RESULT, new Object[] { FRM_MENU_RESULT }, Locale.getDefault()));
		this.add(menuResult);
		
		menuItemSlimeResult = new JMenuItem(messageSource.getMessage(FRM_MENU_SLIME_RESULT, new Object[] { FRM_MENU_SLIME_RESULT }, Locale.getDefault()));
		menuItemSlimeResult.addActionListener(menuController);
		menuItemSlimeResult.setActionCommand(FRM_MENU_SLIME_RESULT);
		menuResult.add(menuItemSlimeResult);

		menuItemFortressResult = new JMenuItem(messageSource.getMessage(FRM_MENU_FORTRESS_RESULT, new Object[] { FRM_MENU_FORTRESS_RESULT }, Locale.getDefault()));
		menuItemFortressResult.addActionListener(menuController);
		menuItemFortressResult.setActionCommand(FRM_MENU_FORTRESS_RESULT);
		menuResult.add(menuItemFortressResult);

		menuItemMonumentResult = new JMenuItem(messageSource.getMessage(FRM_MENU_MONUMENT_RESULT, new Object[] { FRM_MENU_MONUMENT_RESULT }, Locale.getDefault()));
		menuItemMonumentResult.addActionListener(menuController);
		menuItemMonumentResult.setActionCommand(FRM_MENU_MONUMENT_RESULT);
		menuResult.add(menuItemMonumentResult);

		//config
		menuConfiguracion = new JMenu(messageSource.getMessage(FRM_MENU_CONFIGURACION, new Object[] { FRM_MENU_CONFIGURACION }, Locale.getDefault()));
		this.add(menuConfiguracion);
		menuItemSeed = new JMenuItem(messageSource.getMessage(FRM_MENU_CONFIGURACION_SEED, new Object[] { FRM_MENU_CONFIGURACION_SEED }, Locale.getDefault()));
		menuItemSeed.addActionListener(menuController);
		menuItemSeed.setActionCommand(FRM_MENU_CONFIGURACION_SEED);
		menuConfiguracion.add(menuItemSeed);

	}

}
