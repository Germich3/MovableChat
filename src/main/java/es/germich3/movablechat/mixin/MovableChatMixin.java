package es.germich3.movablechat.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static es.germich3.movablechat.config.MovableChatConfig.getConfig;

@Mixin(ChatHud.class)
public class MovableChatMixin {

	@Shadow
	@Final
	private MinecraftClient client;

	private int getOffset() {
		if (getConfig().automatic.isAbsorptionAutoMoveEnabled) {
			int offset = 0;
			if (client.player == null || client.player.isCreative() || client.player.isSpectator()) {
				return offset;
			}
			if (client.player.getArmor() > 0) {
				offset += 10;
			}
			if (client.player.getAbsorptionAmount() > 0) {
				offset += 10;
			}
			return offset;
		}
		else {
			return (getConfig().manual.verticalityChat * getConfig().manual.multiplierChat) + getConfig().manual.plusChat;
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