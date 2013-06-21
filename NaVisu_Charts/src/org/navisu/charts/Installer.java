package org.navisu.charts;

import org.openide.modules.ModuleInstall;
import org.openide.util.Lookup;

public class Installer extends ModuleInstall {

    protected final String defaultChartsPath = System.getProperty("user.dir") + "/NaVisu_Charts/charts";
    protected final String defaultDataFileStore = System.getProperty("user.dir") + "/NaVisu_Charts/WorldWindData";

    protected ChartsControllerServices chartsCtrl;
    
    public Installer() {
        chartsCtrl = Lookup.getDefault().lookup(ChartsControllerServices.class);
    }

    @Override
    public void restored() {
        
        this.chartsCtrl.addChartsPath(defaultChartsPath);
        this.chartsCtrl.addFileStore(defaultDataFileStore);
        this.chartsCtrl.reload();
    }
}
