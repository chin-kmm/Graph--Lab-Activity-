import java.util.*;

public class TreasureHuntGame {
    private static class Graph {
        private Map<String, List<String>> adjacencyList;

        public Graph() {
            adjacencyList = new HashMap<>();
        }

        public void addNode(String node) {
            adjacencyList.putIfAbsent(node, new ArrayList<>());
        }

        public void addEdge(String from, String to) {
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }

        public List<String> getNeighbors(String node) {
            return adjacencyList.getOrDefault(node, new ArrayList<>());
        }

        public boolean containsNode(String node) {
            return adjacencyList.containsKey(node);
        }
    }

    private Graph map;
    private String treasureLocation;
    private String playerLocation;

    public TreasureHuntGame() {
        map = new Graph();
    }

    // Initialize the game
    public void initializeGame() {
        // Create locations (nodes)
        map.addNode("Start");
        map.addNode("Forest");
        map.addNode("Cave");
        map.addNode("River");
        map.addNode("Mountain");
        map.addNode("Treasure");

        // Create paths (edges)
        map.addEdge("Start", "Forest");
        map.addEdge("Forest", "Cave");
        map.addEdge("Forest", "River");
        map.addEdge("Cave", "Mountain");
        map.addEdge("Mountain", "Treasure");

        // Set initial positions
        playerLocation = "Start";
        treasureLocation = "Treasure";
    }

    // Play the game
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Treasure Hunt Game!");
        System.out.println("Find your way through the locations to discover the treasure.");

        while (true) {
            System.out.println("\nYou are currently at: " + playerLocation);
            if (playerLocation.equals(treasureLocation)) {
                System.out.println("Congratulations! You found the treasure!");
                break;
            }

            System.out.println("Available paths: " + map.getNeighbors(playerLocation));
            System.out.print("Enter your next location: ");
            String nextLocation = scanner.nextLine();

            if (map.getNeighbors(playerLocation).contains(nextLocation)) {
                playerLocation = nextLocation;
            } else {
                System.out.println("Invalid location! Try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TreasureHuntGame game = new TreasureHuntGame();
        game.initializeGame();
        game.play();
    }
}