package org.navisu.simulator.waypointitem;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WayPointItemCtrl {

    protected WayPointItemView view;
    
    protected int id;
    protected Calendar time;
    protected double latitudeInDeg;
    protected double longitudeInDeg;

    protected DateFormat utcDateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
    protected DecimalFormat latLonFormat = new DecimalFormat("#0.0000°");
    
    public WayPointItemCtrl(int id, long time, double latitudeInDeg, double longitudeInDeg) {
        this.view = new WayPointItemView();
        
        this.id = id;
        this.view.idLabel.setText(id + ")");
        
        this.time = new GregorianCalendar();
        this.time.setTimeInMillis(time);
        this.view.utcLabel.setText(this.utcDateFormat.format(this.time.getTime()));
        
        this.latitudeInDeg = latitudeInDeg;
        this.longitudeInDeg = longitudeInDeg;
        this.view.latLonLabel.setText(this.latLonFormat.format(this.latitudeInDeg) + "/" + this.latLonFormat.format(this.longitudeInDeg));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.view.idLabel.setText(id + ")");
    }

    public long getTime() {
        return time.getTimeInMillis();
    }

    public void setTime(long time) {
        this.time.setTimeInMillis(time);
        this.view.utcLabel.setText(this.utcDateFormat.format(this.time.getTime()));
    }

    public double getLatitudeInDeg() {
        return latitudeInDeg;
    }

    public void setLatitudeInDeg(double latitudeInDeg) {
        this.latitudeInDeg = latitudeInDeg;
        this.view.latLonLabel.setText(this.latLonFormat.format(this.latitudeInDeg) + "/" + this.latLonFormat.format(this.longitudeInDeg));
    }

    public double getLongitudeInDeg() {
        return longitudeInDeg;
    }

    public void setLongitudeInDeg(double longitudeInDeg) {
        this.longitudeInDeg = longitudeInDeg;
        this.view.latLonLabel.setText(this.latLonFormat.format(this.latitudeInDeg) + "/" + this.latLonFormat.format(this.longitudeInDeg));
    }
    
    public WayPointItemView getView() {
        return view;
    }
}
