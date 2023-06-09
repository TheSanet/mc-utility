package org.jsanz.core.batch.slimefinder.algoritmo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.jsanz.core.pojo.Direction;
import org.jsanz.core.pojo.Point;

public class CaracolList implements Iterable<Point>{

    /**
     * Bordes de la espiral
     */
    private final int minimaCoordX, maximaCoordX,minimaCoordZ, maximaCoordZ;

    /**
     * Punto de origen de la espiral
     */
    private final Point center;
    
    /**
     * Punto actual de la espiral
     */
    private Point actualPoint;

    //VARIABLES AUXILIARES
    
    /**
     * The direction of the next step
     */
    private Direction direccionProximaIteracion;
    
    private Integer longitudProximaIteracion;
    private Integer longitudRecoridaEnEsteGiro;

    public CaracolList(Point center, Integer radio) {
        this.maximaCoordX = center.x+radio;
        this.minimaCoordX =  center.x-radio;
        this.maximaCoordZ = center.z+radio;
        this.minimaCoordZ = center.z-radio;
        this.center = center;
        
        //VA
        this.actualPoint=center;
        longitudProximaIteracion=1;
        longitudRecoridaEnEsteGiro=0;
        direccionProximaIteracion=Direction.EAST;
    }

    /**
     * Rotates the direction of the next step clockwise
     */
    private void rota() {
        if (direccionProximaIteracion == Direction.NORTH) {
            direccionProximaIteracion = Direction.EAST;
            longitudProximaIteracion++;
        } else if (direccionProximaIteracion == Direction.EAST) {
            direccionProximaIteracion = Direction.SOUTH;
        } else if (direccionProximaIteracion == Direction.SOUTH) {
            direccionProximaIteracion = Direction.WEST;
            longitudProximaIteracion++;
        } else if (direccionProximaIteracion == Direction.WEST) {
            direccionProximaIteracion = Direction.NORTH;
        }
        longitudRecoridaEnEsteGiro=0;
    }

    /**
     * Move this point in the given direction 
     */
    public Point move1(Point point, Direction direction) {
        point.x=point.x+direction.x;
        point.x=point.z+direction.z;
		return point;
    }

	public Point getCenter() {
		return center;
	}
	
	public Point getNext() {
		Point nextPoint=new Point();
		nextPoint.x=new Integer(actualPoint.x);
		nextPoint.z=new Integer(actualPoint.z);
		avanza();
		
		if(nextPoint.x>maximaCoordX || nextPoint.x<minimaCoordX ||nextPoint.z<minimaCoordZ || nextPoint.z>maximaCoordZ) {
			return null;
		}
		
		return nextPoint;
	}

	private void avanza() {
		Point proximoPunto=new Point();
		if(longitudRecoridaEnEsteGiro<longitudProximaIteracion) {
			proximoPunto.x=actualPoint.x+direccionProximaIteracion.x;
			proximoPunto.z=actualPoint.z+direccionProximaIteracion.z;
		}else {
			rota();
			proximoPunto.x=actualPoint.x+direccionProximaIteracion.x;
			proximoPunto.z=actualPoint.z+direccionProximaIteracion.z;
		}
		longitudRecoridaEnEsteGiro++;
		actualPoint=proximoPunto;
	}

	@Override
	public Iterator<Point> iterator() {
		return new Iterator<Point>() {

			@Override
			public boolean hasNext() {
				if(actualPoint.x==maximaCoordX || actualPoint.x==minimaCoordX ||actualPoint.z==minimaCoordZ || actualPoint.z==maximaCoordZ) {
					return false;
				}
				return true;
			}

			@Override
			public Point next() {
				return getNext();
			}
		};
	}
 
}
