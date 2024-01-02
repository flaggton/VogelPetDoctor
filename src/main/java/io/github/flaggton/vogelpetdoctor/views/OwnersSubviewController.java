package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtilException;
import io.github.flaggton.vogelpetdoctor.data.Owner;
import org.h2.server.web.WebServer;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;

public class OwnersSubviewController {
    public void init() {
        Owner o = new Owner(null, "David", "WebServer", "vgnrejknaek@rever.de", LocalDate.of(1970, 1, 1));
        try {
            HibernateQueryUtil.Inserter.insertOne(o);
        } catch (HibernateQueryUtilException e) {
            throw new RuntimeException(e);
        }
    }
}
