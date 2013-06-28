package org.navisu.charts.tiles;

/**
 *
 * @author Thibault
 */
public interface TilesFileStoreEvents {

    void tilesFileStoreChanged();
    
    public interface TilesFileStoreEventsSubscribe {
        void subscribe  (TilesFileStoreEvents observer);
        void unsubscribe(TilesFileStoreEvents observer);
    }
}
