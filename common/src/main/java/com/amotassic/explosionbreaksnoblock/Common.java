package com.amotassic.explosionbreaksnoblock;

import com.amotassic.explosionbreaksnoblock.config.Configuration;
import com.amotassic.explosionbreaksnoblock.platform.IPlatformHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Common {

    public static final String MOD_ID = "explosionbreaksnoblock";
    public static final String MOD_NAME = "Explosion Breaks No Block";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static Configuration config;
    private static List<String> EBNBList, ENIDList, EBNBWhiteList, ENIDWhiteList;
    public static final IPlatformHelper PLATFORM = loadService();

    public static void init() {
        LOGGER.info("Ciallo～(∠·ω< )⌒★");
        loadConfig();
    }

    private static IPlatformHelper loadService() {
        boolean isFabric;
        try {
            Class.forName("net.neoforged.neoforge.common.NeoForge");
            isFabric = false;
        } catch (ClassNotFoundException e) {
            isFabric = true;
        }
        var loaded = ServiceLoader.load(IPlatformHelper.class);
        for (var service : loaded) {
            if (isFabric) {
                if (service.getPlatformName().equals("Fabric")) return service;
            } else if (service.getPlatformName().equals("NeoForge")) return service;
        }
        throw new NullPointerException("Failed to load service for " + IPlatformHelper.class.getName());
    }

    public static void loadConfig() {
        config = new Configuration(EBNBConfig.class, MOD_ID);
        EBNBList = toList(EBNBConfig.ExplosionBreaksNoBlockList);
        ENIDList = toList(EBNBConfig.ExplosionNoItemDamageList);
        EBNBWhiteList = toList(EBNBConfig.ExplosionBreaksNoBlockWhiteList);
        ENIDWhiteList = toList(EBNBConfig.ExplosionNoItemDamageWhiteList);
    }

    private static List<String> toList(String string) {
        if (string == null || string.isEmpty()) return List.of();
        // 使用正则表达式 [,;] 分割字符串，然后使用 Stream 处理每个元素
        return Arrays.stream(string.split("[,;]"))
                .map(String::trim) // 去除每个元素的前后空白字符
                .filter(s -> !s.isEmpty()) // 过滤掉空字符串
                .filter(s -> !Objects.equals(s, "minecraft:air")) // 不允许填入空气，防止出意外
                .collect(Collectors.toList()); // 收集到 List 中
    }

    public static boolean cancel(Entity entity, Level level, Object... pos) {
        if (isInList(EBNBWhiteList, entity, level, pos)) return false;
        if (EBNBConfig.EBNB_ALL) return true;
        return isInList(EBNBList, entity, level, pos);
    }

    public static boolean cancelItemDamageByExplosion(DamageSource source, Level level) {
        if (isInList(ENIDWhiteList, source.getDirectEntity(), level, source.sourcePositionRaw())) return false;
        if (EBNBConfig.ENID_ALL) return true;
        return isInList(ENIDList, source.getDirectEntity(), level, source.sourcePositionRaw());
    }

    private static String getEntityId(Entity entity) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
    }

    private static boolean isEntityInTag(String tagName, Entity entity) {
        String tag = tagName.replace("#", "");
        return entity.is(TagKey.create(Registries.ENTITY_TYPE, Identifier.parse(tag)));
    }

    private static boolean isInList(List<String> list, Entity entity, Level level, Object... pos) {
        if (entity != null) {
            if (list.contains(getEntityId(entity))) return true;
            for (String tag : list) {
                if (!tag.startsWith("#")) continue;
                if (isEntityInTag(tag, entity)) return true;
            }
        } else {
            if (list.contains(BlockRemoveCache.getBlockId(level, pos))) return true;
            for (String tag : list) {
                if (!tag.startsWith("#")) continue;
                if (BlockRemoveCache.isBlockInTag(tag, level, pos)) return true;
            }
        }
        return false;
    }
}
