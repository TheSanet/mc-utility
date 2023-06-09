package org.jsanz.core.batch.fortressfinder.pojo;

public class FortressPiece {
	
	private PieceType pieceType;
	private int weight;
	private int maxPlaceCount;
	private int allowInRow;
	private int placeCount;
	
	public FortressPiece(PieceType pieceType, int weight, int maxPlaceCount, int allowInRow, int placeCount) {
		super();
		this.pieceType = pieceType;
		this.weight = weight;
		this.maxPlaceCount = maxPlaceCount;
		this.allowInRow = allowInRow;
		this.placeCount = placeCount;
	}
	
	public boolean isInvalid() {
	    return maxPlaceCount != 0 && placeCount >= maxPlaceCount;
	}
	
	public PieceType getPieceType() {
		return pieceType;
	}
	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getMaxPlaceCount() {
		return maxPlaceCount;
	}
	public void setMaxPlaceCount(int maxPlaceCount) {
		this.maxPlaceCount = maxPlaceCount;
	}
	public int getAllowInRow() {
		return allowInRow;
	}
	public void setAllowInRow(int allowInRow) {
		this.allowInRow = allowInRow;
	}
	public int getPlaceCount() {
		return placeCount;
	}
	public void setPlaceCount(int placeCount) {
		this.placeCount = placeCount;
	}

}


