import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
public class GameState {
    private int currentPlayer;
    private Player[] players;
    private int cardCounter, endGame;
    private ArrayList<Route> routes;
    private HashMap<String, Integer> trainDeck;
    private HashMap<String, Integer> discardPile;
    private String[] currentCards;
    private ArrayList<DestinationTicket> shortRoutes, longRoutes;
    private Player longestPath;
    private ArrayList<City> cities;
    
    public GameState() {
    	 try {
             cities = readCities();
             createMap(cities);
             createTickets();
             System.out.println("Success");
         }
         catch(Exception e) {
             System.out.println("error");
         }
         createDeck();
         printDeck();
         currentCards = new String[5];
         for(int i = 0; i < 5; i++) {
             currentCards[i] = drawCard();
         }
         System.out.println(Arrays.toString(currentCards));
         cardCounter = 0;
         //playerTurn = 0;
         currentPlayer = 1;
         
    	
        players = new Player[4];
        for(int i = 0; i < 4; i++) {
            players[i] = new Player(i+1);
        }
        currentPlayer = 1;
    }
    
    public void incCurrentPlayer() {
    	if(currentPlayer < 4)
    	currentPlayer += 1; 
    	else
    		currentPlayer = 1;
    }
    
    public int getCurrentPlayer() {
    	return currentPlayer; 
    }
    
    public Player getPlayer(int n) {
    	return players[n];
    }
    
    public void createMap(ArrayList<City> cities) throws IOException {
        board = new HashMap<>();
        Scanner sc = new Scanner(new File("routes.txt"));
        ArrayList<Route> routes = new ArrayList<Route>();
        City city1, city2;
        while(sc.hasNextLine()){
          String[] info = sc.nextLine().split(" ");
          System.out.println(Arrays.toString(info));
          city1 = searchCity(info[0], cities);
          System.out.println(city1);
          city2 = searchCity(info[1], cities);
          routes.add(new Route(city1, city2, Integer.parseInt(info[2]), info[3], Boolean.parseBoolean(info[4]), Integer.parseInt(info[5])));
        }
        System.out.println("done");
        for(Route r : routes){
            //System.out.println("-");
            System.out.println(r);
        }
        
        for(City k : cities){
            ArrayList<Route> tempRouteList = new ArrayList<>();
            for(Route r : routes){
                if(r.getCity1().equals(k) || r.getCity2().equals(k)){
                    tempRouteList.add(r);
                }
            }
            board.put(k, tempRouteList);
        } 

        for(Entry<City, ArrayList<Route>> tentry : board.entrySet()){
            System.out.println("City: " + tentry.getKey() + " Routes " + tentry.getValue());
        }
        
    }

    public City searchCity(String city, ArrayList<City> cityList){
        for(City k : cityList){
            if(k.name().equals(city)){
                return k;
            }
        }
        return null;
    }

    public ArrayList<City> readCities() throws IOException{
        System.out.println("read cities");
        Scanner sc = new Scanner(new File("cities.txt"));
        ArrayList<City> cities = new ArrayList<>();
        while(sc.hasNextLine()){
            String[] info = sc.nextLine().split(" ");
            cities.add(new City(info[0]));
        }
        System.out.println(cities);
        return cities;
    }

    public void createTickets() throws IOException{
        discardPile = new ArrayList<>();
        longRoutes = new ArrayList<>();
        Scanner sc = new Scanner(new File("tickets.txt"));
        while(sc.hasNextLine()){
            String[] info = sc.nextLine().split(" ");
            City city1 = searchCity(info[0], cities);
            City city2 = searchCity(info[1], cities);
            DestinationTicket t = new DestinationTicket(city1, city2, Integer.parseInt(info[2]));
            if(t.getPoints() >= 20){
                longRoutes.add(t);
            }else{
                discardPile.add(t);
            }
        }
        //Collections.shuffle(ticketPile);
        Collections.shuffle(longRoutes);
    }

    public DestinationTicket drawTicket(){
        return discardPile.remove(0);
    }

    public Ticket getLongTicket(){
        return longRoutes.remove(0);
    }

    //end of game setup

    public boolean checkCompleted(DestinationTicket t, String p){
        System.out.println(t);
        City city1 = t.city1();
        City city2 = t.city2();
        ArrayList<City> visited = new ArrayList<>();
        return checkCompleted(city1, city2, p, visited) || checkCompleted(city2, city1, p, visited);
    }

    public boolean checkCompleted(City c1, City c2, String p, ArrayList<City> visited){
        ArrayList<Route> routeliest = board.get(c1);
        visited.add(c1);
        for(Route r : routeliest){
            if(r.boughtColor() != null && r.boughtColor().equals(p)){
                 if(r.getCity2().equals(c2) && !visited.contains(c2)){
                    return true;
                 }
                 return checkCompleted(r.getCity2(), c2, p, visited);
            }
            return false;
        }
        return false;
    }
    
    public void drawStartingTickets(){
        Scanner sc = new Scanner(System.in);
        ArrayList<DestinationTicket> temp = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            temp = new ArrayList<>();
            temp.add(getLongTicket());
            for(int j = 0; j < 3; j++){
                temp.add(drawTicket());
            }

            System.out.println("Drawn tickets " + temp);
            System.out.println("Select the indexes of up to 2 tickets to discard (type STOP to end)");
            String x = sc.nextLine();
            while(!x.equals("STOP") && temp.size() >= 4){
                int index = Integer.parseInt(x);
                temp.remove(index);
                System.out.println(temp);
                x = sc.nextLine();
            }
            for(DestinationTicket t : temp){
                players[i].addDestCard(t);
            }
        }

    }
   
    public boolean tryDrawCard(String choice){
        //choice will either be deck or faceup index
        if(cardCounter >= 2){
            return false;
        }
        if(choice.equals("deck")){
            String c = drawCard();
            players[currentPlayer].addCard(c);
            cardCounter++;
            if(cardCounter == 2){
                endTurn();
            }
            return true;
        }
        int index = Integer.parseInt(choice);
        String c = drawCard(index);
        if(c.equals("loco")){
            if(cardCounter == 0){
            players[currentPlayer].addCard(c);
            endTurn();
            return true;
            }
            return false;
        }
        players[currentPlayer].addCard(c);
        cardCounter++;
        if(cardCounter == 2){
            endTurn();
        }
        return true;
    }

    public void endTurn(){
        if(players[currentPlayer].trains() <= 2 || endgame != 0){
            endgame++;
        }
        if(endgame == 4){
            endGame();
        }
        currentPlayer = (currentPlayer+1)%4;
        cardCounter = 0;

    }

    public void printDeck(){
        for(Entry<String, Integer> e : trainDeck.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

//setting up game
    public void createDeck(){
        trainDeck = new HashMap<String, Integer>();
        discardPile = Helper.getEmptyDeck();
        for (String s : Helper.colors) {
            if (s.equals("loco")) {
                trainDeck.put(s, 14);
            } else {
                trainDeck.put(s, 12);
            }
        }
    }
    
    private String drawCard(){
        int count = 0;
        for (String s : Helper.colors) {
            count += trainDeck.get(s);
        }
        int drawn = (int) (count * Math.random());
        for (String s : Helper.colors) {
            drawn -= trainDeck.get(s);
            if (drawn < 0) {
                trainDeck.put(s, trainDeck.get(s) - 1);
                return s;
            }
        }
        return "no";        
    }

    public String drawCard(int i){
        String k = currentCards[i];
        currentCards[i] = drawCard();
        int lococounter = 0;
        for(String c : currentCards){
            if (c.equals("loco"))
            lococounter++;
        }
        if (lococounter >= 3){
            for(int j = 0; j < 5; j++){
                currentCards[j] = drawCard();
            }
        }
        return k;
    }
    
    public String[] getCards(){
        return currentCards;
    }
//buy routes
    public boolean buyRoute(String c1, String c2, ArrayList<String> cards){
        if(cards.isEmpty()){
            return false;
        }
        City city1 = searchCity(c1, cities);
        City city2 = searchCity(c2, cities);
        ArrayList<Route> routeslist = board.get(city1);
        System.out.println(routeslist);
        String cardcolor = "";
        for(String s:cards){
            System.out.println(s);
            if(!s.equals("loco")){
                cardcolor = s;
        }
    }
        if(cardcolor.equals(""))
        cardcolor = "loco";
        System.out.println("color " + cardcolor);
        Route purchaseRoute = null;
        for(Route r : routeslist){
            if(((r.getCity1().equals(city1) && r.getCity2().equals(city2))|| (r.getCity2().equals(city1) && r.getCity1().equals(city2))) && (r.color().equals("gray") || r.color().equals(cardcolor))){
                purchaseRoute = r;
            }
        }
        if(purchaseRoute == null){
            System.out.println("no route found");
            return false;
        }
        System.out.println(purchaseRoute);
         
        int locomotives = purchaseRoute.getLocomotives();
        int locomotivecards = 0;
        for(String x : cards){
            if(x.equals("loco"))
            locomotivecards++;
        }
        if(cards.size() < purchaseRoute.getLength()){
            return false;
        }
        if(!purchaseRoute.isTunnel() && locomotives == 0){
            purchaseRoute.buyRoute(players[currentPlayer].getColor());
            players[currentPlayer].addPoints(purchaseRoute.getPoints());
            //endTurn();
            return true;
        }
        if(locomotives != 0){
            if(locomotivecards >= locomotives){
                purchaseRoute.buyRoute(players[currentPlayer].getColor());
                players[currentPlayer].addPoints(purchaseRoute.getPoints());
                //endTurn();
                return true;
            }
            return false;
        }
        return buyTunnel(purchaseRoute, cardcolor);


    }

    public boolean buyTunnel(Route purchaseRoute, String color){
        ArrayList<String> extraCards = new ArrayList<>();
        int extra = 0;
        for(int i = 0; i < 3; i++){
            String c = drawCard();
            extraCards.add(c);
            if(c.equals(color)){
                extra++;
            }
        }
        if(extra == 0){
            purchaseRoute.buyRoute(players[currentPlayer].getColor());
            endTurn();
            return true;
        }
        //ask for extra cards
        return false;


    }

    public boolean placeStation(String s){
        City c = searchCity(s, cities);
        if(c.addStation(players[currentPlayer].getColor())){
            int price = 4 - players[currentPlayer].getStations();
            ArrayList<String> cards = new ArrayList<String>();
            if(cards.size() >= price){
                players[currentPlayer].useStation();
                endTurn();
                return true;
            }
        }
        return false;
    }

    public void drawTickets(){
        ArrayList<DestinationTicket> tlist = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            tlist.add(drawTicket());
        }


        endTurn();
    }
//end game
    public void endGame(){
        int s = 0;
        for(Player p : players){
            s += checkStations(p);
            s += checkCompleted(p);
            p.addPoints(s);
        }
        //  check european express
        // switch to end screen
    }

    public int checkStations(Player p){
        return 4 * p.getStations();
    }

    public int checkCompleted(Player p){
        return 0;
    }
}
