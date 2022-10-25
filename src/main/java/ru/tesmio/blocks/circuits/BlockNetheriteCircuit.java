package ru.tesmio.blocks.circuits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class BlockNetheriteCircuit extends Block {
    protected static VoxelShape SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty DISSECTION = IntegerProperty.create("diss", 0,8);
    public BlockNetheriteCircuit(Properties properties, VoxelShape shape) {
        super(properties);
        this.SHAPE = shape;
        this.setDefaultState(this.stateContainer.getBaseState().with(DISSECTION, 0).with(WATERLOGGED, Boolean.FALSE));
    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(worldIn.getBlockState(pos).isAir()) {
            if (state.get(DISSECTION) == 0) {
                state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegBlocks.NETHERITE_CIRCUIT.get(), 1));
            }
        }
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if(player.getHeldItemMainhand().getItem() == RegItems.WIRE_CUTTERS.get()) {
            if (worldIn instanceof ServerWorld) {
                switch (state.get(DISSECTION)) {
                    case 0:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 1));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.GREEN_CONDENSER.get(), 24));
                        player.getHeldItemMainhand().damageItem(24, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 1:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 2));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.YELLOW_CONDENSER.get(), 30));
                        player.getHeldItemMainhand().damageItem(30, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 2:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 3));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.BLUE_CONDENSER.get(), 20));
                        player.getHeldItemMainhand().damageItem(20, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 3:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 4));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DIODE_BLACK.get(), 19));
                        player.getHeldItemMainhand().damageItem(19, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 4:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 5));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DIODE_GRAY.get(), 2));
                        player.getHeldItemMainhand().damageItem(2, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 5:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 6));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_YELLOW_MICRO.get(), 34));
                        player.getHeldItemMainhand().damageItem(34, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 6:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 7));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_BLUE_MICRO.get(), 15));
                        player.getHeldItemMainhand().damageItem(15, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 7:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 8));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.GREEN_JACK.get(), 2));
                        player.getHeldItemMainhand().damageItem(2, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 8:
                        worldIn.setBlockState(pos, RegBlocks.NETHERITE_CIRCUIT_EMPTY.get().getDefaultState());
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.YELLOW_JACK.get(), 2));
                        player.getHeldItemMainhand().damageItem(2, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                }

            }
        }
        return ActionResultType.SUCCESS;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DISSECTION, WATERLOGGED);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
