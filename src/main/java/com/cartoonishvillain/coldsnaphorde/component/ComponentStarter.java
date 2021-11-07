package com.cartoonishvillain.coldsnaphorde.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.resources.ResourceLocation;

public class ComponentStarter implements WorldComponentInitializer {
    public static final ComponentKey<WorldCooldownComponent> WORLDCOMPONENT =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("coldsnaphorde:cooldown"), WorldCooldownComponent.class);
    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(WORLDCOMPONENT, world -> new WorldCooldownComponent());
    }
}
