package es.germich3.movablechat.client.mixin.accessors;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Options.class)
public interface OptionsAccessor {

    @Accessor("chatPositionY")
    OptionInstance<Integer> getChatPositionY();

    @Accessor("isCalcByArmorAbsorption")
    OptionInstance<Boolean> isCalcByArmorAbsorption();

}
