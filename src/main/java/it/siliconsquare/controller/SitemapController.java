package it.siliconsquare.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.siliconsquare.common.SEO.XmlUrl;
import it.siliconsquare.common.SEO.XmlUrlSet;
import it.siliconsquare.common.redirect.Page;
import it.siliconsquare.common.redirect.ResourcePath;
import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.ComponentCategory;
import it.siliconsquare.provider.Database;

@Controller
public class SitemapController {
    static XmlUrlSet xmlUrlSet = null;
    static String myPage = "https://www.siliconsquare.it/";

    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
        // if (xmlUrlSet == null)
        // xmlUrlSet = loadFromFTP();
        // return xmlUrlSet;
        return loadFromFTP();
    }

    private XmlUrlSet loadFromFTP() {
        URL url;
        xmlUrlSet = new XmlUrlSet();
        try {
            url = new URL(ResourcePath.ROOT.value + "/sitemap.xml");
            Scanner s = new Scanner(url.openStream());
            StringBuilder sb = new StringBuilder();
            while (s.hasNext())
                sb.append(s.nextLine());
            s.close();
            return readFile(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new XmlUrlSet();
    }

    public static XmlUrlSet createSiteMap() {
        xmlUrlSet = new XmlUrlSet();

        create(xmlUrlSet, "", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, Page.HOME, XmlUrl.Priority.HIGH);
        create(xmlUrlSet, Page.TEMPLATES, XmlUrl.Priority.HIGH);
        create(xmlUrlSet, Page.ABOUTUS, XmlUrl.Priority.HIGH);
        create(xmlUrlSet, Page.COMPONENT, XmlUrl.Priority.HIGH);
        create(xmlUrlSet, Page.CONFIGURATOR, XmlUrl.Priority.HIGH);

        create(xmlUrlSet, "templates", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "aboutUs", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "component", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "configurator", XmlUrl.Priority.HIGH);

        indexAllComponents();
        System.out.println("Sitemap created");
        return xmlUrlSet;
    }

    private XmlUrlSet readFile(String in) {
        // tokenize the string
        String[] tokens = in.split("<loc>");
        for (String s : tokens) {
            if (s.contains("http://www.sitemaps.org/schemas/sitemap/0.9"))
                continue;
            String[] tokens2 = s.split("</loc>");
            xmlUrlSet.addUrl(new XmlUrl(tokens2[0], XmlUrl.Priority.HIGH));
        }

        return xmlUrlSet;

    }

    private static void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl(myPage + link, priority));
    }

    /**
     * do the indexing of each component category and each of his children and add
     * them to the sitemap
     * 
     */
    private static void indexAllComponents() {
        List<String> componentCategories = getComponentCategory();

        for (String componentC : componentCategories) {
            create(xmlUrlSet, componentC, XmlUrl.Priority.HIGH);
            List<Component> components = Database.getInstance().getAllPublishedComponentsList(componentC);
            for (Component c : components)
                create(xmlUrlSet, componentC + "/" + c.getId(), XmlUrl.Priority.HIGH);
        }

    }

    /**
     * @return all the component categories' path (without /) in the database (e.g.
     *         "cpu-cooler")
     */
    private static List<String> getComponentCategory() {
        List<ComponentCategory> list = Database.getInstance().getComponentCategoryDAO().getAllComponentCategory();
        List<String> componentPath = new ArrayList<>();
        for (ComponentCategory cc : list)
            componentPath.add(cc.getPath().replace("/", ""));

        return componentPath;
    }

}