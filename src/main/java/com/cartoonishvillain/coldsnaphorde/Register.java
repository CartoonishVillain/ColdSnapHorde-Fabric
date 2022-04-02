package com.cartoonishvillain.coldsnaphorde;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.*;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.StandardHorde;
import com.cartoonishvillain.coldsnaphorde.entities.projectiles.*;
import com.cartoonishvillain.coldsnaphorde.items.*;
import com.cartoonishvillain.coldsnaphorde.items.Armor.ArmorMaterials;
import com.cartoonishvillain.coldsnaphorde.items.Armor.TopHat;
import com.cartoonishvillain.coldsnaphorde.items.Projectiles.*;
import com.cartoonishvillain.coldsnaphorde.items.Tier;
import com.cartoonishvillain.coldsnaphorde.items.ToolsOrOther.IceSword;
import com.cartoonishvillain.coldsnaphorde.items.ToolsOrOther.Icicle;
import com.cartoonishvillain.coldsnaphorde.items.ToolsOrOther.Materials;
import com.cartoonishvillain.coldsnaphorde.items.ToolsOrOther.ModMusicDisc;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.List;

import static com.cartoonishvillain.coldsnaphorde.ColdSnapHorde.MOD_ID;

public class Register {

    public static final Item TOPHAT = new TopHat(ArmorMaterials.HAT, EquipmentSlot.HEAD, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item REDTOPHAT = new TopHat(ArmorMaterials.HAT, EquipmentSlot.HEAD, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item BLUETOPHAT = new TopHat(ArmorMaterials.HAT, EquipmentSlot.HEAD, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item GREENTOPHAT = new TopHat(ArmorMaterials.HAT, EquipmentSlot.HEAD, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PURPLETOPHAT = new TopHat(ArmorMaterials.HAT, EquipmentSlot.HEAD, new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final Item ROCKYSNOWBALL = new RockySnowball();
    public static final Item SNOWIERSNOWBALL = new SnowierSnowball();
    public static final Item LIGHTNINGSNOWBALL = new LightningSnowball();

    public static final Item LIGHTNINGTRANSPOSERPIECE = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item LIGHTNINGTRANSPOSER = new Transposer(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item THERMOMETER = new Thermometer(new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final Item ICESHARD = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item SNOWGLOBE = new Snowglobe(new Item.Properties().tab(ColdSnapHorde.TAB), Tier.ONE);
    public static final Item SNOWWALLCHARM = new FrostWallCharm(new Item.Properties().tab(ColdSnapHorde.TAB).stacksTo(1), Tier.ONE);
    public static final Item SMALLPRESENT = new Present(new Item.Properties().tab(ColdSnapHorde.TAB), Tier.ONE);
    public static final Item LESSERHEALINGBALL = new HealingSnowball(Tier.ONE);
    public static final Item ICICLE = new Icicle(Materials.ICICLE, 2, -3.2f, new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final Item FROSTESSENCE = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item FROSTCORE = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PRESENT = new Present(new Item.Properties().tab(ColdSnapHorde.TAB), Tier.TWO);
    public static final Item GLACIERWALLCHARM = new FrostWallCharm(new Item.Properties().tab(ColdSnapHorde.TAB).stacksTo(1), Tier.TWO);
    public static final Item FROSTCHARM = new FrostCharm(new Item.Properties().tab(ColdSnapHorde.TAB).stacksTo(1), Tier.TWO);
    public static final Item HEALINGBALL = new HealingSnowball(Tier.TWO);
    public static final Item ICESWORD = new IceSword(Materials.ICE, 0, -2.4f, new Item.Properties().tab(ColdSnapHorde.TAB).rarity(Rarity.UNCOMMON));
    public static final Item FROSTEDSNOWGLOBE = new Snowglobe(new Item.Properties().tab(ColdSnapHorde.TAB), Tier.TWO);
    public static final Item ICESTAFF = new IceStaff(Materials.FROSTESSENCE, new Item.Properties().tab(ColdSnapHorde.TAB), Tier.TWO);
    public static final Item WANDOFTHEFROSTWALKER = new FrostWalkStaff(Materials.FROSTESSENCE, new Item.Properties().tab(ColdSnapHorde.TAB), Tier.TWO);

    public static final Item ICEESSENCE = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item LARGEPRESENT = new Present(new Item.Properties().tab(ColdSnapHorde.TAB), Tier.THREE);
    public static final Item REINFOCEDGLACIERCHARM = new FrostWallCharm(new Item.Properties().tab(ColdSnapHorde.TAB).stacksTo(1), Tier.THREE);
    public static final Item ARCTICCHARM = new FrostCharm(new Item.Properties().tab(ColdSnapHorde.TAB).stacksTo(1), Tier.THREE);
    public static final Item GREATERHEALINGBALL = new HealingSnowball(Tier.THREE);
    public static final Item FROZENSNOWGLOBE = new Snowglobe(new Item.Properties().tab(ColdSnapHorde.TAB), Tier.THREE);
    public static final Item GLACIERSTAFF = new IceStaff(Materials.FROSTESSENCE, new Item.Properties().tab(ColdSnapHorde.TAB), Tier.THREE);
    public static final Item STAFFOFICEFROSTWALKER = new FrostWalkStaff(Materials.FROSTESSENCE, new Item.Properties().tab(ColdSnapHorde.TAB), Tier.THREE);
    public static final Item ICECORE = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final EntityType<StandardHorde.StandardGifter> COLDSNAPGIFTER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "coldsnapgifter"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, StandardHorde.StandardGifter::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<StandardHorde.StandardGunner> COLDSNAPGUNNER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "coldsnapgunner"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, StandardHorde.StandardGunner::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<StandardHorde.StandardStabber> COLDSNAPSTABBER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "coldsnapstabber"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, StandardHorde.StandardStabber::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<StandardHorde.StandardSnowballer> COLDSNAPSNOWBALLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "coldsnapsnowballer"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, StandardHorde.StandardSnowballer::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<StandardHorde.StandardZapper> COLDSNAPZAPPER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "coldsnapzapper"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, StandardHorde.StandardZapper::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<StandardHorde.StandardBrawler> COLDSNAPBRAWLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "coldsnapbrawler"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, StandardHorde.StandardBrawler::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final EntityType<NetherHorde.NetherGifter> NCOLDSNAPGIFTER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ncoldsnapgifter"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, NetherHorde.NetherGifter::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<NetherHorde.NetherGunner> NCOLDSNAPGUNNER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ncoldsnapgunner"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, NetherHorde.NetherGunner::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<NetherHorde.NetherStabber> NCOLDSNAPSTABBER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ncoldsnapstabber"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, NetherHorde.NetherStabber::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<NetherHorde.NetherSnowballer> NCOLDSNAPSNOWBALLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ncoldsnapsnowballer"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, NetherHorde.NetherSnowballer::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<NetherHorde.NetherZapper> NCOLDSNAPZAPPER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ncoldsnapzapper"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, NetherHorde.NetherZapper::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<NetherHorde.NetherBrawler> NCOLDSNAPBRAWLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ncoldsnapbrawler"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, NetherHorde.NetherBrawler::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final EntityType<EndHorde.EndGifter> ECOLDSNAPGIFTER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ecoldsnapgifter"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, EndHorde.EndGifter::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<EndHorde.EndGunner> ECOLDSNAPGUNNER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ecoldsnapgunner"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, EndHorde.EndGunner::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<EndHorde.EndStabber> ECOLDSNAPSTABBER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ecoldsnapstabber"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, EndHorde.EndStabber::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<EndHorde.EndSnowballer> ECOLDSNAPSNOWBALLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ecoldsnapsnowballer"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, EndHorde.EndSnowballer::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<EndHorde.EndZapper> ECOLDSNAPZAPPER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ecoldsnapzapper"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, EndHorde.EndZapper::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<EndHorde.EndBrawler> ECOLDSNAPBRAWLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "ecoldsnapbrawler"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, EndHorde.EndBrawler::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final EntityType<PlagueHorde.PlagueGifter> PCOLDSNAPGIFTER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "pcoldsnapgifter"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, PlagueHorde.PlagueGifter::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<PlagueHorde.PlagueGunner> PCOLDSNAPGUNNER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "pcoldsnapgunner"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, PlagueHorde.PlagueGunner::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<PlagueHorde.PlagueStabber> PCOLDSNAPSTABBER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "pcoldsnapstabber"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, PlagueHorde.PlagueStabber::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<PlagueHorde.PlagueSnowballer> PCOLDSNAPSNOWBALLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "pcoldsnapsnowballer"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, PlagueHorde.PlagueSnowballer::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<PlagueHorde.PlagueZapper> PCOLDSNAPZAPPER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "pcoldsnapzapper"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, PlagueHorde.PlagueZapper::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<PlagueHorde.PlagueBrawler> PCOLDSNAPBRAWLER = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "pcoldsnapbrawler"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, PlagueHorde.PlagueBrawler::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final Item COLDSNAPGUNNERSPAWN = new SpawnEggItem(COLDSNAPGUNNER, 14804727, 0, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item COLDSNAPSTABBERSPAWN = new SpawnEggItem(COLDSNAPSTABBER, 14804727, 8585216, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item COLDSNAPSNOWBALLERSPAWN = new SpawnEggItem(COLDSNAPSNOWBALLER, 14804727, 25753, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item COLDSNAPGIFTERSPAWN = new SpawnEggItem(COLDSNAPGIFTER, 14804727, 26671, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item COLDSNAPZAPPERSPAWN = new SpawnEggItem(COLDSNAPZAPPER, 14804727, 9802549, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item COLDSNAPBRAWLERSPAWN = new SpawnEggItem(COLDSNAPBRAWLER, 14804727, 6229378, new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final Item NCOLDSNAPGUNNERSPAWN = new SpawnEggItem(NCOLDSNAPGUNNER, 4926000, 0, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item NCOLDSNAPSTABBERSPAWN = new SpawnEggItem(NCOLDSNAPSTABBER, 4926000, 8585216, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item NCOLDSNAPSNOWBALLERSPAWN = new SpawnEggItem(NCOLDSNAPSNOWBALLER, 4926000, 25753, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item NCOLDSNAPGIFTERSPAWN = new SpawnEggItem(NCOLDSNAPGIFTER, 4926000, 26671, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item NCOLDSNAPZAPPERSPAWN = new SpawnEggItem(NCOLDSNAPZAPPER, 4926000, 9802549, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item NCOLDSNAPBRAWLERSPAWN = new SpawnEggItem(NCOLDSNAPBRAWLER, 4926000, 6229378, new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final Item ECOLDSNAPGUNNERSPAWN = new SpawnEggItem(ECOLDSNAPGUNNER, 15332272, 0, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ECOLDSNAPSTABBERSPAWN = new SpawnEggItem(ECOLDSNAPSTABBER, 15332272, 8585216, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ECOLDSNAPSNOWBALLERSPAWN = new SpawnEggItem(ECOLDSNAPSNOWBALLER, 15332272, 25753, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ECOLDSNAPGIFTERSPAWN = new SpawnEggItem(ECOLDSNAPGIFTER, 15332272, 26671, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ECOLDSNAPZAPPERSPAWN = new SpawnEggItem(ECOLDSNAPZAPPER, 15332272, 9802549, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ECOLDSNAPBRAWLERSPAWN = new SpawnEggItem(ECOLDSNAPBRAWLER, 15332272, 6229378, new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final Item PCOLDSNAPGUNNERSPAWN = new SpawnEggItem(PCOLDSNAPGUNNER, 7444, 0, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PCOLDSNAPSTABBERSPAWN = new SpawnEggItem(PCOLDSNAPSTABBER, 7444, 8585216, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PCOLDSNAPSNOWBALLERSPAWN = new SpawnEggItem(PCOLDSNAPSNOWBALLER, 7444, 25753, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PCOLDSNAPGIFTERSPAWN = new SpawnEggItem(PCOLDSNAPGIFTER, 7444, 26671, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PCOLDSNAPZAPPERSPAWN = new SpawnEggItem(PCOLDSNAPZAPPER, 7444, 9802549, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PCOLDSNAPBRAWLERSPAWN = new SpawnEggItem(PCOLDSNAPBRAWLER, 7444, 6229378, new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final EntityType<GunnerProjectileEntity> GUNNERPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "gunnerprojectile"), FabricEntityTypeBuilder.<GunnerProjectileEntity>create(MobCategory.MISC, GunnerProjectileEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<RockSnowballEntity> ROCKSNOWBALLPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "rocksnowballprojectile"), FabricEntityTypeBuilder.<RockSnowballEntity>create(MobCategory.MISC, RockSnowballEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<ThrownChorusEntity> THROWNCHORUSPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "thrownchorusprojectile"), FabricEntityTypeBuilder.<ThrownChorusEntity>create(MobCategory.MISC, ThrownChorusEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<SnowierSnowballEntity> SNOWIERSNOWBALLPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "snowiersnowballprojectile"), FabricEntityTypeBuilder.<SnowierSnowballEntity>create(MobCategory.MISC, SnowierSnowballEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<LightningSnowEntity> LIGHTNINGSNOWBALLPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "lightningsnowballprojectile"), FabricEntityTypeBuilder.<LightningSnowEntity>create(MobCategory.MISC, LightningSnowEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<HealingSnowballEntity> HEALINGSNOWBALLPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "healingsnowprojectile"), FabricEntityTypeBuilder.<HealingSnowballEntity>create(MobCategory.MISC, HealingSnowballEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<IceProjectile> ICEPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "iceprojectile"), FabricEntityTypeBuilder.<IceProjectile>create(MobCategory.MISC, IceProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static final EntityType<ColdSnapCow> COLDSNAPCOW = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "frostycow"), FabricEntityTypeBuilder.<ColdSnapCow>create(MobCategory.CREATURE, ColdSnapCow::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build());
    public static final Item COLDSNAPCOWSPAWN = new SpawnEggItem(COLDSNAPCOW, 14804727, 1840384, new Item.Properties().tab(ColdSnapHorde.TAB));

    public static final Block SLUSH = new SlushBlock(BlockBehaviour.Properties.of(Material.SNOW).strength(0).sound(SoundType.SNOW));
    public static final Block GREENCANDYCANEBLOCK = new Block(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));
    public static final Block REDCANDYCANEBLOCK = new Block(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));

    public static final ResourceLocation gifter_laugh_id = new ResourceLocation(MOD_ID, "gifter_attack");
    public static final ResourceLocation november_snow_id = new ResourceLocation(MOD_ID, "november_snow");
    public static final ResourceLocation arctic_beat_id = new ResourceLocation(MOD_ID, "arctic_beat");
    public static final SoundEvent GIFTERATTACK = new SoundEvent(gifter_laugh_id);
    public static final SoundEvent NOVEMBERSNOW = new SoundEvent(november_snow_id);
    public static final SoundEvent ARCTICBEAT = new SoundEvent(arctic_beat_id);

    public static final Item NOVEMBERSNOWDISC = new ModMusicDisc(6, Register.NOVEMBERSNOW, new Item.Properties().tab(ColdSnapHorde.TAB).stacksTo(1));
    public static final Item ARCTICBEATDISC = new ModMusicDisc(7, Register.ARCTICBEAT, new Item.Properties().tab(ColdSnapHorde.TAB).stacksTo(1));

    public static final FrostStepEffect FROST_EFFECT = new FrostStepEffect();


    public static void init(){
        Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "slush"), SLUSH);
        Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "greencandycane"), GREENCANDYCANEBLOCK);
        Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "redcandycane"), REDCANDYCANEBLOCK);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "slush"), new BlockItem(SLUSH, new Item.Properties().tab(ColdSnapHorde.TAB)));
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "greencandycane"), new LoredBlockItem(GREENCANDYCANEBLOCK, new Item.Properties().tab(ColdSnapHorde.TAB), new TranslatableComponent("itemtooltip.candycane.1").withStyle(ChatFormatting.AQUA), new TranslatableComponent("itemtooltip.candycane.2").withStyle(ChatFormatting.AQUA)));
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "redcandycane"), new LoredBlockItem(REDCANDYCANEBLOCK, new Item.Properties().tab(ColdSnapHorde.TAB), new TranslatableComponent("itemtooltip.candycane.1").withStyle(ChatFormatting.AQUA), new TranslatableComponent("itemtooltip.candycane.2").withStyle(ChatFormatting.AQUA)));
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "rockysnowball"), ROCKYSNOWBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "freezeball"), SNOWIERSNOWBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "iceshard"), ICESHARD);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "icecore"), ICECORE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "transposerpiece"), LIGHTNINGTRANSPOSERPIECE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lightningtransposer"), LIGHTNINGTRANSPOSER);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "thermometer"), THERMOMETER);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lightningsnowball"), LIGHTNINGSNOWBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "snowglobe"), SNOWGLOBE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "present"), PRESENT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "ice_sword"), ICESWORD);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "tophat"), TOPHAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "redtophat"), REDTOPHAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "bluetophat"), BLUETOPHAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "greentophat"), GREENTOPHAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "purpletophat"), PURPLETOPHAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "snow_wall_charm"), SNOWWALLCHARM);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "small_present"), SMALLPRESENT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lesser_healing_ball"), LESSERHEALINGBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "icicle"), ICICLE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "frostessence"), FROSTESSENCE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "frostcore"), FROSTCORE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "glacier_wall_charm"), GLACIERWALLCHARM);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "frost_charm"), FROSTCHARM);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "healing_ball"), HEALINGBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "frosted_snowglobe"), FROSTEDSNOWGLOBE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "ice_staff"), ICESTAFF);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "wand_of_frost_walker"), WANDOFTHEFROSTWALKER);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "iceessence"), ICEESSENCE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "reinforced_glacier_wall_charm"), REINFOCEDGLACIERCHARM);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "arctic_wind_charm"), ARCTICCHARM);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "greater_healing_ball"), GREATERHEALINGBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "large_present"), LARGEPRESENT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "glacier_staff"), GLACIERSTAFF);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "staff_of_ice_walker"), STAFFOFICEFROSTWALKER);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "frozen_snowglobe"), FROZENSNOWGLOBE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "november_snow"), NOVEMBERSNOWDISC);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "arctic_beat"), ARCTICBEATDISC);

        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "frostcow_spawn_egg"), COLDSNAPCOWSPAWN);

        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "gunner_spawn_egg"), COLDSNAPGUNNERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "stabber_spawn_egg"), COLDSNAPSTABBERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "snowballer_spawn_egg"), COLDSNAPSNOWBALLERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "zapper_spawn_egg"), COLDSNAPZAPPERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "gifter_spawn_egg"), COLDSNAPGIFTERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "brawler_spawn_egg"), COLDSNAPBRAWLERSPAWN);

        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "ngunner_spawn_egg"), NCOLDSNAPGUNNERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "nstabber_spawn_egg"), NCOLDSNAPSTABBERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "nsnowballer_spawn_egg"), NCOLDSNAPSNOWBALLERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "nzapper_spawn_egg"), NCOLDSNAPZAPPERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "ngifter_spawn_egg"), NCOLDSNAPGIFTERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "nbrawler_spawn_egg"), NCOLDSNAPBRAWLERSPAWN);

        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "egunner_spawn_egg"), ECOLDSNAPGUNNERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "estabber_spawn_egg"), ECOLDSNAPSTABBERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "esnowballer_spawn_egg"), ECOLDSNAPSNOWBALLERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "ezapper_spawn_egg"), ECOLDSNAPZAPPERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "egifter_spawn_egg"), ECOLDSNAPGIFTERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "ebrawler_spawn_egg"), ECOLDSNAPBRAWLERSPAWN);

        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "pgunner_spawn_egg"), PCOLDSNAPGUNNERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "pstabber_spawn_egg"), PCOLDSNAPSTABBERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "psnowballer_spawn_egg"), PCOLDSNAPSNOWBALLERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "pzapper_spawn_egg"), PCOLDSNAPZAPPERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "pgifter_spawn_egg"), PCOLDSNAPGIFTERSPAWN);
        Registry.register(Registry.ITEM, new ResourceLocation(ColdSnapHorde.MOD_ID, "pbrawler_spawn_egg"), PCOLDSNAPBRAWLERSPAWN);

        Registry.register(Registry.MOB_EFFECT, new ResourceLocation(ColdSnapHorde.MOD_ID, "frost_step_effect"), FROST_EFFECT);

        Registry.register(Registry.SOUND_EVENT, gifter_laugh_id, GIFTERATTACK);
        Registry.register(Registry.SOUND_EVENT, november_snow_id, NOVEMBERSNOW);
        Registry.register(Registry.SOUND_EVENT, arctic_beat_id, ARCTICBEAT);

        FabricDefaultAttributeRegistry.register(COLDSNAPSTABBER, ColdSnapStabber.customAttributes());
        FabricDefaultAttributeRegistry.register(COLDSNAPGUNNER, ColdSnapGunner.customAttributes());
        FabricDefaultAttributeRegistry.register(COLDSNAPSNOWBALLER, ColdSnapSnowballer.customAttributes());
        FabricDefaultAttributeRegistry.register(COLDSNAPGIFTER, ColdSnapGifter.customAttributes());
        FabricDefaultAttributeRegistry.register(COLDSNAPZAPPER, ColdSnapZapper.customAttributes());
        FabricDefaultAttributeRegistry.register(COLDSNAPBRAWLER, ColdSnapBrawler.customAttributes());

        FabricDefaultAttributeRegistry.register(NCOLDSNAPSTABBER, ColdSnapStabber.customAttributes());
        FabricDefaultAttributeRegistry.register(NCOLDSNAPGUNNER, ColdSnapGunner.customAttributes());
        FabricDefaultAttributeRegistry.register(NCOLDSNAPSNOWBALLER, ColdSnapSnowballer.customAttributes());
        FabricDefaultAttributeRegistry.register(NCOLDSNAPGIFTER, ColdSnapGifter.customAttributes());
        FabricDefaultAttributeRegistry.register(NCOLDSNAPZAPPER, ColdSnapZapper.customAttributes());
        FabricDefaultAttributeRegistry.register(NCOLDSNAPBRAWLER, ColdSnapBrawler.customAttributes());

        FabricDefaultAttributeRegistry.register(ECOLDSNAPSTABBER, ColdSnapStabber.customAttributes());
        FabricDefaultAttributeRegistry.register(ECOLDSNAPGUNNER, ColdSnapGunner.customAttributes());
        FabricDefaultAttributeRegistry.register(ECOLDSNAPSNOWBALLER, ColdSnapSnowballer.customAttributes());
        FabricDefaultAttributeRegistry.register(ECOLDSNAPGIFTER, ColdSnapGifter.customAttributes());
        FabricDefaultAttributeRegistry.register(ECOLDSNAPZAPPER, ColdSnapZapper.customAttributes());
        FabricDefaultAttributeRegistry.register(ECOLDSNAPBRAWLER, ColdSnapBrawler.customAttributes());

        FabricDefaultAttributeRegistry.register(PCOLDSNAPSTABBER, ColdSnapStabber.customAttributes());
        FabricDefaultAttributeRegistry.register(PCOLDSNAPGUNNER, ColdSnapGunner.customAttributes());
        FabricDefaultAttributeRegistry.register(PCOLDSNAPSNOWBALLER, ColdSnapSnowballer.customAttributes());
        FabricDefaultAttributeRegistry.register(PCOLDSNAPGIFTER, ColdSnapGifter.customAttributes());
        FabricDefaultAttributeRegistry.register(PCOLDSNAPZAPPER, ColdSnapZapper.customAttributes());
        FabricDefaultAttributeRegistry.register(PCOLDSNAPBRAWLER, ColdSnapBrawler.customAttributes());

        FabricDefaultAttributeRegistry.register(COLDSNAPCOW, ColdSnapCow.customAttributes());

        ColdSnapHorde.TOPHATS = new ArrayList<>(List.of(Register.TOPHAT, Register.REDTOPHAT, Register.BLUETOPHAT, Register.GREENTOPHAT, Register.PURPLETOPHAT));

    }
}
