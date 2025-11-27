package com.fmg1925.mixin;

import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageTracker.class)
public class DeathMessagesMixin {
    @Inject(method = "getDeathMessage", at = @At("RETURN"), cancellable = true)
    private void hideDeathMessage(CallbackInfoReturnable<Text> cir) {
        cir.setReturnValue(Text.empty());
    }
}
