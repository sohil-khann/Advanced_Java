import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SocialMediaAnalytics {
    private static final List<Post> posts = new ArrayList<>();
    private static final Map<String, Hashtag> hashtagMap = new HashMap<>();

    public static void main(String[] args) {
        Post p1 = new Post("priya", "Loving the #java #coding vibe today!");
        Post p2 = new Post("rahul", "Check out #java and #springboot tutorials");
        Post p3 = new Post("arjun", "Bad day #sad #coding");

        posts.addAll(Arrays.asList(p1, p2, p3));
        updateHashtags();

        System.out.println("Trending hashtags: " + getTrendingHashtags(2));
        System.out.println("Total engagement: " + getTotalEngagement());
        System.out.println("Invalid post check: " + validatePost(p1));
    }

    public static void updateHashtags() {
        posts.stream().flatMap(p -> p.getHashtags().stream()).forEach(tag -> {
            hashtagMap.computeIfAbsent(tag, Hashtag::new).increment();
        });
    }

    public static List<Map.Entry<String, Integer>> getTrendingHashtags(int n) {
        return hashtagMap.entrySet().stream()
            .sorted(Map.Entry.<String, Hashtag>comparingByValue((a, b) -> Integer.compare(b.getCount(), a.getCount())))
            .limit(n)
            .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().getCount()))
            .collect(Collectors.toList());
    }

    public static long getTotalEngagement() {
        return posts.stream().mapToLong(p -> p.getLikes() + p.getComments().size()).sum();
    }

    public static boolean validatePost(Post post) {
        if (post.getContent() == null || post.getContent().trim().isEmpty()) return false;
        if (post.getAuthor() == null || post.getAuthor().trim().isEmpty()) return false;
        return true;
    }
}
