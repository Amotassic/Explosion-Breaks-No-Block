package com.amotassic.explosionbreaksnoblock;

import com.amotassic.explosionbreaksnoblock.config.Config;

public class EBNBConfig {
    private static final String ID = "explosionbreaksnoblock";
    private static final String GENERAL = "general";

    @Config(config = ID, category = GENERAL, comment = "阻止所有爆炸破坏方块\n Prevent all explosions from destroying blocks")
    public static boolean EBNB_ALL = false;

    @Config(config = ID, category = GENERAL, comment = "阻止破坏方块的爆炸源列表\n List of explosion sources that no block destruction\n e.g. minecraft:bed; minecraft:respawn_anchor; minecraft:creeper")
    public static String ExplosionBreaksNoBlockList = "";

    @Config(config = ID, category = GENERAL, comment = "阻止所有爆炸破坏掉落物\n Prevent all explosions from destroying item entities")
    public static boolean ENID_ALL = false;

    @Config(config = ID, category = GENERAL, comment = "阻止破坏掉落物的爆炸源列表（由于一些原因，床和重生锚在此合并为respawn_blocks）\n List of explosion sources that no destroying item entities (Note: Bed and Respawn Anchor are merged as respawn_blocks due to technical reasons)\n e.g. respawn_blocks; minecraft:creeper; tacz:bullet")
    public static String ExplosionNoItemDamageList = "";

}
