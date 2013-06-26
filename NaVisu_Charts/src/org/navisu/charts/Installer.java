package org.navisu.charts;

import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    protected final String defaultChartsLocation = System.getProperty("user.dir") + "/NaVisu_Charts/charts";
    protected final String defaultTilesLocation = System.getProperty("user.dir") + "/NaVisu_Charts/WorldWindData";

    protected ChartsControllerServices chartsCtrl;
    
    public Installer() {
        this.chartsCtrl = ChartsControllerServices.lookup;
    }

    @Override
    public void restored() {
        
        this.chartsCtrl.addChartsLocation(defaultChartsLocation);
        this.chartsCtrl.getTilesFileStore().addTilesLocation(defaultTilesLocation);
        
        //this.chartsCtrl.addChartsPath(defaultChartsPath);
        //this.chartsCtrl.addTilesLocation(defaultDataFileStore);
        //this.chartsCtrl.reload();
    }
}
