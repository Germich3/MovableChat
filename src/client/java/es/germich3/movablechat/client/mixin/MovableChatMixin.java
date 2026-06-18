package es.germich3.movablechat.client.mixin;

import es.germich3.movablechat.config.MovableChatConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatComponent.class)
public abstract class MovableChatMixin {

	@Shadow
	@Final
	private Minecraft minecraft;

	@Unique
	private int getOffset() {
		if (MovableChatConfig.getConfig().isAbsorptionAutoMoveEnabled()) {
			int offset = 0;
			if (minecraft.player == null || minecraft.player.isCreative() || minecraft.player.isSpectator()) {
				return offset;
			}
			if (minecraft.player.getArmorValue() > 0) {
				offset += 10;
			}
			if (minecraft.player.getAbsorptionAmount() > 0) {
				offset += 10;
			}
			return offset;
		}
		else {
			int verticalityChat = MovableChatConfig.getConfig().getVerticalityChat();
			int multiplierChat = MovableChatConfig.getConfig().getMultiplierChat();
			int plusChat = MovableChatConfig.getConfig().getPlusChat();
			return (verticalityChat * multiplierChat) + plusChat;
		}
	}

	@Inject(
		method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;updatePose(Ljava/util/function/Consumer;)V",
			shift = At.Shift.AFTER
		)
	)
	private void injectOnExtractRenderStateAfterUpdatePose(ChatComponent.ChatGraphicsAccess graphics, int i, int j, ChatComponent.DisplayMode displayMode, CallbackInfo ci) {
		graphics.updatePose((pose) -> {
			pose.translate(0.0F, -getOffset());
		});
	}

}
