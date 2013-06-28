package org.navisu.core;

import java.util.Collection;
import java.util.Iterator;
import org.openide.LifecycleManager;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Thibault
 */
@ServiceProvider(service=LifecycleManager.class, position=1)
public class NaVisuLifecycleManager extends LifecycleManager {

    @Override
    public void exit() {
        // save the WorldWind session state
        WorldWindManagerServices worldWindManager = WorldWindManagerServices.lookup;
        worldWindManager.saveSessionState();

        // call others LifecycleManager to exit application
        Collection<?> c = Lookup.getDefault().lookupAll(LifecycleManager.class);
        for (Iterator<?> i = c.iterator(); i.hasNext();) {
            LifecycleManager lm = (LifecycleManager) i.next();
            if (lm != this) {
                lm.exit();
            }
        }
    }

    @Override
    public void saveAll() {
        // nothing to do here
    }
}
