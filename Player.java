import java.util.*;

public class Player {
	
	private String name;
	//private DestinationTicket longDestCard;
	private ArrayList<DestinationTicket> destinationTickets;
	private HashMap<String, Integer> trainCards; 
	private int stations = 3;
	private int trains = 45;
	private int score;
	//private Set<Route> claimedRoutes;
	//private Set<String> claimedStations;
	//private boolean hasExpressCard;
	
	//other inventory attributes
	
	public Player(String c) {
		score = 0;
        trains = 45;
        stations = 3;
        destinationTickets = new ArrayList<>();
		trainCards = Helper.getEmptyDeck();
        name = c;
    }	
	public ArrayList<DestinationTicket> getTickets(){
        return destinationTickets;
    }
    public void addPoints(int x){
        score += x;
    }
	
	//destination cards
	
	public void addDestCard(DestinationTicket c) {
		destinationTickets.add(c);
	}
	public List<DestinationTicket> getDestCards(){
		return destinationTickets;
	}
	
	//train cards
	public void addCard(String c){
        trainCards.put(c, trainCards.get(c) + 1);
    }
    public void removeCard(String c){
        if(trainCards.get(c) > 0){
            trainCards.put(c, trainCards.get(c) - 1);
        }
        else{
            System.out.println("You don't have any " + c + " train cards left.");
        }
    }
	public int getTrainCardCount(String c) {
		return trainCards.get(c);
	}
	
	
	public int getStationsCount() {
		return stations;
	}
	public void useTrains(int count) {
		if(trains >= count) {
			trains -= count;
		}
	}
	public int getTrainsCount() {
		return trains;
	}
	
	public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public int getStations(){
        return stations;
    }
    public int numTrains(){
        return trains;
    }
    public String toString(){
        //gang, I kinda dont know to use this well, kinda need to pair this with StringBuffer but its fine ill write it later
        // StringBuilder sb = new StringBuilder();
        // sb.append("Player Color: " + name + "\n");
        // sb.append("Score: " + score + "\n");
        // sb.append("Number of Trains: " + trains + "\n");
        // sb.append("Number of Stations: " + stations + "\n");
        // sb.append("Cards: \n");

        // for(Map.Entry<String, Integer> entry : trainCards.entrySet()){
        //     sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        // }

        
        // return sb.toString();
        //honestly just use this for now so much simpler
        return name + " player " + trains + " trains " + stations + " stations " + destinationTickets + " " + cardtostring();
    }

    public String cardtostring(){
        String x = "";
        for(Map.Entry<String, Integer> entry : trainCards.entrySet()){
                x += entry.getKey() + ": " + entry.getValue() + "   ";
        }
        return x;
    }

}
