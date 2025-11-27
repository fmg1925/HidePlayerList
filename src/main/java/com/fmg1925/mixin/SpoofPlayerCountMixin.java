package com.fmg1925.mixin;

import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.rcon.QueryResponseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(QueryResponseHandler.class)
public class SpoofPlayerCountMixin {
    @Unique
    private static final String[] PLAYERS = new String[] { "" };
    @Redirect(
            method = "handle",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/dedicated/DedicatedServer;getCurrentPlayerCount()I"
            )
    )
    private int spoofPlayerCount(DedicatedServer server) {
        return 0;
    }

    @Redirect(
            method = "createRulesReply",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/dedicated/DedicatedServer;getCurrentPlayerCount()I"
            )
    )
    private int getCurrentPlayerCount(DedicatedServer server) {
        return 0;
    }

    // get player names
    @Redirect(
            method = "createRulesReply",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/dedicated/DedicatedServer;getPlayerNames()[Ljava/lang/String;"
            )
    )
    private String[] getPlayerNames(DedicatedServer server) {
        return PLAYERS;
    }
}
