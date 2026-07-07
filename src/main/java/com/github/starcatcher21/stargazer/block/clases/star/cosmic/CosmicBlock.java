package com.github.starcatcher21.stargazer.block.clases.star.cosmic;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.particle.Particles;
import com.mojang.serialization.MapCodec;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CosmicBlock extends BaseEntityBlock {
    private final static Random random = new Random();
    private final static float velocity = 0.06F;
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/dream_block.png");
    @Override
    protected MapCodec<? extends CosmicBlock> codec() {
        return simpleCodec(CosmicBlock::new);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CosmicBlockEntity(pos, state);
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        if (context.isHoldingItem(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_block"))) || context.isHoldingItem(ModItems.STAR_HAMMER)) {
            return Shapes.block();
        } else {
            return Shapes.box(0.0, 0.0, 0.0, 0.01, 0.01, 0.01);
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (world instanceof ServerLevel sw) {
            sw.sendParticles((SimpleParticleType) Particles.STAR, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, 5, -velocity + random.nextFloat(velocity*2), -velocity + random.nextFloat(velocity*2), -velocity + random.nextFloat(velocity*2), 0.02d);
        }
        world.playLocalSound(pos, this.soundType.getBreakSound(), SoundSource.BLOCKS, this.soundType.volume*2, this.soundType.pitch, true);
        world.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, state));
        return state;
    }


    public CosmicBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return (context.getItemInHand().isEmpty() || !context.getItemInHand().is(this.asItem()));
    }

    @Override
    public boolean isPossibleToRespawnInThis(BlockState state) {
        return true;
    }
}
