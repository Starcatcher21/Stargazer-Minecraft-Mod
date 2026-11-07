package com.github.starcatcher21.stargazer.block.clases;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.Crops;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
//? if >= 26.2 {
/*import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.item.ItemInstance;
*///? } else {
import net.minecraft.world.ItemInteractionResult;
//? }
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
//? if >= 26.2 {
/*import net.minecraft.world.level.block.VegetationBlock;
*///? } else {
import com.github.starcatcher21.stargazer.block.older1211.VegetationBlock;
//? }
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StarBerryBushBlock extends VegetationBlock implements BonemealableBlock {
    public static final MapCodec<StarBerryBushBlock> CODEC = simpleCodec(StarBerryBushBlock::new);
    private static final float HURT_SPEED_THRESHOLD = 0.003F;
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE;
    private static final VoxelShape SHAPE_SAPLING;
    private static final VoxelShape SHAPE_GROWING;

    public MapCodec<StarBerryBushBlock> codec() {
        return CODEC;
    }

    public StarBerryBushBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(AGE, 0));
    }

    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(Crops.STARBERRY.get());
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;
        switch ((Integer)state.getValue(AGE)) {
            case 0 -> var10000 = SHAPE_SAPLING;
            case 3 -> var10000 = Shapes.block();
            default -> var10000 = SHAPE_GROWING;
        }

        return var10000;
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return (Integer)state.getValue(AGE) < 3;
    }

    protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        int age = (Integer)state.getValue(AGE);
        if (age < 3 && random.nextInt(5) == 0 && level.getRawBrightness(pos.above(), 0) >= 9) {
            BlockState newState = (BlockState)state.setValue(AGE, age + 1);
            level.setBlock(pos, newState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(newState));
        }
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity
                                //? if >= 26.2 {
                                /*, InsideBlockEffectApplier effectApplier,
                                boolean isPrecise
                                *///? }
                                ) {
        entity.makeStuckInBlock(state, new Vec3((double)0.8F, (double)0.75F, (double)0.8F));
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            if ((Integer)state.getValue(AGE) != 0) {
                //? if >= 26.2 {
                /*Vec3 movement = entity.isClientAuthoritative() ? entity.getKnownMovement() : entity.oldPosition().subtract(entity.position());
                if (movement.horizontalDistanceSqr() > (double)0.0F) {
                    double xs = Math.abs(movement.x());
                    double zs = Math.abs(movement.z());
                    if (xs >= (double)0.003F || zs >= (double)0.003F) {
                *///? } else {
                if (!level.isClientSide && (Integer)state.getValue(AGE) > 0 && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                    double d0 = Math.abs(entity.getX() - entity.xOld);
                    double d1 = Math.abs(entity.getZ() - entity.zOld);
                    if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                //? }
                        if (entity instanceof LivingEntity le) {
                            le.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
                        }
                    }
                }
            }
        }

    }

    //? if >= 26.2 {
    /*protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int age = (Integer)state.getValue(AGE);
        boolean isMaxAge = age == 3;
        return (InteractionResult)(!isMaxAge && itemStack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(itemStack, state, level, pos, player, hand, hitResult));
    }
    *///? } else {
    protected ItemInteractionResult useItemOn(ItemStack arg, BlockState arg2, Level arg3, BlockPos arg4, Player arg5, InteractionHand arg6, BlockHitResult arg7) {
        int i = (Integer)arg2.getValue(AGE);
        boolean flag = i == 3;
        return !flag && arg.is(Items.BONE_MEAL) ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.useItemOn(arg, arg2, arg3, arg4, arg5, arg6, arg7);
    }
    //? }


    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if ((Integer)state.getValue(AGE) > 1) {
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                //? if >= 26.2 {
                /*var random = level.getRandom();
                *///? } else {
                var random = level.random;
                //? }
                popResource(level, pos, new ItemStack(Crops.STARBERRY.get(), 1 + random.nextInt(2)));
                serverLevel.playSound((Entity)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + serverLevel.getRandom().nextFloat() * 0.4F);
                BlockState newState = (BlockState)state.setValue(AGE, 1);
                serverLevel.setBlock(pos, newState, 2);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(player, newState));
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return (Integer)state.getValue(AGE) < 3;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int newAge = Math.min(3, (Integer)state.getValue(AGE) + 1);
        level.setBlock(pos, (BlockState)state.setValue(AGE, newAge), 2);
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SHAPE_SAPLING = Block.box((double)3.0F, (double)0.0F, (double)3.0F, (double)13.0F, (double)8.0F, (double)13.0F);
        SHAPE_GROWING = Block.box((double)1.0F, (double)0.0F, (double)1.0F, (double)15.0F, (double)16.0F, (double)15.0F);
    }
}
