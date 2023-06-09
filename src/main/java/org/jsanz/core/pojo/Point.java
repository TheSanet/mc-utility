package org.jsanz.core.pojo;

import java.util.Objects;

import jakarta.persistence.Embeddable;



/**
 * Represents 2 dimensional Integer coordinates.
 */
@Embeddable
public class Point {

    public Integer x;
	public Integer z;
	
    public Point() {
		super();
	}

    public Point(Integer x, Integer z) {
        setPoint(x, z);
    }

    public Point(Point p) {
		x=p.x;
		z=p.z;
	}

	public void setPoint(Integer x, Integer z) {
        this.x = x;
        this.z = z;
    }

    public void setPoint(Point point) {
        this.x = point.x;
        this.z = point.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
            z == point.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    public String toString() {
        return x + "," + z;
    }
}
