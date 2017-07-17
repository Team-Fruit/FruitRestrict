package net.teamfruit.fruitrestrict;

import com.feed_the_beast.ftbl.lib.math.BlockPosContainer;
import com.feed_the_beast.ftbl.lib.math.ChunkDimPos;
import com.feed_the_beast.ftbu.FTBUPermissions;
import com.feed_the_beast.ftbu.api.chunks.BlockInteractionType;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClaimedChunkHandler {

	public static final ClaimedChunkHandler INSTANCE = new ClaimedChunkHandler();

	private ClaimedChunkHandler() {
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onBlockBreak(final BlockEvent.BreakEvent event) {
		if (event.getPlayer() instanceof EntityPlayerMP&&!canPlayerInteract((EntityPlayerMP) event.getPlayer(), EnumHand.MAIN_HAND, new BlockPosContainer(event.getWorld(), event.getPos(), event.getState()), BlockInteractionType.EDIT))
			event.setCanceled(true);
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onBlockPlace(final BlockEvent.PlaceEvent event) {
		if (event.getPlayer() instanceof EntityPlayerMP&&!canPlayerInteract((EntityPlayerMP) event.getPlayer(), EnumHand.MAIN_HAND, new BlockPosContainer(event.getWorld(), event.getPos(), event.getPlacedBlock()), BlockInteractionType.EDIT))
			event.setCanceled(true);
	}

	private static boolean canPlayerInteract(final EntityPlayerMP ep, final EnumHand hand, final BlockPosContainer block, final BlockInteractionType type) {
		if (FTBUPermissions.canModifyBlock(ep, hand, block, type))
			return true;
		if (FTBUIntegration.API.getClaimedChunks().getChunk(new ChunkDimPos(block.getPos(), ep.dimension))==null)
			return false;
		return true;
	}

}
