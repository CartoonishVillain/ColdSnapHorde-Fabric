package com.cartoonishvillain.coldsnaphorde.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "coldsnaphorde")
public class ColdSnapConfig implements ConfigData {


    @ConfigEntry.Gui.CollapsibleObject
    public ColdSnapSpawning spawnconfig = new ColdSnapSpawning();

    @ConfigEntry.Gui.CollapsibleObject
    public ColdSnapSettings coldSnapSettings = new ColdSnapSettings();

    @ConfigEntry.Gui.CollapsibleObject
    public ColdSnapClientSettings coldSnapClientSettings = new ColdSnapClientSettings();

    public static class ColdSnapSpawning {
        @Comment("Changes temperature spawn requirements for standard horde members. 0: Cold, 1: Neutral, 2: Warm, 3: Hot.")
        public int SPAWNTEMPS = 0;
        @Comment("Defines how hot a standard snowmember can get before it melts. 0: Cold, 1: Neutral, 2: Warm, 3: Hot (Recommend matching or being higher than spawnTempRange")
        public int HEATPROT = 1;
        @Comment("The hottest biome temperature a snow trail can spawn in, 0: Cold, 1: Neutral, 2: Warm, 3: Hot")
        public int SNOWTRAIL = 1;
        @Comment("If true, cold snap horde mobs will be restricted to spawning only when unlocked by horde completion. If false, entities will spawn as if the world was through tier 3 already.")
        public boolean PROGRESSIVESPAWNS = true;
        @Comment("EXPERIMENTAL! MUST BE ALL CHARACTERS FROM [a-z0-9/._-] OR THE GAME WILL CRASH. List the biome names seperated by commas that you want to absolutely exclude the horde from. (eg: minecraft:desert,minecraft:jungle)")
        public String BiomeExclusion = "notabiome";
        @Comment("Changes the spawn weight of the Stabber in the Overworld")
        public int STABBER = 20;
        @Comment("Changes the spawn weight of the Gunner in the Overworld")
        public int GUNNER = 20;
        @Comment("Changes the spawn weight of the Snowballer in the Overworld")
        public int SNOWBALLER = 20;
        @Comment("Changes the spawn weight of the Gifter in the Overworld")
        public int GIFTER = 10;
        @Comment("Changes the spawn weight of the Zapper in the Overworld")
        public int ZAPPER = 6;
        @Comment("Changes the spawn weight of the Brawler in the Overworld")
        public int BRAWLER = 8;
        @Comment("Changes the spawn weight of the Stabber other dimensions")
        public int DSTABBER = 2;
        @Comment("Changes the spawn weight of the Gunner other dimensions")
        public int DGUNNER = 2;
        @Comment("Changes the spawn weight of the Snowballer other dimensions")
        public int DSNOWBALLER = 2;
        @Comment("Changes the spawn weight of the Gifter other dimensions")
        public int DGIFTER = 1;
        @Comment("Changes the spawn weight of the Zapper other dimensions")
        public int DZAPPER = 1;
        @Comment("Changes the spawn weight of the Brawler other dimensions")
        public int DBRAWLER = 1;
        @Comment("Changes the spawn weight of the Frosty Cow.")
        public int SNOWCOW = 4;
    }

    public static class ColdSnapSettings {
        @Comment("Enables or disabled technical levels of thermometers. Gives minecraft temperature values instead of adapted ones.")
        public boolean TECHNICALTHERMOMETER = false;
        @Comment("The percent chance that the snowman zapper will stick a transponder on his target if one is available.")
        public int STICKTRANSPONDER = 20;
        @Comment("Plague variants of the Cold Snap Horde can infect attack entities with Immortuos Calyx if it is installed alongside this mod.")
        public boolean PLAGUEIMMORTUOSCOMPAT = true;
        @Comment("Horde snowmen take double damage from fire.")
        public boolean HORDETAKESMOREFIRE = true;
        @Comment("How long in ticks (20 per second by default) it takes for a frosty cow to be ready to be \"milked\" for powdered snow again.")
        public int FROSTYHARVESTCOOLDOWN = 1800;
        @Comment("How long in seconds players need to wait inbetween Snowglobe usage (Spawning a notable number of snowmen)")
        public int GLOBALHORDECOOLDOWN = 60;
        @Comment("How many horde members will a tier 1 horde attempt to spawn at once. Once this limit is reached, the horde will not spawn more members until the amount of members alive or in range fall below this number")
        public int TIER1HORDESIZE = 5;
        @Comment("Default amount of horde members needed to be killed in a tier 1 horde on easy difficulty before the horde deactivates with a player victory")
        public int TIER1ALIVEEASY = 20;
        @Comment("Default amount of horde members needed to be killed in a tier 1 horde on normal difficulty before the horde deactivates with a player victory")
        public int TIER1ALIVENORMAL = 30;
        @Comment("Default amount of horde members needed to be killed in a tier 1 horde on hard difficulty before the horde deactivates with a player victory")
        public int TIER1ALIVEHARD = 40;
        @Comment("How many horde members will a tier 2 horde attempt to spawn at once. Once this limit is reached, the horde will not spawn more members until the amount of members alive or in range fall below this number")
        public int TIER2HORDESIZE = 8;
        @Comment("Default amount of horde members needed to be killed in a tier 2 horde on easy difficulty before the horde deactivates with a player victory")
        public int TIER2ALIVEEASY = 35;
        @Comment("Default amount of horde members needed to be killed in a tier 2 horde on normal difficulty before the horde deactivates with a player victory")
        public int TIER2ALIVENORMAL = 50;
        @Comment("Default amount of horde members needed to be killed in a tier 2 horde on hard difficulty before the horde deactivates with a player victory")
        public int TIER2ALIVEHARD = 65;
        @Comment("How many horde members will a tier 3 horde attempt to spawn at once. Once this limit is reached, the horde will not spawn more members until the amount of members alive or in range fall below this number")
        public int TIER3HORDESIZE = 10;
        @Comment("Default amount of horde members needed to be killed in a tier 3 horde on easy difficulty before the horde deactivates with a player victory")
        public int TIER3ALIVEEASY = 50;
        @Comment("Default amount of horde members needed to be killed in a tier 3 horde on normal difficulty before the horde deactivates with a player victory")
        public int TIER3ALIVENORMAL = 65;
        @Comment("Default amount of horde members needed to be killed in a tier 3 horde on hard difficulty before the horde deactivates with a player victory")
        public int TIER3ALIVEHARD = 80;
        @Comment("How often more expensive update ticks occur. This allows new in range players to get the bossbar, and updates the horde with the position of their main target. Higher reduces frequency of these more expensive ticks, but lowers horde responsiveness.")
        public int UPDATETICK = 100;
    }

    public static class ColdSnapClientSettings {
        @Comment("Nostalgia mode. If true, snowmen are replaced with their original counterparts where applicable.")
        public boolean OLD_SNOW = false;
    }
}
