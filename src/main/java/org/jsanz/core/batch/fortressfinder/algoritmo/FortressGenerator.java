package org.jsanz.core.batch.fortressfinder.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jsanz.core.batch.fortressfinder.pojo.Direction;
import org.jsanz.core.batch.fortressfinder.pojo.FortressPiece;
import org.jsanz.core.batch.fortressfinder.pojo.Piece;
import org.jsanz.core.batch.fortressfinder.pojo.PieceType;
import org.jsanz.core.pojo.Point;

public class FortressGenerator {
	
	private static final Integer spacing=27;
	private static final Integer separation=4;
	private static final Integer salt=30084232;
	
	private static final long RANDOM_MULTIPLIER= 25214903917L;
	private static final long RANDOM_ADDEND =11L;
	private static final long RANDOM_MASK =281474976710655L;
	
	private static final FortressPiece BRIDGE=new FortressPiece(PieceType.BRIDGE,30,0,1,0);
	private static final FortressPiece BRIDGE_CROSSING=new FortressPiece(PieceType.BRIDGE_CROSSING,10,4,0,0);
	private static final FortressPiece BRIDGE_SMALL_CROSSING=new FortressPiece(PieceType.BRIDGE_SMALL_CROSSING,10,4,0,0);
	private static final FortressPiece BRIDGE_STAIRS=new FortressPiece(PieceType.BRIDGE_STAIRS,10,4,0,0);
	private static final FortressPiece BRIDGE_PLATFORM=new FortressPiece(PieceType.BRIDGE_PLATFORM,10,4,0,0);
	private static final FortressPiece CORRIDOR_EXIT=new FortressPiece(PieceType.CORRIDOR_EXIT,10,4,0,0);
	private static final List<FortressPiece> BRIDGE_PIECE_WEIGHTS= Arrays.asList(BRIDGE,BRIDGE_CROSSING,BRIDGE_SMALL_CROSSING,BRIDGE_STAIRS,BRIDGE_PLATFORM,CORRIDOR_EXIT);
	
	private static final FortressPiece SMALL_CORRIDOR=new FortressPiece(PieceType.SMALL_CORRIDOR,25,0,1,0);
	private static final FortressPiece CORRIDOR_CROSSING=new FortressPiece(PieceType.CORRIDOR_CROSSING,15,5,0,0);
	private static final FortressPiece CORRIDOR_RIGHT_TURN=new FortressPiece(PieceType.CORRIDOR_RIGHT_TURN,5,10,0,0);
	private static final FortressPiece CORRIDOR_LEFT_TURN=new FortressPiece(PieceType.CORRIDOR_LEFT_TURN,5,10,0,0);
	private static final FortressPiece CORRIDOR_STAIRS=new FortressPiece(PieceType.CORRIDOR_STAIRS,10,3,0,0);
	private static final FortressPiece CORRIDOR_BALCONY=new FortressPiece(PieceType.CORRIDOR_BALCONY,7,2,0,0);
	private static final FortressPiece CORRIDOR_NETHER_WARTS_ROOM=new FortressPiece(PieceType.CORRIDOR_NETHER_WARTS_ROOM,5,2,0,0);
	private static final List<FortressPiece> CASTLE_PIECE_WEIGHTS= Arrays.asList(SMALL_CORRIDOR,CORRIDOR_CROSSING,CORRIDOR_RIGHT_TURN,CORRIDOR_LEFT_TURN,CORRIDOR_STAIRS,CORRIDOR_BALCONY,CORRIDOR_NETHER_WARTS_ROOM);
	
	
	private int corridorPiecesCount;
	private int bridgePiecesCount;
	
	public void processRegion() {
		//genera la fortress
		//analiza las partes
	}

	/**
	 * 
	 * @param seed
	 * @param regionX coordenada x, expresada en modod region, ed block/27 (version > 1.16)/16
	 * @param regionZ coordenada z, expresada en modod region, ed block/27 (version > 1.16)/16
	 * @param rand
	 * @return
	 */
	public Point generateForRegion(Long seed, Integer regionX, Integer regionZ) {
	    Random random;
	    //random_setSeed(random, regionX * 341873128712LLU + regionZ * 132897987541LLU + structureSeed + (uint64_t)(int64_t)salt);
	    //seed = (seed ^ RANDOM_MULTIPLIER) & RANDOM_MASK;
	    long ramdomSeed=((regionX*341873128712L+regionZ*132897987541L+seed)^RANDOM_MULTIPLIER) & 281474976710655L;
	    random=new Random(ramdomSeed);
	    
	    int bits=random_next(random,ramdomSeed, 31);
	    int val = bits % 23;
	    while ((int)(bits - val + 22) < 0) {
    		bits = (int)random_next(random,ramdomSeed, 31);
    		val = bits % 23;
    	}
        int chunkX = regionX * 27 + val;
        int chunkZ = regionZ * 27 + val;
        
        val = bits % 5;
	    while ((int)(bits - val + 4) < 0) {
    		bits = (int)random_next(random,ramdomSeed, 5);
    		val = bits % 5;
    	}
        
        if (val < 2) {
//            return generateForChunk(fortressGenerator, structureSeed, chunkX, chunkZ, version, random);
        }
		return null;
	}
	
	private Point generateForChunk(Long seed, int chunkX, int chunkZ,Random random,long ramdomSeed) {
	    // printf("Running for chunk %i %i\n", chunkX, chunkZ);
//		PieceType lastPieceType = -1;
	    int pendingChildrenCount = 0;
	    int piecesCount = 0;
	    ArrayList<FortressPiece> fortressPiece=new ArrayList<>();
	    fortressPiece.addAll(BRIDGE_PIECE_WEIGHTS);
	    fortressPiece.addAll(CASTLE_PIECE_WEIGHTS);
	    bridgePiecesCount = BRIDGE_PIECE_WEIGHTS.size();
	    corridorPiecesCount = CASTLE_PIECE_WEIGHTS.size();
	    
	    int startX = (chunkX << 4) + 2;
	    int startZ = (chunkZ << 4) + 2;
	    
//	    if (version >= v1_13) {
//	        random_setCarverSeed(&(fortressGenerator->random), structureSeed, chunkX, chunkZ);
//	    }

	    Piece start =new Piece();
	    start.setPieceType(PieceType.BRIDGE_CROSSING);
	    start.getBoundingBox().setMinX(startX);
	    start.getBoundingBox().setMinY(64);
	    start.getBoundingBox().setMinZ(startZ);
	    start.getBoundingBox().setMaxX(startX + 19 - 1);
	    start.getBoundingBox().setMaxY(73);
	    start.getBoundingBox().setMaxZ(startZ + 19 - 1);
	    long direccion=(4L * random_next(random,ramdomSeed, 31)) >> 31;
	    if(direccion==1L) {
	    	start.setFacing(Direction.NORTH);
	    }else if(direccion==2L) {
	    	start.setFacing(Direction.EAST);
	    }else if(direccion==3L){
	    	start.setFacing(Direction.SOUTH);
	    }else if(direccion==4L){
	    	start.setFacing(Direction.WEST);
	    }
	    
	    
	    startsetLength(0);
	    fortressGeneratorsetPiecesCount(1);
	    
	    // printf("Placing piece %s at /tp %i ~ %i\n", PIECE_TYPE_NAMES[start->pieceType], start->boundingBox.minX, start->boundingBox.minZ);
	    // printf("Placing children for %s at /tp %i ~ %i\n", PIECE_TYPE_NAMES[start->pieceType], start->boundingBox.minX, start->boundingBox.minZ);
	    fortressGenerator_placeJigsaw(fortressGenerator, start);

	    while (fortressGenerator->pendingChildrenCount != 0) {
	        // printf("Placing a piece, %i left\n", fortressGenerator->pendingChildrenCount);

	        int i = random_nextIntUnknown(&(fortressGenerator->random), fortressGenerator->pendingChildrenCount);
	        Piece *piece = fortressGenerator->pendingChildren[i];
	        if(i < fortressGenerator->pendingChildrenCount - 1) {
	            memmove(&(fortressGenerator->pendingChildren[i]),
	                    &(fortressGenerator->pendingChildren[i]) + 1,
	                    (fortressGenerator->pendingChildrenCount - i - 1) * sizeof(Piece*));
	        }
	        fortressGenerator->pendingChildrenCount--;
	        // printf("Placing children for %s at /tp %i ~ %i\n", PIECE_TYPE_NAMES[piece->pieceType], piece->boundingBox.minX, piece->boundingBox.minZ);
	        fortressGenerator_placeJigsaw(fortressGenerator, piece);
	    }

	    BlockBox boundingBox = start->boundingBox;
	    for (int i = 1; i < fortressGenerator->piecesCount; i++) {
	        blockBox_encompass(&boundingBox, &(fortressGenerator->pieces[i].boundingBox));
	    }

	    int32_t k = 23 - (boundingBox.maxY - boundingBox.minY + 1);
	    int32_t m = k > 1 ? 48 + random_nextIntUnknown(&(fortressGenerator->random), k) : 48;
	    int32_t offsetY = m - boundingBox.minY;

	    for (int i = 0; i < fortressGenerator->piecesCount; i++) {
	        fortressGenerator->pieces[i].boundingBox.minY += offsetY;
	        fortressGenerator->pieces[i].boundingBox.maxY += offsetY;
	    }
	}
	
	
	
	private int random_next(Random random, long ramdomSeed, int bits) {
		long seed = (ramdomSeed * RANDOM_MULTIPLIER + RANDOM_ADDEND) & RANDOM_MASK;
		return (int)(seed >> (48 - bits));
	}

}
