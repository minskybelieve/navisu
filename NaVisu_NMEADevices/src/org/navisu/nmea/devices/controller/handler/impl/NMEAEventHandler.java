/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.handler.impl;

import org.navisu.nmea.controller.parser.handler.Handler;
import org.navisu.nmea.devices.controller.services.AAMService;
import org.navisu.nmea.devices.controller.services.APBService;
import org.navisu.nmea.devices.controller.services.BECService;
import org.navisu.nmea.devices.controller.services.BODService;
import org.navisu.nmea.devices.controller.services.BWCService;
import org.navisu.nmea.devices.controller.services.BWRService;
import org.navisu.nmea.devices.controller.services.DBKService;
import org.navisu.nmea.devices.controller.services.DBSService;
import org.navisu.nmea.devices.controller.services.DBTService;
import org.navisu.nmea.devices.controller.services.DPTService;
import org.navisu.nmea.devices.controller.services.GGAService;
import org.navisu.nmea.devices.controller.services.GLLService;
import org.navisu.nmea.devices.controller.services.GSAService;
import org.navisu.nmea.devices.controller.services.GSVService;
import org.navisu.nmea.devices.controller.services.HDGService;
import org.navisu.nmea.devices.controller.services.HDMService;
import org.navisu.nmea.devices.controller.services.HDTService;
import org.navisu.nmea.devices.controller.services.MSKService;
import org.navisu.nmea.devices.controller.services.MTAService;
import org.navisu.nmea.devices.controller.services.MTWService;
import org.navisu.nmea.devices.controller.services.MWDService;
import org.navisu.nmea.devices.controller.services.MWVService;
import org.navisu.nmea.devices.controller.services.NMEAService;
import org.navisu.nmea.devices.controller.services.RMBService;
import org.navisu.nmea.devices.controller.services.RMCService;
import org.navisu.nmea.devices.controller.services.RMTService;
import org.navisu.nmea.devices.controller.services.RSDService;
import org.navisu.nmea.devices.controller.services.RTEService;
import org.navisu.nmea.devices.controller.services.VBWService;
import org.navisu.nmea.devices.controller.services.VHWService;
import org.navisu.nmea.devices.controller.services.VLWService;
import org.navisu.nmea.devices.controller.services.VPWService;
import org.navisu.nmea.devices.controller.services.VTGService;
import org.navisu.nmea.devices.controller.services.VWRService;
import org.navisu.nmea.devices.controller.services.VWTService;
import org.navisu.nmea.devices.controller.services.XTEService;
import org.navisu.nmea.devices.controller.services.ZDAService;
import org.navisu.nmea.model.AAM;
import org.navisu.nmea.model.APB;
import org.navisu.nmea.model.BEC;
import org.navisu.nmea.model.BOD;
import org.navisu.nmea.model.BWC;
import org.navisu.nmea.model.BWR;
import org.navisu.nmea.model.DBK;
import org.navisu.nmea.model.DBS;
import org.navisu.nmea.model.DBT;
import org.navisu.nmea.model.DPT;
import org.navisu.nmea.model.GGA;
import org.navisu.nmea.model.GLL;
import org.navisu.nmea.model.GSA;
import org.navisu.nmea.model.GSV;
import org.navisu.nmea.model.HDG;
import org.navisu.nmea.model.HDM;
import org.navisu.nmea.model.HDT;
import org.navisu.nmea.model.MSK;
import org.navisu.nmea.model.MTA;
import org.navisu.nmea.model.MTW;
import org.navisu.nmea.model.MWD;
import org.navisu.nmea.model.MWV;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.RMB;
import org.navisu.nmea.model.RMC;
import org.navisu.nmea.model.RMT;
import org.navisu.nmea.model.RSD;
import org.navisu.nmea.model.RTE;
import org.navisu.nmea.model.VBW;
import org.navisu.nmea.model.VHW;
import org.navisu.nmea.model.VLW;
import org.navisu.nmea.model.VPW;
import org.navisu.nmea.model.VTG;
import org.navisu.nmea.model.VWR;
import org.navisu.nmea.model.VWT;
import org.navisu.nmea.model.XTE;
import org.navisu.nmea.model.ZDA;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openide.util.Lookup;

/**
 *
 * @author Serge Morvan
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class NMEAEventHandler
        extends Handler {

    private Map<Class, Class> nmeaServices = null;
    private Map<Class, Collection< ? extends NMEAService>> nmeaServiceProviders = null;

    public NMEAEventHandler() {
        nmeaServices = new HashMap<>();
        nmeaServices.put(AAM.class, AAMService.class);
        nmeaServices.put(APB.class, APBService.class);
        nmeaServices.put(BEC.class, BECService.class);
        nmeaServices.put(BOD.class, BODService.class);
        nmeaServices.put(BWC.class, BWCService.class);
        nmeaServices.put(BWR.class, BWRService.class);
        nmeaServices.put(DBK.class, DBKService.class);
        nmeaServices.put(DBS.class, DBSService.class);
        nmeaServices.put(DBT.class, DBTService.class);
        nmeaServices.put(DPT.class, DPTService.class);
        nmeaServices.put(GGA.class, GGAService.class);
        nmeaServices.put(GLL.class, GLLService.class);
        nmeaServices.put(GSA.class, GSAService.class);
        nmeaServices.put(GSV.class, GSVService.class);
        nmeaServices.put(HDG.class, HDGService.class);
        nmeaServices.put(HDM.class, HDMService.class);
        nmeaServices.put(HDT.class, HDTService.class);
        nmeaServices.put(MSK.class, MSKService.class);
        nmeaServices.put(MTA.class, MTAService.class);
        nmeaServices.put(MTW.class, MTWService.class);
        nmeaServices.put(MWD.class, MWDService.class);
        nmeaServices.put(MWV.class, MWVService.class);
        nmeaServices.put(NMEA.class, NMEAService.class);
        nmeaServices.put(RMB.class, RMBService.class);
        nmeaServices.put(RMC.class, RMCService.class);
        nmeaServices.put(RMT.class, RMTService.class);
        nmeaServices.put(RSD.class, RSDService.class);
        nmeaServices.put(RTE.class, RTEService.class);
        nmeaServices.put(VBW.class, VBWService.class);
        nmeaServices.put(VHW.class, VHWService.class);
        nmeaServices.put(VLW.class, VLWService.class);
        nmeaServices.put(VPW.class, VPWService.class);
        nmeaServices.put(VTG.class, VTGService.class);
        nmeaServices.put(VWR.class, VWRService.class);
        nmeaServices.put(VWT.class, VWTService.class);
        nmeaServices.put(XTE.class, XTEService.class);
        nmeaServices.put(ZDA.class, ZDAService.class);

        nmeaServiceProviders = new HashMap<>();
        Set<Class> keySet = nmeaServices.keySet();
        for (Class<?> claze : keySet) {
            nmeaServiceProviders.put(claze, Lookup.getDefault().lookupAll(nmeaServices.get(claze)));
        }
    }

    @Override
    public <T extends NMEA> void doIt(T nmea) {
        Collection<? extends NMEAService> providers;
        providers = nmeaServiceProviders.get(nmea.getClass());
        for (NMEAService c : providers) {
            c.update(nmea); 
        }
    }
}
