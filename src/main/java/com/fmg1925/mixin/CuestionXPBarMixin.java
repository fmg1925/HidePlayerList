package com.fmg1925.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.waypoint.ServerWaypoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWaypoint.class)
public interface CuestionXPBarMixin {
        @Inject(method = "cannotReceive(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", at= @At("HEAD"), cancellable = true)
        private static void cannotReceive(LivingEntity source, ServerPlayerEntity receiver, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(true);
        }
    }
