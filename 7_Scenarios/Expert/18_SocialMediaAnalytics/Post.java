import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post {
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#[\\p{L}0-9_]+");
    private static final Pattern MENTION_PATTERN = Pattern.compile("@[\\p{L}0-9_]+");
    private static int idGen = 1;

    private final int postId;
    private final String author;
    private final String content;
    private final LocalDateTime createdAt;
    private final List<String> hashtags;
    private final List<String> mentions;
    private int likes;
    private final List<String> comments;

    public Post(String author, String content) {
        this.postId = idGen++;
        this.author = author;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.hashtags = extractTags(HASHTAG_PATTERN, content);
        this.mentions = extractTags(MENTION_PATTERN, content);
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public int getPostId() { return postId; }
    public String getAuthor() { return author; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<String> getHashtags() { return hashtags; }
    public List<String> getMentions() { return mentions; }
    public int getLikes() { return likes; }
    public List<String> getComments() { return comments; }

    public void addLike() { likes++; }
    public void addComment(String comment) { comments.add(comment); }

    private List<String> extractTags(Pattern pattern, String text) {
        List<String> tags = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            tags.add(matcher.group());
        }
        return tags;
    }
}
