package zmaster587.advancedRocketry.block;

<<<<<<< HEAD
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import zmaster587.libVulpes.block.BlockTile;
import zmaster587.libVulpes.inventory.GuiHandler;
=======
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import zmaster587.libVulpes.block.BlockTile;
import zmaster587.libVulpes.block.BlockTileComparatorOverride;
>>>>>>> origin/feature/nuclearthermalrockets
import zmaster587.libVulpes.util.IAdjBlockUpdate;

public class BlockTileNeighborUpdate extends BlockTileComparatorOverride {

	/**
	 * @param tileClass must extend IAdjBlockUpdate
	 */
<<<<<<< HEAD
	public BlockTileNeighborUpdate(Properties properties,
			GuiHandler.guiId guiId) {
		super(properties, guiId);
	}
	
	@Override
	public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
		super.onNeighborChange(state, world, pos, neighbor);
=======
	public BlockTileNeighborUpdate(Class<? extends TileEntity> tileClass, int guiId) {
		super(tileClass, guiId);
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		super.onNeighborChange(world, pos, neighbor);
>>>>>>> origin/feature/nuclearthermalrockets
		TileEntity tile = world.getTileEntity(pos);
		
		if(tile instanceof IAdjBlockUpdate)
			((IAdjBlockUpdate)tile).onAdjacentBlockUpdated();
	}
<<<<<<< HEAD
=======

>>>>>>> origin/feature/nuclearthermalrockets
}
