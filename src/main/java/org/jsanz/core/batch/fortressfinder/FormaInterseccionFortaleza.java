package org.jsanz.core.batch.fortressfinder;

public enum FormaInterseccionFortaleza {

	 DOUBLE(0), TRIPLE_LINE(1), QUAD_LINE(2), QUINT_LINE(3), TRIPLE_CORNER(4),QUAD_SQUARE(5), QUINT_BLOB(6);
	
	private int value;

	private FormaInterseccionFortaleza(int value) {
		this.value = value;
	}
}
