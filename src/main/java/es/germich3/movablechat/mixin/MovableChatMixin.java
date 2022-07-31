package es.germich3.movablechat.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static es.germich3.movablechat.MovableChat.CONFIG;

@Mixin(ChatHud.class)
public class MovableChatMixin {

	@Shadow
	@Final
	private MinecraftClient client;

	private int getOffset() {
		if (CONFIG.isAbsorptionAutoMoveEnabled()) {
			ClientPlayerEntity player = this.client.player;
			int offset = 0;
			if (player == null || player.isCreative() || player.isSpectator()) {
				return offset;
			}
			if (player.getArmor() > 0) {
				offset += 10;
			}
			if (player.getAbsorptionAmount() > 0) {
				offset += 10;
			}
			return offset;
		}
		else {
			return (CONFIG.getVerticalityChat() * CONFIG.getMultiplierChat()) + CONFIG.getPlusChat();
		}
	}

	@ModifyArg(method = "render", index = 1, at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V",
			ordinal = 0
	))
	private double offsetY(double y) {
		return y - getOffset();
	}

}