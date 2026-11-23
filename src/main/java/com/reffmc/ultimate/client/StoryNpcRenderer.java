package com.reffmc.ultimate.client;
import com.reffmc.ultimate.ReffMCUltimate;
import com.reffmc.ultimate.entity.StoryNpcEntity;
import com.reffmc.ultimate.registry.ModEntities;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
public class StoryNpcRenderer extends MobRenderer<StoryNpcEntity, VillagerModel<StoryNpcEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/villager/villager.png");
    public StoryNpcRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel<>(context.bakeLayer(ModelLayers.VILLAGER)), 0.5F);
    }
    @Override public ResourceLocation getTextureLocation(StoryNpcEntity entity) { return TEXTURE; }
    @Mod.EventBusSubscriber(modid = ReffMCUltimate.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class Registration {
        @SubscribeEvent public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.BOZO.get(), StoryNpcRenderer::new);
            event.registerEntityRenderer(ModEntities.SUS.get(), StoryNpcRenderer::new);
            event.registerEntityRenderer(ModEntities.OFFICER.get(), StoryNpcRenderer::new);
            event.registerEntityRenderer(ModEntities.NORMAL.get(), StoryNpcRenderer::new);
        }
    }
}
