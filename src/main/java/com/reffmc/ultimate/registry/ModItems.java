package com.reffmc.ultimate.registry;
import com.reffmc.ultimate.ReffMCUltimate;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public final class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReffMCUltimate.MODID);
    public static final RegistryObject<Item> BOZO_EGG = ITEMS.register("bozo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.BOZO, 0xA5B85A, 0x51452D, new Item.Properties()));
    public static final RegistryObject<Item> SUS_EGG = ITEMS.register("sus_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SUS, 0x343434, 0x8B2020, new Item.Properties()));
    public static final RegistryObject<Item> OFFICER_EGG = ITEMS.register("officer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.OFFICER, 0x243E75, 0xD6D6D6, new Item.Properties()));
    public static final RegistryObject<Item> NORMAL_EGG = ITEMS.register("normal_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.NORMAL, 0xB58B62, 0xD5B78B, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_DESKTOP = ITEMS.register("silver_desktop", () -> new BlockItem(ModBlocks.SILVER_DESKTOP.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILVER_GAMING_PC = ITEMS.register("silver_gaming_pc", () -> new BlockItem(ModBlocks.SILVER_GAMING_PC.get(), new Item.Properties()));
    private ModItems() { }
}
