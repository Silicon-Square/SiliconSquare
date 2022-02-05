package it.siliconsquare.model.configuration;

import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.component.*;
import it.siliconsquare.provider.ComponentProvider;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @Column(name = "id_cpu", nullable = false)
    private Integer idCpu;

    @JoinColumn(name = "configuration_cpu", updatable = false, insertable = false)
    @ManyToOne
    private Cpu cpu;

    @Column(name = "id_motherboard", nullable = false)
    private Integer idMotherboard;

    @JoinColumn(name = "configuration_motherboard", updatable = false, insertable = false)
    @ManyToOne
    private Motherboard motherboard;

    @Column(name = "id_cpu_cooler", nullable = false)
    private Integer idCpuCooler;

    @JoinColumn(name = "configuration_cpu_cooler", updatable = false, insertable = false)
    @ManyToOne
    private CpuCooler cpuCooler;

    @Column(name = "id_power_supply", nullable = false)
    private Integer idPowerSupply;

    @JoinColumn(name = "configuration_power_supply", updatable = false, insertable = false)
    @ManyToOne
    private PowerSupply powerSupply;

    @Column(name = "id_case", nullable = false)
    private Integer idCase;

    @JoinColumn(name = "configuration_case", nullable = false, referencedColumnName = "id_case", insertable = false, updatable = false)
    @ManyToOne
    private Case _case;

    @Column(name = "id_video_card", nullable = false)
    private Integer idVideoCard;

    @JoinColumn(name = "configuration_video_card", updatable = false, insertable = false)
    @ManyToOne
    private VideoCard videoCard;

    @Column(name = "is_private", nullable = false)
    private boolean isPrivate = false;

    @Column(name = "is_compatible", nullable = false)
    private boolean isCompatible;

    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @JoinColumn(name = "configuration_configuration_external_storage", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationExternalStorage> configurationExternalStorageList;

    @JoinColumn(name = "configuration_fan", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationFan> configurationFanList;

    @JoinColumn(name = "configuration_fan_controller", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationFanController> configurationFanControllerList;

    @JoinColumn(name = "configuration_internal_storage", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationInternalStorage> configurationInternalStorageList;

    @JoinColumn(name = "configuration_memory", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationMemory> configurationMemoryList;

    @JoinColumn(name = "configuration_optical_drive", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationOpticalDrive> configurationOpticalDriveList;

    @JoinColumn(name = "configuration_sound_card", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationSoundCard> configurationSoundCardList;

    @JoinColumn(name = "configuration_thermal_compound", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationThermalCompound> configurationThermalCompoundList;

    @JoinColumn(name = "configuration_wired_network_adapter", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationWiredNetworkAdapter> configurationWiredNetworkAdapterList;

    @JoinColumn(name = "configuration_wireless_network_adapter", updatable = false, insertable = false)
    @OneToMany
    private List<ConfigurationWirelessNetworkAdapter> configurationWirelessNetworkAdapterList;

    public Configuration() {
        configurationExternalStorageList = new ArrayList<>();
        configurationFanList = new ArrayList<>();
        configurationFanControllerList = new ArrayList<>();
        configurationInternalStorageList = new ArrayList<>();
        configurationMemoryList = new ArrayList<>();
        configurationOpticalDriveList = new ArrayList<>();
        configurationSoundCardList = new ArrayList<>();
        configurationThermalCompoundList = new ArrayList<>();
        configurationWiredNetworkAdapterList = new ArrayList<>();
        configurationWirelessNetworkAdapterList = new ArrayList<>();
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public List<ExternalStorage> getExternalStorageList() {
        List<ExternalStorage> externalStorageList = new ArrayList<ExternalStorage>();
        for (ConfigurationComponent<ExternalStorage> configurationExternalStorage : configurationExternalStorageList) {
            externalStorageList.add(configurationExternalStorage.getComponent());
        }
        return externalStorageList;
    }

    public List<Fan> getFanList() {
        List<Fan> fanList = new ArrayList<Fan>();
        for (ConfigurationComponent<Fan> configurationFan : configurationFanList) {
            fanList.add(configurationFan.getComponent());
        }
        return fanList;
    }

    public List<FanController> getFanControllerList() {
        List<FanController> fanControllerList = new ArrayList<FanController>();
        for (ConfigurationComponent<FanController> configurationFanController : configurationFanControllerList) {
            fanControllerList.add(configurationFanController.getComponent());
        }
        return fanControllerList;
    }

    public List<InternalStorage> getInternalStorageList() {
        List<InternalStorage> internalStorageList = new ArrayList<InternalStorage>();
        for (ConfigurationComponent<InternalStorage> configurationInternalStorage : configurationInternalStorageList) {
            internalStorageList.add(configurationInternalStorage.getComponent());
        }
        return internalStorageList;
    }

    public List<Memory> getMemoryList() {
        List<Memory> memoryList = new ArrayList<Memory>();
        for (ConfigurationComponent<Memory> configurationMemory : configurationMemoryList) {
            memoryList.add(configurationMemory.getComponent());
        }
        return memoryList;
    }

    public void add(Component c) {
        if (c instanceof Cpu) {
            this.cpu = (Cpu) c;
            this.idCpu = this.cpu.getId();
            return;
        }

        if (c instanceof Case) {
            this._case = (Case) c;
            this.idCase = _case.getId();
            return;
        }

        if (c instanceof CpuCooler) {
            this.cpuCooler = (CpuCooler) c;
            this.idCpuCooler = cpuCooler.getId();
            return;
        }

        if (c instanceof Motherboard) {
            this.motherboard = (Motherboard) c;
            this.idMotherboard = motherboard.getId();
            return;
        }

        if (c instanceof PowerSupply) {
            this.powerSupply = (PowerSupply) c;
            this.idPowerSupply = powerSupply.getId();
            return;
        }

        if (c instanceof VideoCard) {
            this.videoCard = (VideoCard) c;
            this.idVideoCard = videoCard.getId();
            return;
        }

        // add component to its list e.g. more components of Fan, Fan Controller, etc.
        // can be added to a single configuration
        // ConfigurationComponent configurationComponent =
        // ComponentProvider.createConfiguration(c);
        // if (configurationComponent != null) {
        // configurationComponent.setIdConfiguration(idConfiguration);
        // configurationComponent.setComponent(c);
        // addToList(configurationComponent);
        // System.out.println("Config Component: " + configurationComponent);
        // }

        if (c == null)
            return;
        addToList(c);

    }

    private void addToList(Component component) {
        ConfigurationComponent c = ComponentProvider.createConfiguration(component);
        if (idConfiguration != 0)
            c.setIdConfiguration(idConfiguration);
        c.setComponent(component);

        System.out.println("Configuration.java: CONFIG -> " + c);

        if (component instanceof ExternalStorage)
            this.configurationExternalStorageList.add((ConfigurationExternalStorage) c);

        if (component instanceof Fan)
            this.configurationFanList.add((ConfigurationFan) c);

        if (component instanceof FanController)
            this.configurationFanControllerList.add((ConfigurationFanController) c);

        if (component instanceof InternalStorage)
            this.configurationInternalStorageList.add((ConfigurationInternalStorage) c);

        if (component instanceof Memory)
            this.configurationMemoryList.add((ConfigurationMemory) c);

        if (component instanceof OpticalDrive)
            this.configurationOpticalDriveList.add((ConfigurationOpticalDrive) c);

        if (component instanceof SoundCard)
            this.configurationSoundCardList.add((ConfigurationSoundCard) c);

        if (component instanceof ThermalCompound)
            this.configurationThermalCompoundList.add((ConfigurationThermalCompound) c);

        if (component instanceof WiredNetworkAdapter)
            this.configurationWiredNetworkAdapterList.add((ConfigurationWiredNetworkAdapter) c);

        if (component instanceof WirelessNetworkAdapter) {
            this.configurationWirelessNetworkAdapterList.add((ConfigurationWirelessNetworkAdapter) c);
        }
    }

    public boolean remove(Component c) {
        if (c instanceof Cpu)
            if (idCpu == c.getId()) {
                this.cpu = null;
                this.idCpu = 0;
                return true;
            } else
                return false;

        if (c instanceof Case)
            if (idCase == c.getId()) {
                this._case = null;
                this.idCase = 0;
                return true;
            } else
                return false;

        if (c instanceof CpuCooler)
            if (idCpuCooler == c.getId()) {
                this.cpuCooler = null;
                this.idCpuCooler = 0;
                return true;
            } else
                return false;

        if (c instanceof Motherboard)
            if (idMotherboard == c.getId()) {
                this.motherboard = null;
                this.idMotherboard = 0;
                return true;
            } else
                return false;

        if (c instanceof PowerSupply)
            if (idPowerSupply == c.getId()) {
                this.powerSupply = null;
                this.idPowerSupply = 0;
                return true;
            } else
                return false;
        if (c instanceof VideoCard)
            if (idVideoCard == c.getId()) {
                this.videoCard = null;
                this.idVideoCard = 0;
                return true;
            } else
                return false;

        // remove component from its list e.g. more components of Fan, Fan Controller,
        // etc.
        return removeFromList(c);
    }

    private boolean removeFromList(Component c) {
        ConfigurationComponent configurationComponent = ComponentProvider.createConfiguration(c);
        if (c instanceof ExternalStorage)
            return this.configurationExternalStorageList.removeIf(
                    externalStorage -> externalStorage.getComponent().getId() == c.getId());

        if (c instanceof Fan)
            return this.configurationFanList.removeIf(
                    configurationFan -> configurationFan.getComponent().getId() == c.getId());

        if (c instanceof FanController)
            return this.configurationFanControllerList.removeIf(configurationFanController -> configurationFanController
                    .getComponent().getId() == c.getId());
        if (c instanceof InternalStorage)
            return this.configurationInternalStorageList
                    .removeIf(configurationInternalStorage -> configurationInternalStorage
                            .getComponent().getId() == c.getId());

        if (c instanceof Memory)
            return this.configurationMemoryList
                    .removeIf(configurationMemory -> configurationMemory.getComponent().getId() == c
                            .getId());

        if (c instanceof OpticalDrive)
            return this.configurationOpticalDriveList.removeIf(configurationOpticalDrive -> configurationOpticalDrive
                    .getComponent().getId() == c.getId());

        if (c instanceof SoundCard)
            return this.configurationSoundCardList
                    .removeIf(configurationSoundCard -> configurationSoundCard.getComponent()
                            .getId() == c.getId());

        if (c instanceof ThermalCompound)
            return this.configurationThermalCompoundList
                    .removeIf(configurationThermalCompound -> configurationThermalCompound
                            .getComponent().getId() == c.getId());

        if (c instanceof WiredNetworkAdapter)
            return this.configurationWiredNetworkAdapterList
                    .removeIf(configurationWiredNetworkAdapter -> configurationWiredNetworkAdapter.getComponent()
                            .getId() == c.getId());

        if (c instanceof WirelessNetworkAdapter)
            return this.configurationWirelessNetworkAdapterList
                    .removeIf(configurationWirelessNetworkAdapter -> configurationWirelessNetworkAdapter.getComponent()
                            .getId() == c.getId());

        return false;
    }

    public List<OpticalDrive> getOpticalDriveList() {
        List<OpticalDrive> opticalDriveList = new ArrayList<OpticalDrive>();
        for (ConfigurationComponent<OpticalDrive> configurationOpticalDrive : configurationOpticalDriveList) {
            opticalDriveList.add(configurationOpticalDrive.getComponent());
        }
        return opticalDriveList;
    }

    public List<SoundCard> getSoundCardList() {
        List<SoundCard> soundCardList = new ArrayList<SoundCard>();
        for (ConfigurationComponent<SoundCard> configurationSoundCard : configurationSoundCardList) {
            soundCardList.add(configurationSoundCard.getComponent());
        }
        return soundCardList;
    }

    public List<ThermalCompound> getThermalCompoundList() {
        List<ThermalCompound> thermalCompoundList = new ArrayList<ThermalCompound>();
        for (ConfigurationComponent<ThermalCompound> configurationThermalCompound : configurationThermalCompoundList) {
            thermalCompoundList.add(configurationThermalCompound.getComponent());
        }
        return thermalCompoundList;
    }

    public List<WiredNetworkAdapter> getWiredNetworkAdapterList() {
        List<WiredNetworkAdapter> wiredNetworkAdapterList = new ArrayList<WiredNetworkAdapter>();
        for (ConfigurationComponent<WiredNetworkAdapter> configurationWiredNetworkAdapter : configurationWiredNetworkAdapterList) {
            wiredNetworkAdapterList.add(configurationWiredNetworkAdapter.getComponent());
        }
        return wiredNetworkAdapterList;
    }

    public List<WirelessNetworkAdapter> getWirelessNetworkAdapterList() {
        List<WirelessNetworkAdapter> wirelessNetworkAdapterList = new ArrayList<WirelessNetworkAdapter>();
        for (ConfigurationComponent<WirelessNetworkAdapter> configurationWirelessNetworkAdapter : configurationWirelessNetworkAdapterList) {
            wirelessNetworkAdapterList.add(configurationWirelessNetworkAdapter.getComponent());
        }
        return wirelessNetworkAdapterList;
    }

    public List<Component> getAllComponents() {
        List<Component> components = new ArrayList<Component>();
        if (cpu != null)
            components.add(cpu);

        if (_case != null)
            components.add(_case);

        if (cpuCooler != null)
            components.add(cpuCooler);

        if (motherboard != null)
            components.add(motherboard);

        if (powerSupply != null)
            components.add(powerSupply);

        if (videoCard != null)
            components.add(videoCard);

        if (getMemoryList() != null)
            components.addAll(getMemoryList());

        if (getInternalStorageList() != null)
            components.addAll(getInternalStorageList());

        if (getFanList() != null)
            components.addAll(getFanList());

        if (getFanControllerList() != null)
            components.addAll(getFanControllerList());

        if (getExternalStorageList() != null)
            components.addAll(getExternalStorageList());

        if (getOpticalDriveList() != null)
            components.addAll(getOpticalDriveList());

        if (getSoundCardList() != null)
            components.addAll(getSoundCardList());

        if (getThermalCompoundList() != null)
            components.addAll(getThermalCompoundList());

        if (getWiredNetworkAdapterList() != null)
            components.addAll(getWiredNetworkAdapterList());

        if (getWirelessNetworkAdapterList() != null)
            components.addAll(getWirelessNetworkAdapterList());
        return components;
    }

    public int getIdConfiguration() {
        return idConfiguration;
    }

    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public CpuCooler getCpuCooler() {
        return cpuCooler;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public Case getCase() {
        return _case;
    }

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isCompatible() {
        return isCompatible;
    }

    public void setCompatible(boolean compatible) {
        isCompatible = compatible;
    }

    @Override
    public String toString() {
        return "Configuration [idConfiguration=" + idConfiguration + ", idCpu=" + idCpu + ", cpu=" + cpu
                + ", motherboard=" + motherboard
                + ", cpuCooler=" + cpuCooler + ", powerSupply=" + powerSupply + ", _case=" + _case + ", videoCard="
                + videoCard + ", configurationExternalStorageList=" + configurationExternalStorageList
                + ", configurationFanList=" + configurationFanList + ", configurationFanControllerList="
                + configurationFanControllerList + ", configurationInternalStorageList="
                + configurationInternalStorageList
                + ", configurationMemoryList=" + configurationMemoryList + ", configurationOpticalDriveList="
                + configurationOpticalDriveList + ", configurationSoundCardList=" + configurationSoundCardList
                + ", configurationThermalCompoundList=" + configurationThermalCompoundList
                + ", configurationWiredNetworkAdapterList=" + configurationWiredNetworkAdapterList
                + ", configurationWirelessNetworkAdapterList=" + configurationWirelessNetworkAdapterList + "]";
    }

    public <T> void setConfigurationComponentList(ComponentCategory category, List<T> list) {
        // System.out.println("Received: " + list);
        switch (category) {
            case EXTERNAL_STORAGE:
                for (var o : list)
                    // if (o instanceof ExternalStorage)
                    configurationExternalStorageList.add((ConfigurationExternalStorage) o);
                break;
            case FAN:
                for (var o : list)
                    // if (o instanceof Fan)
                    configurationFanList.add((ConfigurationFan) o);
                break;
            case FAN_CONTROLLER:
                for (var o : list)
                    // if (o instanceof FanController)
                    configurationFanControllerList.add((ConfigurationFanController) o);
                break;
            case INTERNAL_STORAGE:
                for (var o : list)
                    // if (o instanceof InternalStorage)
                    configurationInternalStorageList.add((ConfigurationInternalStorage) o);

                break;
            case MEMORY:
                for (var o : list)
                    // if (o instanceof Memory)
                    configurationMemoryList.add((ConfigurationMemory) o);
                break;
            case OPTICAL_DRIVE:
                for (var o : list)
                    // if (o instanceof OpticalDrive)
                    configurationOpticalDriveList.add((ConfigurationOpticalDrive) o);

                break;
            case SOUND_CARD:
                for (var o : list)
                    // if (o instanceof SoundCard)
                    configurationSoundCardList.add((ConfigurationSoundCard) o);

                break;
            case THERMAL_COMPOUND:
                for (var o : list)
                    // if (o instanceof ThermalCompound)
                    configurationThermalCompoundList.add((ConfigurationThermalCompound) o);

                break;
            case WIRED_NETWORK_ADAPTER:
                for (var o : list)
                    // if (o instanceof WiredNetworkAdapter)
                    configurationWiredNetworkAdapterList.add((ConfigurationWiredNetworkAdapter) o);

                break;
            case WIRELESS_NETWORK_ADAPTER:
                for (var o : list)
                    // if (o instanceof WirelessNetworkAdapter)
                    configurationWirelessNetworkAdapterList.add((ConfigurationWirelessNetworkAdapter) o);
                break;
            default:
                return;
        }

    }

    public boolean hasMotherboard() {
        return motherboard != null;
    }

    public boolean hasInternalStorage() {
        return this.getInternalStorageList().size() > 0;
    }

    public boolean containsM2Slots() {
        if (!hasInternalStorage())
            return false;
        for (InternalStorage internalStorage : getInternalStorageList())
            if (internalStorage.get_interface().contains("M.2"))
                return true;
        return false;
    }

}
