package it.siliconsquare.provider;

import java.util.List;

import com.google.gson.Gson;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.component.*;
import it.siliconsquare.model.configuration.*;

public class ComponentProvider {
	public static Component create(ComponentCategory type, String json) {
		switch (type) {
			case CPU:
				return new Gson().fromJson(json, Cpu.class);
			case CASE:
				return new Gson().fromJson(json, Case.class);
			case CPU_COOLER:
				return new Gson().fromJson(json, CpuCooler.class);
			case EXTERNAL_STORAGE:
				return new Gson().fromJson(json, ExternalStorage.class);
			case FAN:
				return new Gson().fromJson(json, Fan.class);
			case FAN_CONTROLLER:
				return new Gson().fromJson(json, FanController.class);
			case INTERNAL_STORAGE:
				return new Gson().fromJson(json, InternalStorage.class);
			case MEMORY:
				return new Gson().fromJson(json, Memory.class);
			case OPTICAL_DRIVE:
				return new Gson().fromJson(json, OpticalDrive.class);
			case POWER_SUPPLY:
				return new Gson().fromJson(json, PowerSupply.class);
			case SOUND_CARD:
				return new Gson().fromJson(json, SoundCard.class);
			case THERMAL_COMPOUND:
				return new Gson().fromJson(json, ThermalCompound.class);
			case VIDEO_CARD:
				return new Gson().fromJson(json, VideoCard.class);
			case WIRED_NETWORK_ADAPTER:
				return new Gson().fromJson(json, WiredNetworkAdapter.class);
			case WIRELESS_NETWORK_ADAPTER:
				return new Gson().fromJson(json, WirelessNetworkAdapter.class);
			case MOTHERBOARD:
				return new Gson().fromJson(json, Motherboard.class);
			default:
				return null;
		}
	}

	public static String createJson(ComponentCategory type, List<Component> componentList) {
		
		// List<Motherboard> mList = new ArrayList<>();
		// for(Component component : componentList) {
		// 	Motherboard m = (Motherboard) component;
		// 	mList.add(m);
			
		// }

		return new Gson().toJson(componentList);
/* 
		switch (type) {
			case CPU:
				return new Gson().toJson(componentList, Cpu.class);
			case CASE:
				return new Gson().toJson(componentList, Case.class);
			case CPU_COOLER:
				return new Gson().toJson(componentList, CpuCooler.class);
			case EXTERNAL_STORAGE:
				return new Gson().toJson(componentList, ExternalStorage.class);
			case FAN:
				return new Gson().toJson(componentList, Fan.class);
			case FAN_CONTROLLER:
				return new Gson().toJson(componentList, FanController.class);
			case INTERNAL_STORAGE:
				return new Gson().toJson(componentList, InternalStorage.class);
			case MEMORY:
				return new Gson().toJson(componentList, Memory.class);
			case OPTICAL_DRIVE:
				return new Gson().toJson(componentList, OpticalDrive.class);
			case POWER_SUPPLY:
				return new Gson().toJson(componentList, PowerSupply.class);
			case SOUND_CARD:
				return new Gson().toJson(componentList, SoundCard.class);
			case THERMAL_COMPOUND:
				return new Gson().toJson(componentList, ThermalCompound.class);
			case VIDEO_CARD:
				return new Gson().toJson(componentList, VideoCard.class);
			case WIRED_NETWORK_ADAPTER:
				return new Gson().toJson(componentList, WiredNetworkAdapter.class);
			case WIRELESS_NETWORK_ADAPTER:
				return new Gson().toJson(componentList, WirelessNetworkAdapter.class);
			case MOTHERBOARD:
				return new Gson().toJson(componentList, Motherboard.class);
			default:
				return "";
		} */
	}

	public static <T> ConfigurationComponent createConfiguration(Object type) {

		if (type instanceof ExternalStorage)
			return new ConfigurationExternalStorage();
		else if (type instanceof Fan)
			return new ConfigurationFan();
		else if (type instanceof FanController)
			return new ConfigurationFanController();
		else if (type instanceof InternalStorage)
			return new ConfigurationInternalStorage();
		else if (type instanceof Memory)
			return new ConfigurationMemory();
		else if (type instanceof OpticalDrive)
			return new ConfigurationOpticalDrive();
		else if (type instanceof SoundCard)
			return new ConfigurationSoundCard();
		else if (type instanceof ThermalCompound)
			return new ConfigurationThermalCompound();
		else if (type instanceof WiredNetworkAdapter)
			return new ConfigurationWiredNetworkAdapter();
		else if (type instanceof WirelessNetworkAdapter)
			return new ConfigurationWirelessNetworkAdapter();

		return null;
	}

}