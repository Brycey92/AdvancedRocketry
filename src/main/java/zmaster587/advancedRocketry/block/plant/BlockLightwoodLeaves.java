package zmaster587.advancedRocketry.block.plant;

<<<<<<< HEAD
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
=======
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
>>>>>>> origin/feature/nuclearthermalrockets
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import zmaster587.advancedRocketry.api.AdvancedRocketryBlocks;

<<<<<<< HEAD
import java.util.LinkedList;
=======
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
>>>>>>> origin/feature/nuclearthermalrockets
import java.util.List;
import java.util.Random;

public class BlockLightwoodLeaves extends LeavesBlock {

<<<<<<< HEAD
	public BlockLightwoodLeaves(AbstractBlock.Properties properties) {
		super(properties);
		// light value
		properties.setLightLevel(value -> {return 8;} );
		this.setDefaultState(this.stateContainer.getBaseState().with(PERSISTENT, false).with(DISTANCE, 0));
=======
	public BlockLightwoodLeaves() {
		super();
		this.lightValue = 8;
		this.setDefaultState(this.getDefaultState().withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, false));
	}
	
	@Override
    @Nonnull
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, DECAYABLE, CHECK_DECAY);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(DECAYABLE) ? 1 : 0) + (state.getValue(CHECK_DECAY) ? 2 : 0);
	}
	
	@Override
    @Nonnull
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 1) == 1).withProperty(CHECK_DECAY, (meta & 2) == 2);
>>>>>>> origin/feature/nuclearthermalrockets
	}
	
	protected static final String[] names = {"blueLeaf"};
	protected static final String[][] textures = new String[][] {{"leaves_oak"}, {"leaves_oak_opaque"}};
    
    public int quantityDropped(Random p_149745_1_)
    {
        return p_149745_1_.nextInt(100) == 0 ? 1 : 0;
    }
    
    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
    	return 50;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
    	return 50;
    }
    
    protected void func_150124_c(World world, int x, int y, int z, int p_150124_5_, int p_150124_6_)
    {
        if ((p_150124_5_ & 3) == 0 && world.rand.nextInt(p_150124_6_) == 0)
        {
            //TODO make drop
        	//this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.apple, 1, 0));
        }
    }
    
<<<<<<< HEAD
	
    public Item getItemDropped(BlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(AdvancedRocketryBlocks.blockAlienSapling);
=======
	@Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(AdvancedRocketryBlocks.blockLightwoodSapling);
    }


	@Override
    @Nonnull
	public NonNullList<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1, 0));
	}

	@Override
    @Nonnull
	public EnumType getWoodType(int meta) {
		return EnumType.OAK;
	}

	//These three methods need to be overridden
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return Blocks.LEAVES.isOpaqueCube(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getBlockLayer()
    {
        return Blocks.LEAVES.getBlockLayer();
>>>>>>> origin/feature/nuclearthermalrockets
    }

    
    @Override
<<<<<<< HEAD
    public List<ItemStack> onSheared(PlayerEntity player, ItemStack item, World world, BlockPos pos, int fortune) {
    	List<ItemStack> stackList = new LinkedList<>();
    	stackList.add(new ItemStack(this, 1));
    	return stackList;
=======
    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return Blocks.LEAVES.shouldSideBeRendered(blockState, blockAccess, pos, side);
>>>>>>> origin/feature/nuclearthermalrockets
    }
}
