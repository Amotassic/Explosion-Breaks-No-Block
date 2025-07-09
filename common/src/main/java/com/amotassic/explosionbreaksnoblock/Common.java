package com.amotassic.explosionbreaksnoblock;

import com.amotassic.explosionbreaksnoblock.config.Configuration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Common {

    public static final String MOD_ID = "explosionbreaksnoblock";
    public static final String MOD_NAME = "Explosion Breaks No Block";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static Configuration config;
    public static List<String> EBNBList, ENIDList;

    public static void init() {
        LOGGER.info("Ciallo～(∠·ω< )⌒★");
        loadConfig();
    }

    public static void loadConfig() {
        config = new Configuration(EBNBConfig.class, MOD_ID);
        EBNBList = toList(EBNBConfig.ExplosionBreaksNoBlockList);
        ENIDList = toList(EBNBConfig.ExplosionNoItemDamageList);
    }

    private static List<String> toList(String string) {
        if (string == null || string.isEmpty()) return List.of();
        // 使用正则表达式 [,;] 分割字符串，然后使用 Stream 处理每个元素
        return Arrays.stream(string.split("[,;]"))
                .map(String::trim) // 去除每个元素的前后空白字符
                .filter(s -> !s.isEmpty()) // 过滤掉空字符串
                .collect(Collectors.toList()); // 收集到 List 中
    }

    public static boolean cancel(Entity entity) {
        if (EBNBConfig.EBNB_ALL) return true;

        if (entity == null) return false;
        return EBNBList.contains(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString());
    }

    public static boolean cancelItemDamageByExplosion(DamageSource source) {
        if (EBNBConfig.ENID_ALL) return true;
        if (ENIDList.contains("respawn_blocks") && Objects.equals(source.getMsgId(), "badRespawnPoint")) return true;
        Entity entity = source.getDirectEntity();

        if (entity == null) return false;
        return ENIDList.contains(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString());
    }
}
