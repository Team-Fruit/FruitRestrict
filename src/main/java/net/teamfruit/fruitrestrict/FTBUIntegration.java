package net.teamfruit.fruitrestrict;

import com.feed_the_beast.ftbu.api.FTBUtilitiesAPI;
import com.feed_the_beast.ftbu.api.FTBUtilitiesPlugin;
import com.feed_the_beast.ftbu.api.IFTBUtilitiesPlugin;

import net.minecraftforge.common.MinecraftForge;

public enum FTBUIntegration implements IFTBUtilitiesPlugin {
	@FTBUtilitiesPlugin INSTANCE;

	public static FTBUtilitiesAPI API;

	@Override
	public void init(final FTBUtilitiesAPI api) {
		API = api;
		MinecraftForge.EVENT_BUS.register(ClaimedChunkHandler.INSTANCE);
	}
}
