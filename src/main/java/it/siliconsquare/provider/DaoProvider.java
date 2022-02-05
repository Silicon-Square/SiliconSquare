package it.siliconsquare.provider;

import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.DAO.Impl.component.*;
import it.siliconsquare.model.DAO.component.ComponentDAO;

public class DaoProvider {
	public static ComponentDAO getFactory(ComponentCategory type) {
		switch(type) {
			case CPU: return new CPUDAOImpl();
			case CASE: return new CaseDAOImpl();
			case CPU_COOLER: return new CpuCoolerDAOImpl();
			case EXTERNAL_STORAGE: return new ExternalStorageDAOImpl();
			case FAN: return new FanDAOImpl();
			case FAN_CONTROLLER: return new FanControllerDAOImpl();
			case INTERNAL_STORAGE: return new InternalStorageDAOImpl();
			case MEMORY: return new MemoryDAOImpl();
			case OPTICAL_DRIVE: return new OpticalDriveDAOImpl();
			case POWER_SUPPLY: return new PowerSupplyDAOImpl();
			case SOUND_CARD: return new SoundCardDAOImpl();
			case THERMAL_COMPOUND: return new ThermalCompoundDAOImpl();
			case VIDEO_CARD: return new VideoCardDAOImpl();
			case WIRED_NETWORK_ADAPTER: return new WiredNetworkAdapterDAOImpl();
			case WIRELESS_NETWORK_ADAPTER: return new WirelessNetworkAdapterDAOImpl();
			case MOTHERBOARD: return new MotherboardDAOImpl();
			default: return null;
		}
	}

}