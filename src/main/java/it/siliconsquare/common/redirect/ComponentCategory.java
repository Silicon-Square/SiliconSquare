package it.siliconsquare.common.redirect;

public enum ComponentCategory {

    CASE("case"),
    CPU("cpu"),
    CPU_COOLER("cpu-cooler"),
    FAN("fan"),
    FAN_CONTROLLER("fan-controller"),
    MEMORY("memory"),
    MOTHERBOARD("motherboard"),
    OPTICAL_DRIVE("optical-drive"),
    POWER_SUPPLY("power-supply"),
    SOUND_CARD("sound-card"),
    INTERNAL_STORAGE("internal-storage"),
    EXTERNAL_STORAGE("external-storage"),
    THERMAL_COMPOUND("thermal-compound"),
    VIDEO_CARD("video-card"),
    WIRED_NETWORK_ADAPTER("wired-network-adapter"),
    WIRELESS_NETWORK_ADAPTER("wireless-network-adapter");

    public final String value;

    private ComponentCategory(String s) {
        value = s;
    }

    public static final String[] ALL = {
            CASE.value,
            CPU.value,
            CPU_COOLER.value,
            FAN.value,
            FAN_CONTROLLER.value,
            MEMORY.value,
            MOTHERBOARD.value,
            OPTICAL_DRIVE.value,
            POWER_SUPPLY.value,
            SOUND_CARD.value,
            INTERNAL_STORAGE.value,
            EXTERNAL_STORAGE.value,
            THERMAL_COMPOUND.value,
            VIDEO_CARD.value,
            WIRED_NETWORK_ADAPTER.value,
            WIRELESS_NETWORK_ADAPTER.value
    };
}
