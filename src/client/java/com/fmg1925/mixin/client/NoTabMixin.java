package com.fmg1925.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.hud.PlayerListHud.class)
public class NoTabMixin {
	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
	private void noTabList(DrawContext ctx, int w, Scoreboard sb, @Nullable ScoreboardObjective obj, CallbackInfo ci) {
		ci.cancel();
	}
}