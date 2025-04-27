
public class DestinationTicket {
    private City startCity;
    private City endCity; 
    private int points; 
    private boolean isCompleted; 
    
    
    public DestinationTicket (City city1, City city2, int p){
        startCity = city1;
        endCity = city2;
        points = p;
        isCompleted = false;
    }

    public int getPoints(){
        return points;
    }
    public City city1(){
        return startCity;
    }
    public City city2(){
        return endCity;
    }
    public String toString() {
        return "Ticket from " + startCity + " to " + endCity + " " + points + " " + isCompleted;
    }
}
