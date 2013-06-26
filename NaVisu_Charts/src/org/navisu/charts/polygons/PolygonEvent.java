package org.navisu.charts.polygons;

/**
 *
 * @author Thibault
 */
public interface PolygonEvent {

    void selected(Polygon polygon);
    
    public interface PolygonEventSubscribe {
        
        void subscribe  (PolygonEvent observer);
        void unsubscribe(PolygonEvent observer);
    }
}
