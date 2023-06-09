package org.jsanz.core.batch.fortressfinder.pojo;

public class BlockBox {
	
	 int minX, minY, minZ;
	 int maxX, maxY, maxZ;

	
	public BlockBox rotate(int x, int y, int z, int offsetX, int offsetY, int offsetZ, int sizeX, int sizeY, int sizeZ, Direction facing) {
	    switch(facing) {
	        case NORTH:
	            minX = x + offsetX;
	            minY = y + offsetY;
	            minZ = z - sizeZ + 1 + offsetZ;
	            maxX = x + sizeX - 1 + offsetX;
	            maxY = y + sizeY - 1 + offsetY;
	            maxZ = z + offsetZ;
	            break;
	        case SOUTH:
	            minX = x + offsetX;
	            minY = y + offsetY;
	            minZ = z + offsetZ;
	            maxX = x + sizeX - 1 + offsetX;
	            maxY = y + sizeY - 1 + offsetY;
	            maxZ = z + sizeZ - 1 + offsetZ;
	            break;
	        case WEST:
	            minX = x - sizeZ + 1 + offsetZ;
	            minY = y + offsetY;
	            minZ = z + offsetX;
	            maxX = x + offsetZ;
	            maxY = y + sizeY - 1 + offsetY;
	            maxZ = z + sizeX - 1 + offsetX;
	            break;
	        case EAST:
	            minX = x + offsetZ;
	            minY = y + offsetY;
	            minZ = z + offsetX;
	            maxX = x + sizeZ - 1 + offsetZ;
	            maxY = y + sizeY - 1 + offsetY;
	            maxZ = z + sizeX - 1 + offsetX;
	            break;
	    }
	    
	    return this;
	}

	public boolean intersects(BlockBox other) {
		return
			maxX >= other.getMinX() &&
			minX <= other.getMaxX() &&
			maxZ >= other.getMinZ() &&
			minZ <= other.getMaxZ() &&
			maxY >= other.getMinY() &&
			minY <= other.getMaxY();
	}

	void blockBox_encompass(BlockBox region) {
	    minX = Math.min(minX, region.getMinX());
	    minY = Math.min(minY, region.getMinY());
	    minZ = Math.min(minZ, region.getMinZ());
	    maxX = Math.max(maxX, region.getMaxX());
	    maxY = Math.max(maxY, region.getMaxY());
	    maxZ = Math.max(maxZ, region.getMaxZ());
	}



	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMinZ() {
		return minZ;
	}

	public void setMinZ(int minZ) {
		this.minZ = minZ;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMaxZ() {
		return maxZ;
	}

	public void setMaxZ(int maxZ) {
		this.maxZ = maxZ;
	}
}