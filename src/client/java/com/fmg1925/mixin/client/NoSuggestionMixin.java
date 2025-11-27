package com.fmg1925.mixin.client;

import net.minecraft.client.network.ClientCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Mixin(ClientCommandSource.class)
public class NoSuggestionMixin {
    @Unique
    private final static List<String> MIERDA = Collections.emptyList();

    @Inject(method = "getPlayerNames", at = @At("HEAD"), cancellable = true)
    private void interceptSuggestion(CallbackInfoReturnable<Collection<String>> cir) {
        cir.setReturnValue(MIERDA);
    }
}
