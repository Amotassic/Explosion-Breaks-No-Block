package com.amotassic.explosionbreaksnoblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ExplosionBreaksNoBlock.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockRemoveCache {
    public static final Map<ResourceKey<Level>, Map<BlockPos, String>> blockRemoved = new HashMap<>();

    public static void record(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        blockRemoved.computeIfAbsent(level.dimension(), k -> new HashMap<>())
                .put(pos, BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString());
    }

    public static String getBlockId(Level level, BlockPos pos) {
        return blockRemoved.getOrDefault(level.dimension(), new HashMap<>())
                // 如果没有记录该坐标的方块，就获取该坐标的方块，以防没有移除方块但产生了爆炸（会有人这样做吗？）
                .getOrDefault(pos, BuiltInRegistries.BLOCK.getKey(level.getBlockState(pos).getBlock()).toString());
    }

    public static String getBlockId(Level level, Object... pos) {
        BlockPos blockPos = toBlockPos(pos);
        if (blockPos == null) return "minecraft:air";
        return getBlockId(level, blockPos);
    }

    public static BlockPos toBlockPos(Object... pos) {
        if (pos.length == 3) {
            return new BlockPos(floorToInt(pos[0]), floorToInt(pos[1]), floorToInt(pos[2]));
        }
        if (pos.length == 1) {
            if (pos[0] instanceof BlockPos blockPos) return blockPos;
            if (pos[0] instanceof Vec3 vec3) {
                return new BlockPos(floorToInt(vec3.x), floorToInt(vec3.y), floorToInt(vec3.z));
            }
        }
        return null;
    }

    private static int floorToInt(Object value) {
        if (value instanceof Byte) return (int) Math.floor((byte) value);
        if (value instanceof Short) return (int) Math.floor((short) value);
        if (value instanceof Integer) return (int) Math.floor((int) value);
        if (value instanceof Long) return (int) Math.floor((long) value);
        if (value instanceof Float) return (int) Math.floor((float) value);
        if (value instanceof Double) return (int) Math.floor((double) value);
        return 0;
    }

    public static boolean isBlockInTag(String tagName, Level level, Object... pos) {
        String tag = tagName.replace("#", "");
        TagKey<Block> tagKey = TagKey.create(Registries.BLOCK, new ResourceLocation(tag));
        BlockState state = BuiltInRegistries.BLOCK.get(new ResourceLocation(getBlockId(level, pos))).defaultBlockState();
        return state.is(tagKey);
    }

    @SubscribeEvent
    public static void onTick(TickEvent.LevelTickEvent event) {
        Level level = event.level;
        if (event.phase == TickEvent.Phase.END && !level.isClientSide && level.dimension() == Level.OVERWORLD) {
            blockRemoved.clear();
        }
    }
}
