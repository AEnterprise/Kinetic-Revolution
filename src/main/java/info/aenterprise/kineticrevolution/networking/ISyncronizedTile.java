package info.aenterprise.kineticrevolution.networking;

import net.minecraft.util.BlockPos;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public interface ISyncronizedTile extends ISyncObject {

	BlockPos getBlockPos();

	int getIdentifier(); //used to verify it's read by the right TE type, protection against other mods messing up my TE's
}
