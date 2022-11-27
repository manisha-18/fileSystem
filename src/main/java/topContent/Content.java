package topContent;

public class Content {

    private int contentId;
    private int popularity;

    public Content(int contentId, int popularity) {
        this.contentId = contentId;
        this.popularity = popularity;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}

