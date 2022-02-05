package it.siliconsquare.common.redirect;


public enum ResourcePath {

    ROOT("https://resources.siliconsquare.it"),
    COMPONENTS(Root.WEB.value + "/components/"),
    COMPONENTS_CASE(COMPONENTS.value + "case/"),
    COMPONENTS_CPU(COMPONENTS.value + "cpu/"),
    COMPONENTS_CPU_COOLER(COMPONENTS.value + "cpu_cooler/"),
    COMPONENTS_FAN(COMPONENTS.value + "fan/"),
    COMPONENTS_FAN_CONTROLLER(COMPONENTS.value + "fan_controller/"),
    COMPONENTS_MEMORY(COMPONENTS.value + "memory/"),
    COMPONENTS_MOTHERBOARD(COMPONENTS.value + "motherboard/"),
    COMPONENTS_OPTICAL_DRIVE(COMPONENTS.value + "optical_drive/"),
    COMPONENTS_POWER_SUPPLY(COMPONENTS.value + "power_supply/"),
    COMPONENTS_SOUND_CARD(COMPONENTS.value + "sound_card/"),
    COMPONENTS_STORAGE(COMPONENTS.value + "storage/"),
    COMPONENTS_THERMAL_COMPOUND(COMPONENTS.value + "thermal_compound/"),
    COMPONENTS_VIDEO_CARD(COMPONENTS.value + "video_card/"),
    COMPONENTS_WIRELESS_NETWORK_ADAPTER(COMPONENTS.value + "wireless_network_adapter/"),
    COMPONENTS_WIRED_NETWORK_ADAPTER(COMPONENTS.value + "wired_network_adapter/"),
    USER_AVATAR(Root.USERS.value + "/avatars/"),
    USER_POST(Root.USERS.value + "/posts/"),
    USER_COMPONENT(COMPONENTS.value);

    public final String value;

    private ResourcePath(String value) {
        this.value = value;
    }

    private enum Root {
        WEB("/web"), USERS(WEB.value + "/users");

        public final String value;

        private Root(String value) {
            this.value = value;
        }
    }

}