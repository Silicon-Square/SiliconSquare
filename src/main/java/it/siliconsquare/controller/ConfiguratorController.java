package it.siliconsquare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.siliconsquare.SiliconSquareApplication;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.configuration.Configuration;
import it.siliconsquare.provider.Database;

@RestController
public class ConfiguratorController {

	@GetMapping("/configurator")
	public static void getLastConfigurator(HttpServletRequest req, HttpServletResponse resp) {

		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/configurator.jsp");
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			Logger.getInstance().captureException(e);
		}
	}

	@PostMapping("/configurator/get-amazon-cart/all/items.asp")
	public static String getAmazonCartLink(HttpServletRequest req, HttpServletResponse resp) {

		Configuration config = (Configuration) req.getSession().getAttribute("configurator");

		if (config == null)
			return "";

		int count = 0;
		StringBuilder amazonLink = new StringBuilder();
		for (Component c : config.getAllComponents()) {
			if (c == null || c.getAmazonASIN().equals(""))
				continue;

			amazonLink.append("ASIN." + (count + 1) + "=" + c.getAmazonASIN() + "&" + "Quantity." + (count + 1) + "=1");
			if (count != config.getAllComponents().size())
				amazonLink.append("&");
			count++;

		}
		if (count == 0)
			return "";
		if (amazonLink.lastIndexOf("&") == amazonLink.length() - 1)
			amazonLink.deleteCharAt(amazonLink.length() - 1);
		return amazonLink.toString();
	}

	@GetMapping("/configurator/my-configuration/getid.asp")
	public static int getMyConfigurationId(HttpServletRequest req, HttpServletResponse resp) {

		Configuration config = (Configuration) req.getSession().getAttribute("configuration");
		if (config == null)
			return -1;
		int idConfig = config.getIdConfiguration();
		return idConfig;
	}

	@PostMapping("/configurator/add/{componentCategory}/{idComponent}")
	public static void saveToSession(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable("componentCategory") String componentCategory,
			@PathVariable("idComponent") String idComponent) {
		HttpSession session = req.getSession();
		Configuration config = (Configuration) session.getAttribute("configurator");
		if (config == null)
			config = new Configuration();
		Component component = Database.getInstance().getComponentById(componentCategory, Integer.parseInt(idComponent));
		config.add(component);
		session.setAttribute("configurator", config);

		Logger.getInstance()
				.captureMessage("Component " + component.getCategory() + " All components: "
						+ config.getAllComponents().size() + "\n Configurator[ " + session.getAttribute("configurator")
						+ "] added to configuration");
		// update request after ajax call
	}

	/**
	 * Removes a component from the configuration
	 * 
	 * @param req
	 * @param resp
	 * @param componentCategory
	 * @param idComponent
	 */
	@DeleteMapping("/configurator/remove/{componentCategory}/{idComponent}")
	public static void removeFromSession(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable("componentCategory") String componentCategory,
			@PathVariable("idComponent") String idComponent) {
		HttpSession session = req.getSession();
		Configuration config = (Configuration) session.getAttribute("configurator");

		if (config == null)
			return;

		Component component = Database.getInstance().getComponentById(componentCategory, Integer.parseInt(idComponent));
		config.remove(component);
		session.setAttribute("configurator", config);

		Logger.getInstance()
				.captureMessage("Component " + component.getCategory() + " All components: "
						+ config.getAllComponents().size() + "\n Configurator[ " + session.getAttribute("configurator")
						+ "] removed from configuration");

	}

	@PostMapping("/configurator/clear")
	public static void clearSession(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.setAttribute("configurator", new Configuration());
		Logger.getInstance().captureMessage("Configurator cleared");
	}

	// TODO:
	@GetMapping("/configurator/{id}")
	public static void getLastConfigurator(@PathVariable String id, HttpServletRequest req,
			HttpServletResponse resp) {
		int idConfiguration = Integer.parseInt(id);
		Configuration config = Database.getInstance().getConfigurationDAO()
				.getConfigurationById(idConfiguration);
				
		User user = (User) req.getSession().getAttribute("user");

		if (user == null || user.getIdUser() != config.getIdUser())
			config.setIdConfiguration(0);

		req.getSession().setAttribute("configurator", config);

		System.out.println("Lets see this config: " + (Configuration) req.getSession().getAttribute("configurator"));

		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/configurator.jsp");
			req.setAttribute("showButtons", 0);
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			Logger.getInstance().captureException(e);
		}
	}

	@PostMapping("/configurator/save")
	public static int saveConfiguration(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getSession().getAttribute("user") == null)
			return -1;
		int idUser = ((User) req.getSession().getAttribute("user")).getIdUser();
		Configuration config = (Configuration) req.getSession().getAttribute("configurator");
		if (idUser == 0 || config == null)
			return -1;
		config.setIdUser(idUser);

		Logger.getInstance().captureMessage("Configuration controller: " + config);
		System.out.println("Configuration controller: " + config);

		if (config.getAllComponents().isEmpty() && config.getIdConfiguration() != 0)
			if (Database.getInstance().getConfigurationDAO().deleteConfiguration(config))
				return 0;
			else
				return -1;

		int idConfig = Database.getInstance().getConfigurationDAO().saveConfiguration(config);
		config.setIdConfiguration(idConfig);
		boolean result = config.getIdConfiguration() != 0;
		if (!result)
			return -1;
		req.getSession().setAttribute("configurator", config);
		Logger.getInstance().captureMessage("Configuration saved" + "\n Configurator[ " + config + "] saved");
		// return result;

		return idConfig;
	}

	// TODO:
	@GetMapping(value = "/configurator/{componentCategory}.asp")
	public static String allComponents(@PathVariable String componentCategory, HttpServletRequest req,
			HttpServletResponse resp) {

		// if (SiliconSquareApplication.DEBUG)
		System.out.println("CompCat: " + componentCategory);

		// CompatibilityChecker cc = new
		// CompatibilityChecker((Configuration)req.getAttribute("configuration"));

		// CompatibilityChecker cc = new CompatibilityChecker();
		/*
		 * componentCategory = HtmlVisualizer.toEnum(componentCategory);
		 * ComponentCategory category = ComponentCategory.valueOf(componentCategory);
		 * cc.loadList(category);
		 */

		// cc.loadList(allComponents(componentCategory));

		String componentsJson = Database.getInstance().getAllPublishedComponents(componentCategory);

		if (SiliconSquareApplication.DEBUG)
			System.out.println(componentsJson);

		return componentsJson;
	}

	public static List<Configuration> getAllConfigByUserId(User user) {
		return Database.getInstance().getConfigurationDAO().getAllConfigurationsByUser(user);
	}

	@DeleteMapping("/configurator/delete/{id}")
	public static boolean deleteConfiguration(@PathVariable String id, HttpServletRequest req,
			HttpServletResponse resp) {
		int idConfiguration = Integer.parseInt(id);
		if (idConfiguration == 0)
			return false;
		Configuration config = Database.getInstance().getConfigurationDAO()
				.getConfigurationById(idConfiguration);
		if (config == null)
			return false;
		if (config.getIdUser() != ((User) req.getSession().getAttribute("user")).getIdUser())
			return false;
		return Database.getInstance().getConfigurationDAO().deleteConfiguration(config);
	}

	@GetMapping("/configurator/size/{id}")
	public static int getSize(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp) {
		int idConfiguration = Integer.parseInt(id);
		if(idConfiguration == 0)
			return 0;
		Configuration config = Database.getInstance().getConfigurationDAO().getConfigurationById(idConfiguration);
		if (config == null)
			return 0;
		return config.getAllComponents().size();
	}
}
