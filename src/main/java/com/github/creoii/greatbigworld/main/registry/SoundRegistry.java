package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry implements Register {
    public static final SoundEvent ENTITY_MOOSE_HURT = SoundEvent.of(new Identifier(GreatBigWorld.NAMESPACE, "entity.moose.hurt"));
    public static final SoundEvent ENTITY_MOOSE_DEATH = SoundEvent.of(new Identifier(GreatBigWorld.NAMESPACE, "entity.moose.death"));
    public static final SoundEvent ENTITY_MOOSE_WARNING = SoundEvent.of(new Identifier(GreatBigWorld.NAMESPACE, "entity.moose.warning"));
    public static final SoundEvent MUSIC_DISC_SUNRISE = SoundEvent.of(new Identifier(GreatBigWorld.NAMESPACE, "music_disc.sunrise"));
    public static final SoundEvent MUSIC_DISC_PINA_COLADA = SoundEvent.of(new Identifier(GreatBigWorld.NAMESPACE, "music_disc.pina_colada"));

    @Override
    public void register() {
        registerSoundEvent(ENTITY_MOOSE_HURT);
        registerSoundEvent(ENTITY_MOOSE_DEATH);
        registerSoundEvent(ENTITY_MOOSE_WARNING);
        registerSoundEvent(MUSIC_DISC_SUNRISE);
        registerSoundEvent(MUSIC_DISC_PINA_COLADA);
    }

    private static void registerSoundEvent(SoundEvent soundEvent) {
        Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent);
    }
}
