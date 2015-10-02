package enb329.soccerbot;

import java.io.Serializable;

/**
 * Created by jordanwortel on 20/08/15.
 */
public class OmniObject implements Serializable{

    String type;
    String info;
    double distance;
    double angle;
    boolean visible;


    public OmniObject()
    {
    }

    public OmniObject(String type, double distance, double angle, boolean visible, String info)
    {
        this.type = type;
        this.info = info;
        this.distance = distance;
        this.angle = angle;
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getAngle() {
        return angle;
    }

    public double getDistance() {
        return distance;
    }

    public String getType() {
        return type;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }


}
