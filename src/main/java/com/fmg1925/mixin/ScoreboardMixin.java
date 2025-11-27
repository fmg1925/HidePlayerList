package com.fmg1925.mixin;

import net.minecraft.network.packet.Packet;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerScoreboard.class)
public class ScoreboardMixin {
    @Redirect(
            method = "startSyncing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"
            )
    )
    private void mePicaElScoreboard(ServerPlayNetworkHandler instance, Packet packet) {}

    // score update
    @Redirect(
            method = "updateScore",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/PlayerManager;sendToAll(Lnet/minecraft/network/packet/Packet;)V"
            )
    )
    private void mePicaElNis(PlayerManager instance, Packet<?> packet) {}
}
