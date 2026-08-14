import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Leaderboard system using TreeSet and custom comparators.
 */
class LeaderboardSystem {
    private TreeSet<Player> players;

    /**
     * Constructs an empty leaderboard.
     */
    public LeaderboardSystem() {
        this.players = new TreeSet<>(Comparator
                .comparingInt(Player::getScore).reversed()
                .thenComparing(Player::getName));
    }

    /**
     * Adds or updates a player score.
     */
    public void addScore(String name, int score) {
        Player existing = findPlayer(name);
        if (existing != null) {
            players.remove(existing);
            existing.setScore(score);
            players.add(existing);
        } else {
            players.add(new Player(name, score));
        }
    }

    /**
     * Retrieves the top N players.
     */
    public List<Player> getTopN(int n) {
        List<Player> top = new ArrayList<>();
        int count = 0;
        for (Player p : players) {
            top.add(p);
            count++;
            if (count >= n) break;
        }
        return top;
    }

    /**
     * Looks up a player by name.
     */
    public Player findPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Removes a player by name.
     */
    public boolean removePlayer(String name) {
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            Player p = iterator.next();
            if (p.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all players in the leaderboard.
     */
    public List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * Demonstrates the Leaderboard System.
     */
    public static void main(String[] args) {
        LeaderboardSystem leaderboard = new LeaderboardSystem();

        System.out.println("=== Adding Players ===");
        leaderboard.addScore("Priya", 1500);
        leaderboard.addScore("Rahul", 2200);
        leaderboard.addScore("Arjun", 1800);
        leaderboard.addScore("Anjali", 2200);
        leaderboard.addScore("Isha", 950);

        System.out.println("\n=== Top 3 Players ===");
        for (Player p : leaderboard.getTopN(3)) {
            System.out.println(p);
        }

        System.out.println("\n=== All Players ===");
        for (Player p : leaderboard.getAllPlayers()) {
            System.out.println(p);
        }

        System.out.println("\n=== Lookup Priya ===");
        Player priya = leaderboard.findPlayer("Priya");
        if (priya != null) {
            System.out.println("Found: " + priya);
        }

        System.out.println("\n=== Updating Rahul Score ===");
        leaderboard.addScore("Rahul", 2500);
        System.out.println("Top 2 after update:");
        for (Player p : leaderboard.getTopN(2)) {
            System.out.println(p);
        }

        System.out.println("\n=== Removing Isha ===");
        leaderboard.removePlayer("Isha");
        System.out.println("Remaining players: " + leaderboard.getAllPlayers().size());
    }

    /**
     * Player class with name and score.
     */
    public static class Player {
        private String name;
        private int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }

        @Override
        public String toString() {
            return String.format("Player{name='%s', score=%d}", name, score);
        }

        // For TreeSet operations - equals and hashCode based on name only
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player player = (Player) o;
            return name.equalsIgnoreCase(player.name);
        }

        @Override
        public int hashCode() {
            return name.toLowerCase().hashCode();
        }
    }
}
