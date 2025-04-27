import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

public class Route {
    private City c1, c2; 
    private int length; 
    private int locomotivesCount; // Required locomotives, this value will only be greater than 0 if route is a ferry 
    private int points;
    private boolean isStation; 
    private boolean isTunnel;
    public String color, boughtColor //boughtColor is the color fo the player when a player buys the route

    public Route(City city1, City city2, int len, String c, boolean t, int loco) {
        //des = d;
        isStation = false;
        c1 = city1;
        c2 = city2;
        length = len;
        color = c;
        isTunnel = t;
        locomotives = loco;
        boughtColor = null;
        switch (length) {
            case 1:
                points = 1;
                break;
            case 2:
                points = 2;
                break;
            case 3:
                points = 4;
                break;
            case 4:
                points = 7;
                break;
            case 6:
                points = 15;
                break;
            case 8:
                points = 21;
        }
    }

    public City getCity1(){
        return c1;
    }

    public City getCity2(){
        return c2;
    }

    public int getLength(){
        return length;
    }
    
    public int getPoints(){
        return points;
    }

    public int getLocomotives(){
        return locomotivesCount;
    }

    public String color(){
        return color;
    }

    public String boughtColor(){
        return boughtColor;
    }
    public void setStation(String c){
        isStation = true;
    }

    public void buyRoute(String c){
        boughtColor = c;
    }

    public boolean isTunnel(){
        return isTunnel;
    }
    public boolean isStation(){
        return isStation;
    }

    public String toString(){
        return "Connects " +  c1.name() + " to " + c2.name() + " " + color + " " + length + " " + isTunnel + " " + locomotivesCount + " bought color " + boughtColor;
    }

}