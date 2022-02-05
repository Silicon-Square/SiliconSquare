package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;

import it.siliconsquare.SiliconSquareApplication;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationDAO;
import it.siliconsquare.model.administration.Log;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.model.component.*;
import it.siliconsquare.model.configuration.*;
import it.siliconsquare.provider.Database;

import org.springframework.beans.factory.annotation.Autowired;

import io.sentry.ITransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAOImpl implements ConfigurationDAO {
        @Autowired
        private List<Configuration> configurationList;
        private String sql = "SELECT * FROM configuration";

        // table names
        private final String tableCpu = "cpu";
        private final String tableMotherboard = "motherboard";
        private final String tableCpuCooler = "cpu_cooler";
        private final String tablePowerSupply = "power_supply";
        private final String tableCase = "public.case";
        private final String tableVideoCard = "video_card";

        private final String tableExternalStorage = "external_storage";
        private final String tableFan = "fan";
        private final String tableFanController = "fan_controller";
        private final String tableInternalStorage = "internal_storage";
        private final String tableMemory = "memory";
        private final String tableOpticalDrive = "optical_drive";
        private final String tableSoundCard = "sound_card";
        private final String tableThermalCompound = "thermal_compound";
        private final String tableWiredNetworkAdapter = "wired_network_adapter";
        private final String tableWirelessNetworkAdapter = "wireless_network_adapter";

        /*
         * private static final String tablename = "public.configuration";
         * private static final String pk = "id_configuration";
         * 
         * private static String SELECT = "SELECT *";
         * private static final String VISIBLE = " AND is_visible = true ";
         * private static final String NOT_VISIBLE = " AND is_visible = false ";
         * private static String WHERE = " WHERE " + tablename +
         * ".id_configuration = state.id_state ";
         * 
         * private static String sql = SELECT + " FROM " + tablename +
         * ", public.state "
         * + WHERE + VISIBLE;
         * private static String sql_not_visible = SELECT + " FROM " + tablename +
         * ", public.state " + WHERE + NOT_VISIBLE;
         */

        public ConfigurationDAOImpl() {
                configurationList = new ArrayList<>();
        }

        private String sqlJoin(String joinColumn, boolean specificConfiguration) {
                // NATURAL JOIN
                /*
                 * String whereClause = " WHERE id_configuration = ?";
                 * if(!specificConfiguration)
                 * whereClause = "";
                 * return
                 * " NATURAL JOIN public.configuration_"+joinColumn+" NATURAL JOIN "+
                 * joinColumn + whereClause;
                 */
                // Standard Join
                String whereClause = " AND configuration.id_configuration = ?";
                if (!specificConfiguration)
                        whereClause = "";

                String query = ", " + joinColumn + ", configuration_" + joinColumn + " WHERE " + joinColumn + ".id_"
                                + joinColumn + " = " +
                                "configuration_" + joinColumn + ".id_" + joinColumn + " AND configuration_" + joinColumn
                                + ".id_configuration = " +
                                "configuration.id_configuration" + whereClause;

                if (SiliconSquareApplication.DEBUG)
                        System.out.println(query);

                return query;
        }
        // private fetchMissingParameters()
        // getAllConfigurationByIdUser
        // getConfigurationById
        // saveConfiguration
        // deleteConfiguration
        // updateConfiguration

        private Configuration fetchMissingParameters(Configuration config) {

                String queryCpu = sql + ", " + tableCpu + " WHERE " + tableCpu
                                + ".id_cpu = configuration.id_cpu AND configuration.id_configuration = ?";
                String queryMotherboard = sql + ", " + tableMotherboard + " WHERE " + tableMotherboard
                                + ".id_motherboard = configuration.id_motherboard AND configuration.id_configuration = ?";
                String queryCpuCooler = sql + ", " + tableCpuCooler + " WHERE " + tableCpuCooler
                                + ".id_cpu_cooler = configuration.id_cpu_cooler AND configuration.id_configuration = ?";
                String queryPowerSupply = sql + ", " + tablePowerSupply + " WHERE " + tablePowerSupply
                                + ".id_power_supply = configuration.id_power_supply AND configuration.id_configuration = ?";
                String queryCase = sql + ", " + tableCase + " WHERE " + tableCase
                                + ".id_case = configuration.id_case AND configuration.id_configuration = ?";

                String queryVideoCard = sql + ", " + tableVideoCard + " WHERE " + tableVideoCard
                                + ".id_video_card = configuration.id_video_card AND configuration.id_configuration = ?";

                // More than one components into configuration
                String queryExternalStorage = sql + sqlJoin(tableExternalStorage, true);
                String queryFan = sql + sqlJoin(tableFan, true);
                String queryFanController = sql + sqlJoin(tableFanController, true);
                String queryInternalStorage = sql + sqlJoin(tableInternalStorage, true);
                String queryMemory = sql + sqlJoin(tableMemory, true);
                String queryOpticalDrive = sql + sqlJoin(tableOpticalDrive, true);
                String querySoundCard = sql + sqlJoin(tableSoundCard, true);
                String queryThermalCompound = sql + sqlJoin(tableThermalCompound, true);
                String queryWiredNetworkAdapter = sql + sqlJoin(tableWiredNetworkAdapter, true);
                String queryWirelessNetworkAdapter = sql + sqlJoin(tableWirelessNetworkAdapter, true);

                int idConfig = config.getIdConfiguration();
                Cpu cpu = Q2Obj.fromSelect(Cpu.class, queryCpu, idConfig);
                Case _case = Q2Obj.fromSelect(Case.class, queryCase, idConfig);
                PowerSupply powerSupply = Q2Obj.fromSelect(PowerSupply.class, queryPowerSupply, idConfig);
                Motherboard motherboard = Q2Obj.fromSelect(Motherboard.class, queryMotherboard, idConfig);
                CpuCooler cpuCooler = Q2Obj.fromSelect(CpuCooler.class, queryCpuCooler, idConfig);
                VideoCard videoCard = Q2Obj.fromSelect(VideoCard.class, queryVideoCard, idConfig);

                List<ConfigurationExternalStorage> listExternalStorage = Q2ObjList
                                .fromSelect(ConfigurationExternalStorage.class, queryExternalStorage, idConfig);
                List<ConfigurationFan> listFan = Q2ObjList.fromSelect(ConfigurationFan.class, queryFan, idConfig);
                List<ConfigurationFanController> listFanController = Q2ObjList.fromSelect(
                                ConfigurationFanController.class,
                                queryFanController, idConfig);
                List<ConfigurationInternalStorage> listInternalStorage = Q2ObjList
                                .fromSelect(ConfigurationInternalStorage.class, queryInternalStorage, idConfig);
                List<ConfigurationMemory> listMemory = Q2ObjList.fromSelect(ConfigurationMemory.class, queryMemory,
                                idConfig);
                List<ConfigurationOpticalDrive> listOpticalDrive = Q2ObjList.fromSelect(ConfigurationOpticalDrive.class,
                                queryOpticalDrive, idConfig);
                List<ConfigurationSoundCard> listSoundCard = Q2ObjList.fromSelect(ConfigurationSoundCard.class,
                                querySoundCard, idConfig);
                List<ConfigurationThermalCompound> listThermalCompound = Q2ObjList
                                .fromSelect(ConfigurationThermalCompound.class, queryThermalCompound, idConfig);
                List<ConfigurationWiredNetworkAdapter> listWiredNetworkAdapter = Q2ObjList
                                .fromSelect(ConfigurationWiredNetworkAdapter.class, queryWiredNetworkAdapter, idConfig);
                List<ConfigurationWirelessNetworkAdapter> listWirelessNetworkAdapter = Q2ObjList
                                .fromSelect(ConfigurationWirelessNetworkAdapter.class, queryWirelessNetworkAdapter,
                                                idConfig);

                System.out.println("memoryList - " + listMemory);

                config.add(cpu);
                config.add(_case);
                config.add(powerSupply);
                config.add(motherboard);
                config.add(cpuCooler);
                config.add(videoCard);
                config.setConfigurationComponentList(ComponentCategory.EXTERNAL_STORAGE, listExternalStorage);
                config.setConfigurationComponentList(ComponentCategory.FAN, listFan);
                config.setConfigurationComponentList(ComponentCategory.FAN_CONTROLLER, listFanController);
                config.setConfigurationComponentList(ComponentCategory.INTERNAL_STORAGE, listInternalStorage);
                config.setConfigurationComponentList(ComponentCategory.MEMORY, listMemory);
                config.setConfigurationComponentList(ComponentCategory.OPTICAL_DRIVE, listOpticalDrive);
                config.setConfigurationComponentList(ComponentCategory.SOUND_CARD, listSoundCard);
                config.setConfigurationComponentList(ComponentCategory.THERMAL_COMPOUND, listThermalCompound);
                config.setConfigurationComponentList(ComponentCategory.WIRED_NETWORK_ADAPTER, listWiredNetworkAdapter);
                config.setConfigurationComponentList(ComponentCategory.WIRELESS_NETWORK_ADAPTER,
                                listWirelessNetworkAdapter);

                // System.out.println("Configuration fetched " + config);
                return config;
        }

        @Override
        public Configuration getConfigurationById(int id) {
                System.out.println("Fetching configuration n" + id);
                Configuration config = Q2Obj.fromSelect(Configuration.class, sql + " WHERE id_configuration = ?", id);
                return fetchMissingParameters(config);
        }

        /**
         * @return the last configuration of user
         */
        @Override
        public Configuration getConfigurationByUser(User u) {
                return getConfigurationById(u.getIdUser());
        }

        public Configuration getConfigurationByIdUser(int id) {

                String orderBy = " ORDER BY id_configuration DESC LIMIT 1";
                Configuration config = Q2Obj.fromSelect(Configuration.class, sql + " WHERE id_user = ?" + orderBy,
                                id);

                System.out.println("Configuration fetched " + config);
                return fetchMissingParameters(config);
        }

        @Override
        public int saveConfiguration(Configuration configuration) {
                if (configuration == null || configuration.getIdUser() == null || configuration.getIdUser() == 0)
                        return 0;
                ITransaction transaction = Logger.getInstance().startTransaction("ConfigurationDAOImpl",
                                "saveConfiguration");
                int result = configuration.getIdConfiguration() == 0 ? insertConfiguration(configuration)
                                : updateConfiguration(configuration);
                // int result = insertConfiguration(configuration);
                Logger.getInstance().closeTransaction(transaction);
                return result;
        }

        private int insertConfiguration(Configuration configuration) {
                String sql = "INSERT INTO public.\"configuration\" (id_cpu, id_motherboard, id_cpu_cooler, id_power_supply, id_case, id_video_card, is_private, id_user)"
                                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement ps = null;
                try (Connection conn = Database.getInstance().getConnection()) {
                        ps = fillPreparedStatement(ps, configuration, sql, conn);
                        int affectedRows = ps.executeUpdate();
                        Logger.getInstance().captureMessage("Inserted " + affectedRows + " configuration");
                        if (affectedRows == 0)
                                return 0;
                        int idConfig = this.getConfigurationByIdUser(configuration.getIdUser()).getIdConfiguration();
                        configuration.setIdConfiguration(idConfig);
                        insertLists(configuration.getAllComponents(), configuration);
                        return configuration.getIdConfiguration();

                } catch (SQLException e) {
                        Logger.getInstance().captureException(e);
                        return 0;
                } finally {
                        try {
                                ps.close();
                        } catch (SQLException e) {
                                Logger.getInstance().captureException(e);
                                return 0;
                        }
                }
        }

        private PreparedStatement fillPreparedStatement(PreparedStatement ps, Configuration configuration, String sql,
                        Connection conn) throws SQLException {
                int defaultValue = Types.INTEGER;
                ps = conn.prepareStatement(sql);
                if (configuration.getCpu() != null)
                        ps.setInt(1, configuration.getCpu().getId());
                else
                        ps.setNull(1, defaultValue);
                if (configuration.getMotherboard() != null)
                        ps.setInt(2, configuration.getMotherboard().getId());
                else
                        ps.setNull(2, defaultValue);
                if (configuration.getCpuCooler() != null)
                        ps.setInt(3, configuration.getCpuCooler().getId());
                else
                        ps.setNull(3, defaultValue);

                if (configuration.getPowerSupply() != null)
                        ps.setInt(4, configuration.getPowerSupply().getId());
                else
                        ps.setNull(4, defaultValue);
                if (configuration.getCase() != null)
                        ps.setInt(5, configuration.getCase().getId());
                else
                        ps.setNull(5, defaultValue);
                if (configuration.getVideoCard() != null)
                        ps.setInt(6, configuration.getVideoCard().getId());
                else
                        ps.setNull(6, defaultValue);
                ps.setBoolean(7, configuration.isPrivate());
                ps.setInt(8, configuration.getIdUser());
                return ps;
        }

        private void insertLists(List<Component> allComponents, Configuration configuration) {
                resetConfiguration(configuration);
                System.out.println("config dao impl: " + configuration);
                Logger.getInstance().captureMessage("Inserting lists " + configuration);

                for (Component c : allComponents) {
                        System.out.println("INSIDE FOR!");
                        if (c.getId() != 0) {
                                sqlConfigurationInsert(c, configuration);
                        }
                }
        }

        @Override
        public boolean deleteConfiguration(Configuration configuration) {
                String sql = "DELETE FROM public.\"configuration\" WHERE id_configuration = ?;";
                int idConfig = configuration.getIdConfiguration();
                resetConfiguration(configuration);
                int affectedRows = Q2Sql.executeUpdate(sql, idConfig);
                Logger.getInstance().captureMessage("Deleted " + affectedRows + " configuration");
                return affectedRows > 0;
        }

        private void resetConfiguration(Configuration configuration) {
                int idConfig = configuration.getIdConfiguration();
                if (idConfig == 0)
                        return;

                String query = "SELECT remove_all_configuration_components(?);";
                Q2Sql.executeQuery(query, idConfig);
        }

        @Override
        public int updateConfiguration(Configuration configuration) {
                String sql = "UPDATE public.\"configuration\" SET id_cpu = ?, id_motherboard = ?, id_cpu_cooler = ?, id_power_supply = ?, id_case = ?, id_video_card = ?, is_private = ? WHERE id_user = ? AND id_configuration = ?;";
                PreparedStatement ps = null;
                int idConfig = configuration.getIdConfiguration();
                try (Connection conn = Database.getInstance().getConnection()) {
                        ps = fillPreparedStatement(ps, configuration, sql, conn);
                        ps.setInt(9, configuration.getIdConfiguration());
                        int affectedRows = ps.executeUpdate();
                        Logger.getInstance().captureMessage("Updated " + affectedRows + " configuration");
                        if (affectedRows != 0)
                                insertLists(configuration.getAllComponents(), configuration);
                        return idConfig;
                } catch (SQLException e) {
                        Logger.getInstance().captureException(e);
                        return idConfig;
                } finally {
                        try {
                                ps.close();
                        } catch (SQLException e) {
                                Logger.getInstance().captureException(e);
                                return idConfig;
                        }
                }
        }

        @Override
        public List<Configuration> getAllConfiguration() {
                List<Configuration> list = Q2ObjList.fromSelect(Configuration.class, sql);
                for (Configuration configuration : list)
                        configuration = fetchMissingParameters(configuration);

                return list;
        }

        /**
         * 
         * @param c
         * @param config
         * @return
         */
        private boolean sqlConfigurationInsert(Component c, Configuration config) {

                if (!c.canBeAddedMultipleTimes())
                        return false;

                int configId = config.getIdConfiguration();
                String objectName = c.getObjectTableName();
                int idComponent = c.getId();

                Logger.getInstance()
                                .captureMessage("sqlConfigurationInsert " + objectName + " " + idComponent + " "
                                                + configId);

                if (configId == 0 || idComponent == 0 || objectName == null || objectName.isEmpty())
                        return false;

                String query = "";

                query = "INSERT INTO public.configuration_" + objectName + " (id_configuration, id_"
                                + objectName + ") VALUES ( ?, ?);";

                PreparedStatement ps = null;
                try (Connection conn = Database.getInstance().getConnection()) {
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, configId);
                        ps.setInt(2, idComponent);
                        ps.executeUpdate();
                        return true;
                } catch (SQLException e) {
                        Logger.getInstance().captureException(e);
                        return false;
                } finally {
                        try {
                                ps.close();
                        } catch (SQLException e) {
                                Logger.getInstance().captureException(e);
                                return false;
                        }
                }
        }

        public List<Configuration> getAllConfigurationsByUser(User user) {
                if (user == null)
                        return new ArrayList<Configuration>();
                String sql = "SELECT * FROM public.\"configuration\" WHERE id_user = ? AND is_private = false ORDER BY id_configuration ASC;";
                return Q2ObjList.fromSelect(Configuration.class, sql, user.getIdUser());
        }
}
