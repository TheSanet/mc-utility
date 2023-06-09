package org.jsanz.core.batch.fortressfinder.pojo;

public enum PieceType {
			BRIDGE(0),
		    BRIDGE_CROSSING(1),
		    BRIDGE_SMALL_CROSSING(2),
		    BRIDGE_STAIRS(3),
		    BRIDGE_PLATFORM(4),
		    CORRIDOR_EXIT(5),
		    SMALL_CORRIDOR(6),
		    CORRIDOR_RIGHT_TURN(7),
		    CORRIDOR_LEFT_TURN(8),
		    CORRIDOR_STAIRS(9),
		    CORRIDOR_BALCONY(10),
		    CORRIDOR_CROSSING(11),
		    CORRIDOR_NETHER_WARTS_ROOM(12),
		    BRIDGE_END(13);
			
			PieceType(int i) {
				id=i;
			}

			int id;

}
