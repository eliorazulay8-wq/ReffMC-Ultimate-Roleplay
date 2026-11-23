package com.reffmc.ultimate.registry;
import com.reffmc.ultimate.ReffMCUltimate;
import com.reffmc.ultimate.entity.StoryNpcEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ReffMCUltimate.MODID);
    public static final RegistryObject<EntityType<StoryNpcEntity>> BOZO = register("bozo");
    public static final RegistryObject<EntityType<StoryNpcEntity>> SUS = register("sus");
    public static final RegistryObject<EntityType<StoryNpcEntity>> OFFICER = register("officer");
    public static final RegistryObject<EntityType<StoryNpcEntity>> NORMAL = register("normal");
    private static RegistryObject<EntityType<StoryNpcEntity>> register(String name) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(StoryNpcEntity::new, MobCategory.CREATURE)
                .sized(0.6F, 1.95F).clientTrackingRange(8).build(name));
    }
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        AttributeSupplier attributes = StoryNpcEntity.createAttributes().build();
        event.put(BOZO.get(), attributes); event.put(SUS.get(), attributes);
        event.put(OFFICER.get(), attributes); event.put(NORMAL.get(), attributes);
    }
    private ModEntities() { }
}
