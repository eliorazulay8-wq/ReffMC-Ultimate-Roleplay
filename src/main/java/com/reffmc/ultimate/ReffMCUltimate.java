package com.reffmc.ultimate;
import com.reffmc.ultimate.command.ReffMCCommands;
import com.reffmc.ultimate.registry.ModBlocks;
import com.reffmc.ultimate.registry.ModEntities;
import com.reffmc.ultimate.registry.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
@Mod(ReffMCUltimate.MODID)
public class ReffMCUltimate {
    public static final String MODID = "reffmcultimate";
    public ReffMCUltimate() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modBus);
        ModEntities.ENTITY_TYPES.register(modBus);
        ModItems.ITEMS.register(modBus);
        modBus.addListener(ModEntities::registerAttributes);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        ReffMCCommands.register(event.getDispatcher());
    }
}
