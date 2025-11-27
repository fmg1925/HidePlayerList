package com.fmg1925.mixin.client;

import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen.class)
public abstract class NoSocialInteractionsMixin {
    @Shadow
    public abstract void close();

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
	private void noTabList(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
		ci.cancel();
        close();
	}

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void noBackground(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
        ci.cancel();
        close();
    }
}