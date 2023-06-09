package org.jsanz.core.batch.fortressfinder.algoritmo;

import static amidst.mojangapi.world.versionfeatures.FeatureKey.END_ISLAND_ORACLE;
import static amidst.mojangapi.world.versionfeatures.FeatureKey.STRONGHOLD_PRODUCER;
import static amidst.mojangapi.world.versionfeatures.FeatureKey.WORLD_OPTIONS;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.swing.SwingUtilities;

import org.jsanz.core.pojo.Point;

import amidst.logging.AmidstLogger;
import amidst.mojangapi.minecraftinterface.MinecraftInterface;
import amidst.mojangapi.minecraftinterface.MinecraftInterfaceException;
import amidst.mojangapi.minecraftinterface.RecognisedVersion;
import amidst.mojangapi.world.Dimension;
import amidst.mojangapi.world.World;
import amidst.mojangapi.world.WorldBuilder;
import amidst.mojangapi.world.WorldOptions;
import amidst.mojangapi.world.WorldSeed;
import amidst.mojangapi.world.WorldType;
import amidst.mojangapi.world.biome.Biome;
import amidst.mojangapi.world.biome.BiomeList;
import amidst.mojangapi.world.coordinates.CoordinatesInWorld;
import amidst.mojangapi.world.coordinates.Resolution;
import amidst.mojangapi.world.icon.locationchecker.LocationChecker;
import amidst.mojangapi.world.icon.locationchecker.StructureBiomeLocationChecker;
import amidst.mojangapi.world.oracle.BiomeDataOracle;
import amidst.mojangapi.world.oracle.BiomeDataOracle.Config;
import amidst.mojangapi.world.versionfeatures.DefaultVersionFeatures;
import amidst.mojangapi.world.versionfeatures.FeatureKey;
import amidst.mojangapi.world.versionfeatures.VersionFeatures;

import static amidst.mojangapi.world.versionfeatures.FeatureKey.*;

public class FortressGeneratorV2 {
	
	private static int REGION_LONG_IN_CHUNKS=32;
	
	private static List<Biome> NETHER_BIOME_LIST;
	
	static {
		//TODO
//		NETHER_BIOME_LIST.add(Biome.);
	}
	

	private MinecraftInterface minecraftInterface;
//	private final StructureWorker structureWorker;

	private static final WorldBuilder worldBuilder = WorldBuilder.createSilentPlayerless();

	public Point generateForRegion(Long seed, Integer regionX, Integer regionZ) throws MinecraftInterfaceException, InvocationTargetException, InterruptedException {
		// genera la fortress
		WorldOptions worldOptions = new WorldOptions(WorldSeed.fromSaveGame(seed), WorldType.DEFAULT);
		World world = worldBuilder.from(minecraftInterface, worldOptions);
		VersionFeatures versionFeatures = DefaultVersionFeatures.builder(worldOptions, null).create(RecognisedVersion._1_17);
		
		BiomeDataOracle dbo=new BiomeDataOracle(null, Dimension.NETHER, BiomeList.construct(null), Config)
		LocationChecker lc= new StructureBiomeLocationChecker(null, REGION_LONG_IN_CHUNKS, NETHER_BIOME_LIST);
		
		int structureOffset = parseStructureOffset("Nether Fortress");
		scanForStructure(lc, new CoordinatesInWorld(regionX, regionZ), Resolution.NETHER_CHUNK, REGION_LONG_IN_CHUNKS, structureOffset);
		
		LocationChecker checker= new StructureBiomeLocationChecker(null, 0, null);
		// analiza las partes
	}

	public ArrayList<Point> scanForStructure(LocationChecker checker, CoordinatesInWorld start, Resolution res, int r,
			int structureOffset) throws InvocationTargetException, InterruptedException {
		ArrayList<Point> retorno =new ArrayList<>();
		CoordinatesInWorld newCoords = null;
		for (long x = -r; x <= r; x++) {
			for (long y = -r; y <= r; y++) {
				if (checker.isValidLocation((int) (start.getXAs(res) + x), (int) (start.getYAs(res) + y))) {
					newCoords = CoordinatesInWorld.from(
							start.getX() + res.convertFromThisToWorld(x)
									+ Resolution.NETHER.convertFromWorldToThis(structureOffset),
							start.getY() + res.convertFromThisToWorld(y)
									+ Resolution.NETHER.convertFromWorldToThis(structureOffset));
					Integer xAux = ((Long)newCoords.getX()) != null ? ((Long)newCoords.getX()).intValue() : null;
					Integer zAux = ((Long)newCoords.getY()) != null ? ((Long)newCoords.getY()).intValue() : null;
					Point point=new Point(xAux,zAux);
					retorno.add(point);
				}

			}
		}
		return retorno;
	}

	public int parseStructureOffset(String structtype) {
		switch (structtype) {
		case "Village":
			return 4;
		case "Mineshaft":
			return 8;
		case "Mansion":
			return 8;
		case "Jungle Temple":
			return 8;
		case "Desert Temple":
			return 8;
		case "Igloo":
			return 8;
		case "Shipwreck":
			return 8;
		case "Witch Hut":
			return 8;
		case "Stronghold":
			return 4;
		case "Ocean Monument":
			return 8;
		case "Ocean Ruin":
			return 8;
		case "Nether Fortress":
			return 88;
		case "End City":
			return 8;
		case "Buried Treasure":
			return 9;
		case "Pillager Outpost":
			return 4;
		default:
			break;
		}
		return 0;
	}

	private class StructureWorker extends ThreadPoolExecutor {
		private final List<Future<?>> futures;

		public StructureWorker() {
			super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					return new Thread(r, "StructureWorker");
				}
			});
			this.futures = new ArrayList<Future<?>>();
		}

	}

}
