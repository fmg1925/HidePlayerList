package com.fmg1925.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.MessageCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageCommand.class)
public class NoMsgMixin {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void stopRegistration(CommandDispatcher<ServerCommandSource> dispatcher, CallbackInfo ci) {
        ci.cancel();
    }
}
