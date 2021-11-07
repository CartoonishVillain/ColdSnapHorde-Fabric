package com.cartoonishvillain.coldsnaphorde;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.StandardHorde;
import com.cartoonishvillain.coldsnaphorde.entities.projectiles.*;
import com.cartoonishvillain.coldsnaphorde.items.Armor.ArmorMaterials;
import com.cartoonishvillain.coldsnaphorde.items.Armor.TopHat;
import com.cartoonishvillain.coldsnaphorde.items.Present;
import com.cartoonishvillain.coldsnaphorde.items.Projectiles.LightningSnowball;
import com.cartoonishvillain.coldsnaphorde.items.Projectiles.RockySnowball;
import com.cartoonishvillain.coldsnaphorde.items.Projectiles.SnowierSnowball;
import com.cartoonishvillain.coldsnaphorde.items.Snowglobe;
import com.cartoonishvillain.coldsnaphorde.items.ToolsOrOther.IceSword;
import com.cartoonishvillain.coldsnaphorde.items.ToolsOrOther.Materials;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static com.cartoonishvillain.coldsnaphorde.ColdSnapHorde.MOD_ID;

public class Register {

    public static final Item TOPHAT = new TopHat(ArmorMaterials.HAT, EquipmentSlot.HEAD, new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ROCKYSNOWBALL = new RockySnowball();
    public static final Item SNOWIERSNOWBALL = new SnowierSnowball();
    public static final Item LIGHTNINGSNOWBALL = new LightningSnowball();
    public static final Item ICESHARD = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ICECORE = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item LIGHTNINGTRANSPOSERPIECE = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item LIGHTNINGTRANSPOSER = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item THERMOMETER = new Item(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item SNOWGLOBE = new Snowglobe(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item PRESENT = new Present(new Item.Properties().tab(ColdSnapHorde.TAB));
    public static final Item ICESWORD = new IceSword(Materials.ICE, 0, -2.4f, new Item.Properties().tab(ColdSnapHorde.TAB).rarity(Rarity.UNCOMMON));


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

    public static final EntityType<GunnerProjectileEntity> GUNNERPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "gunnerprojectile"), FabricEntityTypeBuilder.<GunnerProjectileEntity>create(MobCategory.MISC, GunnerProjectileEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<RockSnowballEntity> ROCKSNOWBALLPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "rocksnowballprojectile"), FabricEntityTypeBuilder.<RockSnowballEntity>create(MobCategory.MISC, RockSnowballEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<ThrownChorusEntity> THROWNCHORUSPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "thrownchorusprojectile"), FabricEntityTypeBuilder.<ThrownChorusEntity>create(MobCategory.MISC, ThrownChorusEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<SnowierSnowballEntity> SNOWIERSNOWBALLPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "snowiersnowballprojectile"), FabricEntityTypeBuilder.<SnowierSnowballEntity>create(MobCategory.MISC, SnowierSnowballEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<LightningSnowEntity> LIGHTNINGSNOWBALLPROJECTILE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, "lightningsnowballprojectile"), FabricEntityTypeBuilder.<LightningSnowEntity>create(MobCategory.MISC, LightningSnowEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());


    public static final ResourceLocation gifter_laugh_id = new ResourceLocation(MOD_ID, "gifter_attack");
    public static final SoundEvent GIFTERATTACK = new SoundEvent(gifter_laugh_id);

    public static void init(){
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "rockysnowball"), ROCKYSNOWBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "snowiersnowball"), SNOWIERSNOWBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "iceshard"), ICESHARD);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "icecore"), ICECORE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "transposerpiece"), LIGHTNINGTRANSPOSERPIECE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lightningtransposer"), LIGHTNINGTRANSPOSER);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "thermometer"), THERMOMETER);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lightningsnowball"), LIGHTNINGSNOWBALL);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "snowglobe"), SNOWGLOBE);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "present"), PRESENT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "ice_sword"), ICESWORD);

        Registry.register(Registry.SOUND_EVENT, gifter_laugh_id, GIFTERATTACK);
    }
}
