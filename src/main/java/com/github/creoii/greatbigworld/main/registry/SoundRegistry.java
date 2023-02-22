package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry implements Register {
    public static SoundEvent ENTITY_MOOSE_HURT;
    public static SoundEvent ENTITY_MOOSE_DEATH;
    public static SoundEvent ENTITY_MOOSE_WARNING;
    public static SoundEvent MUSIC_DISC_SUNRISE;
    public static SoundEvent MUSIC_DISC_PINA_COLADA;

    @Override
    public void register() {
        ENTITY_MOOSE_HURT = registerSoundEvent(new Identifier(GreatBigWorld.NAMESPACE, "entity.moose.hurt"));
        ENTITY_MOOSE_DEATH = registerSoundEvent(new Identifier(GreatBigWorld.NAMESPACE, "entity.moose.death"));
        ENTITY_MOOSE_WARNING = registerSoundEvent(new Identifier(GreatBigWorld.NAMESPACE, "entity.moose.warning"));
        MUSIC_DISC_SUNRISE = registerSoundEvent(new Identifier(GreatBigWorld.NAMESPACE, "music_disc.sunrise"));
        MUSIC_DISC_PINA_COLADA = registerSoundEvent(new Identifier(GreatBigWorld.NAMESPACE, "music_disc.pina_colada"));
    }

    private static SoundEvent registerSoundEvent(Identifier id) {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
