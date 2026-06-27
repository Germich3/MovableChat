package es.germich3.movablechat.client.mixin;

import es.germich3.movablechat.client.mixin.accessors.OptionsAccessor;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.options.ChatOptionsScreen;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatOptionsScreen.class)
public abstract class ChatOptionsScreenMixin {

    @Inject(
        method = "options(Lnet/minecraft/client/Options;)[Lnet/minecraft/client/OptionInstance;",
        at = @At(
            value = "RETURN"
        ),
        cancellable = true
    )
    private static void injectOptions(Options originalOptions, CallbackInfoReturnable<OptionInstance<?>[]> originalReturned) {
        OptionInstance<?>[] returned = originalReturned.getReturnValue();
        OptionInstance<?>[] myOptions = new OptionInstance<?>[]{
            ((OptionsAccessor) originalOptions).getChatPositionY(),
            ((OptionsAccessor) originalOptions).isCalcByArmorAbsorption()
        };
        OptionInstance<?>[] modified = ArrayUtils.addAll(returned, myOptions);
        originalReturned.setReturnValue(modified);
    }

}
