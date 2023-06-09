package org.jsanz.front.view.component.result;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class Table<T> extends JPanel {
	
	private ArrayList<T> l;

	public Table(List<T> l) {
		super();

		setLayout(new BorderLayout());
		JTable tabla = new JTable(new AbstractTableModel() {
			@Override
			public int getRowCount() {
				return l.size();
			}

			@Override
			public int getColumnCount() {
				return l.getClass().getDeclaredFields().length;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				T t= l.get(rowIndex);
				try {
					return ((l.getClass().getDeclaredFields())[columnIndex]).get(l);
				} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		});
		// La tabla se añade a un ScrollPane para que sea éste el
		// que controle automáticamente en tamaño de la tabla,
		// presentando una barra de desplazamiento cuando sea
		// necesario
		JScrollPane panel = new JScrollPane(tabla);
		add(panel, BorderLayout.CENTER);
	}

}


