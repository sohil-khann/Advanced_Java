public class Hashtag {
    private static int idGen = 1;
    private final int tagId;
    private final String tag;
    private int count;

    public Hashtag(String tag) {
        this.tagId = idGen++;
        this.tag = tag.toLowerCase();
        this.count = 1;
    }

    public int getTagId() { return tagId; }
    public String getTag() { return tag; }
    public int getCount() { return count; }
    public void increment() { count++; }
}
