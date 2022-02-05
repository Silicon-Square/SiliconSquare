package it.siliconsquare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.component.Attribute;
import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.Memory;
import it.siliconsquare.model.component.Motherboard;
import it.siliconsquare.model.configuration.Configuration;
import it.siliconsquare.provider.ComponentProvider;
import it.siliconsquare.provider.DaoProvider;

public class CompatibilityChecker {

    private Configuration configuration;

    /**
     * Motherboard port/slots/space count. This is the number of slots that the
     * motherboard has.
     */
    private HashMap<String, Integer> checkMotherboard = new HashMap<String, Integer>(); // compatibility count
                                                                                        // parameters to check

    /**
     * Number of ports/slots/space needed for all the components.
     */
    private HashMap<String, Integer> checkComponents = new HashMap<String, Integer>(); // compatibility count parameters
                                                                                       // to check

    private void initCheck() {

        checkMotherboard.put("mini_pcie_msata_slots", 0);
        // checkMotherboard.put("m_2_slots", 0);
        checkMotherboard.put("pci_e_x1_slots", 0);
        checkMotherboard.put("pci_e_x8_slots", 0);
        checkMotherboard.put("pci_e_x4_slots", 0);
        checkMotherboard.put("sata_6_gb_s", 0);
        checkMotherboard.put("msata_slots", 0);
        checkMotherboard.put("memory_slots", 0);
        checkMotherboard.put("pci_slots", 0);
        checkMotherboard.put("usb_2_0_headers", 0);
        checkMotherboard.put("usb_3_2_gen_1_headers", 0);
        checkMotherboard.put("usb_3_2_gen_2x2_headers", 0);
        checkMotherboard.put("usb_2_0_headers_single_port", 0);
        checkMotherboard.put("usb_3_2_gen_2_headers", 0);
        checkMotherboard.put("pci_e_x16_slots", 0);
        checkMotherboard.put("memory_max", 0);
        checkMotherboard.put("mini_pcie_slots", 0);
        checkMotherboard.put("agp", 0);

        checkComponents.putAll(checkMotherboard);
    }

    public CompatibilityChecker() {
        configuration = new Configuration();
        initCheck();
    }

    public CompatibilityChecker(Configuration configuration) {
        initCheck();
        this.configuration = configuration;
        initializeConfiguration();
    }

    /**
     * Initialize the configuration. Sets all slots count with the correct values.
     */
    private void initializeConfiguration() {
        for (Component c : configuration.getAllComponents()) {
            for (Attribute att : c.getAllCompatibilityParameters()) {
                if (c instanceof Motherboard) {
                    if (checkMotherboard.containsKey(att.getName()))
                        checkMotherboard.put(att.getName(), Integer.parseInt(att.getValue()));
                } else {
                    if (!checkComponents.containsKey(att.getName())) {
                        parseParameter(att.getName(), att.getValue());
                        continue;
                    }

                    System.out.println("oldValue: " + checkComponents.get(att.getName()));
                    Integer oldValue = checkComponents.get(att.getName());

                    checkComponents.put(att.getName(), oldValue + Integer.parseInt(att.getValue()));
                }
            }
        }

        if (configuration.hasMotherboard()) {
            // Check if the motherboard has enough slots for all the components.
            for (String name : checkMotherboard.keySet()) {

                int motherboardSlotValue = checkMotherboard.get(name);
                int componentSlotValue = checkComponents.get(name);

                if (motherboardSlotValue < componentSlotValue) {
                    this.configuration.setCompatible(false);
                    break;
                }
            }
        }

        System.out.println("COMPONENTS COUNT: " + checkComponents.toString());
        System.out.println("MOTHERBOARD COUNT: " + checkMotherboard.toString());

    }

    /**
     * Get the paramater value from the attribute name. Parse through the value.
     * 
     * @param name
     */
    private boolean parseParameter(String name, String value) {
        if (!name.equals("interface") || value.isEmpty() || value == null)
            return false;

        String interfaceType = value.toLowerCase();

        if (interfaceType.contains("pci")) {
            if (interfaceType.contains("16"))
                return updatePciCascade("pci_e_x16_slots");
            if (interfaceType.contains("8"))
                return updatePciCascade("pci_e_x8_slots");
            if (interfaceType.contains("4"))
                return updatePciCascade("pci_e_x4_slots");
            if (interfaceType.contains("2"))
                return updatePciCascade("pci_e_x2_slots");
            if (interfaceType.contains("1"))
                return updatePciCascade("pci_e_x1_slots");
            if (interfaceType.contains("mini"))
                return updatePciCascade("mini_pcie_slots");
            if (interfaceType.contains("x"))
                return updatePciCascade("pcie_slots");
            return updatePciCascade("pci_slots");
        }
        if (interfaceType.contains("m.2"))
            return updatePciCascade("m_2_slots");
        if (interfaceType.contains("msata"))
            return updatePciCascade("msata_slots");
        if (interfaceType.contains("sata"))
            return updatePciCascade("sata_6_gb_s");
        if (interfaceType.contains("agp"))
            return updatePciCascade("agp");
        return false;

    }

    /**
     * 
     * @param category
     * @return a list of {@link ComponentCategory} components that are compatible
     *         with the configuration
     * @see {@link Configuration}
     */
    public String loadList(ComponentCategory category) {
        // type = HtmlVisualizer.toEnum(type);
        // ComponentCategory category = ComponentCategory.valueOf(type);

        if (SiliconSquareApplication.DEBUG)
            System.out.println("Category:" + category);

        var dao = DaoProvider.getFactory(category);

        if (dao == null)
            return "";

        var listComponents = DaoProvider.getFactory(category).getAllPublished();
        List<Component> compatibleList = new ArrayList<>();
        for (var component : listComponents) {
            boolean fullyCompatible = true;
            for (Component componentConfig : (List<Component>) configuration.getAllComponents()) {
                if (componentConfig == null)
                    continue;
                if (!isCompatible(componentConfig, (Component) component, category) && fullyCompatible) {
                    fullyCompatible = false;
                    break;
                }

            }
            if (fullyCompatible)
                compatibleList.add((Component) component);
        }

        Logger.getInstance().captureMessage("[PROVA] Original size:" + listComponents.size() + "\n"
                + "[PROVA] Compatible size:" + compatibleList.size());
        System.out.println("[PROVA] Original size:" + listComponents.size());
        System.out.println("[PROVA] Compatible size:" + compatibleList.size());
        // order the list compatibleList in ascending order by id
        compatibleList.sort(Comparator.comparing(Component::getId));

        // return HtmlVisualizer.toHtml(compatibleList);
        return ComponentProvider.createJson(category, compatibleList);
    }

    /**
     * Check if there are enough slots/ports/space of motherboard for the component.
     * Checks each single component of the component
     * 
     * @param componentConfig
     * @param componentToAdd
     * @param category        the category of the component
     * @return
     */
    private boolean isCompatible(Component componentConfig, Component componentToAdd, ComponentCategory category) {

        String response = componentConfig.isCompatible(category, componentToAdd.getAllCompatibilityParameters());
        // for (String key : checkComponents.keySet()) {
        switch (response) {
            case ProtocolConfiguration.CHECK_MOTHERBOARD_RAM_COMPATIBLE:
                // if (!this.configuration.hasMotherboard())
                // break;

                if (componentToAdd instanceof Memory) {
                    Integer memoryMax = checkMotherboard.get("memory_max");
                    Integer memorySlots = checkMotherboard.get("memory_slots");

                    if (!checkParameter("memory_slots", memorySlots))
                        return false;
                    if (!checkParameter("memory_max", memoryMax))
                        return false;
                    return true;
                } else if (componentConfig instanceof Motherboard) { // TODO: Draft
                    Integer memoryMax = checkComponents.get("memory_max");
                    Integer memorySlots = checkComponents.get("memory_slots");

                    if (!checkParameter("memory_slots", memorySlots))
                        return false;
                    if (!checkParameter("memory_max", memoryMax))
                        return false;
                    return true;
                }
                break;

            // componentToAdd --> Motherboard, Io da motherboard ho abbastanza slot? se io
            // ho 128, - metto 48TM = 64 > 0, ok ce la faccio
            // componentToAdd --> Mmeory, Io da memory, posso entrarci in questa motherbard?
            // se 128 - (48V + 4N) = 80 >0 ? sì, entro

            // if(componentToAdd instanceof Memory)
            // componentToAdd = (Memory) componentToAdd;
            // else if(componentToAdd instanceof Motherboard)
            // componentToAdd = (Motherboard) componentToAdd;
            // Memory memory = (Memory) componentToAdd;
            // if (checkParameter("memory_max", memory.getTotalRam())
            // && checkParameter("memory_slots", memory.getMemorySlots()))
            // return true;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_PCI:
                if (checkPciCascade("pci_slots") != null)
                    return true;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1:
                if (checkPciCascade("pci_e_x1_slots") != null)
                    return true;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X2:
                if (checkPciCascade("pci_e_x2_slots") != null)
                    return true;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X4:
                if (checkPciCascade("pci_e_x4_slots") != null)
                    return true;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X8:
                if (checkPciCascade("pci_e_x8_slots") != null)
                    return true;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X16:
                if (checkPciCascade("pci_e_x16_slots") != null)
                    return true;
                break;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_M2:
                if (configuration.hasMotherboard() && configuration.getMotherboard().getM2Slots() != null)
                    if (!configuration.containsM2Slots())
                        return true;
                break;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_SATA:
                if (checkParameter("sata_6_gb_s"))
                    return true;
                break;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_SLI_CROSSFIRE:
                if (checkParameter("sli_crossfire"))
                    return true;
                break;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_AGP:
                if (checkParameter("agp"))
                    return true;
                break;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_MINI_PCIE:
                if (checkParameter("mini_pcie_slots"))
                    return true;
                break;
            case ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X:
                if (checkPciCascade("pci_e_x_slots") != null)
                    return true;
                break;
            default:
                break;
        }

        System.out.println("[PROVA] RESPONSE: " + response);
        if (!response.equals(ProtocolConfiguration.COMPATIBLE))
            return false;
        return true;
        // return response.equals(ProtocolConfiguration.COMPATIBLE);

    }

    /**
     * Check if there is enough space/slots for the component with motherboard
     * {@param parameter} name
     * 
     * @param parameter
     * @param value     number of slots/space required
     * @return
     */
    private boolean checkParameter(String parameter, Integer value) {
        int componentSlot = checkComponents.get(parameter);
        if (this.configuration.hasMotherboard()) {
            int motherboardSlot = checkMotherboard.get(parameter);

            // TODO:
            System.out
                    .println("[checkParameter HAS] Motherboard slot: " + motherboardSlot + " Component slot: "
                            + componentSlot);
            return motherboardSlot <= (componentSlot + value);
        }
        // TODO:
        System.out
                .println("[checkParameter NOT HAS] Motherboard slot: " + checkMotherboard.get(parameter)
                        + " Component slot: " + componentSlot);
        if (checkMotherboard.containsKey(parameter))
            return value >= componentSlot;
        return false;
    }

    /**
     * Check if there is enough space/slots for the component with
     * <b>{@param parameter}</b> name
     * Use only for components which require one slot/space
     * 
     * @param parameter
     * @return
     */
    private boolean checkParameter(String parameter) {
        return checkParameter(parameter, 1);
    }

    /**
     * Update the space/slots {@param parameter} with <b>{@param value}</b>
     * slots/space required
     * 
     * @param parameter
     * @param value
     */
    private boolean updateParameter(String parameter, Integer value) {
        if (checkParameter(parameter, value) && checkComponents.containsKey(parameter)) {

            int componentSlotValue = checkComponents.get(parameter);
            checkComponents.put(parameter, componentSlotValue + value); // adds the value to the current value
            return true;
        }
        return false;
    }

    /**
     * Update the space/slots {@param parameter}
     * Use for components requiring <b> ONLY ONE</b> slot/space
     * 
     * @param parameter
     */
    private boolean updateParameter(String parameter) {
        return updateParameter(parameter, 1);
    }

    /**
     * 
     * @param pciSlot
     * @return the name of the checked pciSlot which can be used to update the
     *         configuration e.g."pci_e_x1_slots" <br>
     *         <br>
     *         If the pciSlot is not compatible with the configuration, return NULL
     * 
     * @see {@link Configuration}
     */
    private String checkPciCascade(String pciSlot) {
        switch (pciSlot) {
            case "pci_e_x1_slots":
                if (checkParameter("pci_e_x1_slots"))
                    return pciSlot;

            case "pci_e_x2_slots":
                if (checkParameter("pci_e_x2_slots"))
                    return pciSlot;

            case "pci_e_x4_slots":
                if (checkParameter("pci_e_x4_slots"))
                    return pciSlot;

            case "pci_e_x8_slots":
                if (checkParameter("pci_e_x8_slots"))
                    return pciSlot;

            case "pci_e_x16_slots":
                if (checkParameter("pci_e_x16_slots"))
                    return pciSlot;

            default:
                return null;
        }
    }

    private boolean updatePciCascade(String pciSlot) {

        String pciSlotName = checkPciCascade(pciSlot);
        if (pciSlotName == null)
            return false;
        return updateParameter(pciSlotName);
    }

}