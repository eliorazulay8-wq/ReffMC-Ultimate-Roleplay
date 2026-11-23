package com.reffmc.ultimate.entity;
import com.reffmc.ultimate.registry.ModEntities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
public class StoryNpcEntity extends PathfinderMob {
    private int dialogueIndex = 0;
    public StoryNpcEntity(EntityType<? extends StoryNpcEntity> type, Level level) { super(type, level); }
    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes().add(Attributes.MAX_HEALTH, 24.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FOLLOW_RANGE, 24.0D).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
    @Override protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        if (getType() == ModEntities.OFFICER.get()) {
            goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.05D, true));
            targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true));
        }
    }
    @Override protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        if (!level().isClientSide) {
            String[] lines = dialogueForThisNpc();
            String line = lines[Math.floorMod(dialogueIndex++, lines.length)];
            String speaker = getCustomName() != null ? getCustomName().getString() : defaultName();
            player.sendSystemMessage(Component.literal("§e" + speaker + "§f: " + line));
            lookAt(player, 30.0F, 30.0F);
        }
        return InteractionResult.sidedSuccess(level().isClientSide);
    }
    private String defaultName() {
        if (getType() == ModEntities.BOZO.get()) return "Bozo";
        if (getType() == ModEntities.SUS.get()) return "Sus Villager";
        if (getType() == ModEntities.OFFICER.get()) return "Officer Max";
        return "Roleplay Villager";
    }
    private String[] dialogueForThisNpc() {
        if (getType() == ModEntities.BOZO.get()) return new String[] {"ERR RRR AARRARARA!", "I AM THE MANAGER!", "I WORK HERE TOMORROW.", "WHERE DID MY BRAIN GO?", "I ATE THE RECEIPT."};
        if (getType() == ModEntities.SUS.get()) return new String[] {"Why are you asking me that?", "I definitely was not in the basement.", "You saw nothing.", "This is a completely normal day."};
        if (getType() == ModEntities.OFFICER.get()) return new String[] {"Officer Max. What's the problem?", "Nobody move. I am investigating.", "Bozo, step away from the counter.", "I need to write another report..."};
        return new String[] {"Hello! Nice weather today.", "Have you visited the town shop?", "I heard something strange happened nearby.", "Welcome to our village!"};
    }
}
