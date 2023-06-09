package org.jsanz.core.batch.fortressfinder.pojo;

public class Piece {
    PieceType pieceType;
    BlockBox boundingBox;
    Direction facing;
    int length;
	public PieceType getPieceType() {
		return pieceType;
	}
	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}
	public BlockBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BlockBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	public Direction getFacing() {
		return facing;
	}
	public void setFacing(Direction facing) {
		this.facing = facing;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
}
