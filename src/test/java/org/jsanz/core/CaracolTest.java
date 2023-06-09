package org.jsanz.core;

import org.jsanz.core.batch.slimefinder.algoritmo.CaracolList;
import org.jsanz.core.pojo.Point;
import org.junit.Test;

public class CaracolTest {
	
	@Test
	public void CaracolTest() {
		Point centro=new Point(0,0);
		Integer radio=10;
		
		CaracolList caracolList=new CaracolList(centro, radio);
		
		for(Point p : caracolList) {
			System.out.println(p);
		}
	}

}
