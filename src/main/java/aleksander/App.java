package aleksander;


import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;
import org.eclipse.jetty.annotations.AnnotationConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    public static void main(String[] args) throws Exception {

        var webapp = new WebAppContext();
        webapp.setContextPath("\\");
        webapp.setResourceBase("src\\main\\webapp\\WEB-INF\\web.xml");
        webapp.setConfigurations(new Configuration[] {
                        new AnnotationConfiguration(),
                        new WebInfConfiguration(),
                        new WebXmlConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration(),
                        new PlusConfiguration(),
                        new JettyWebXmlConfiguration()
                });

        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
        //webapp.addServlet(HelloServ0let.class,"/api/*")
        var server = new org.eclipse.jetty.server.Server(8080);
        server.setHandler(webapp);

        server.start();
        server.join();

    }
}
