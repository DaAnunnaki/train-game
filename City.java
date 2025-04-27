public class City {
    private String name;
    private String station; //checks if there is already a station on the city
    
    public City(String n){
        name = n;
        station = null;
    }

    public String name(){
        return name;
    }
    
    public String toString(){
        return name;
    }
    public boolean addStation(String s){
        if(station == null){
            station = s;
            return true;
        }
        return false;
    }

    public String getStation(){
        return station;
    }
}