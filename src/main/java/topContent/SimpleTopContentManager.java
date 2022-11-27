package topContent;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SimpleTopContentManager implements TopContentService{
    private Map<Integer, Content> contentMap = new HashMap<>();
    private PriorityQueue<Content> queue = new PriorityQueue<>(Comparator.comparing(Content::getPopularity).reversed());

    @Override
    public int incrementPopularity(int contentId, int incrementBy) {
        if(contentMap.containsKey(contentId)){
            Content content = contentMap.get(contentId);
            queue.remove(content);
            content.setPopularity(content.getPopularity() + incrementBy);
            queue.offer(content);
        }
        else{
            Content content = new Content(contentId, incrementBy);
            contentMap.put(contentId, content);
            queue.offer(content);
        }
        return queue.isEmpty() ? -1 : queue.peek().getContentId();
    }

    @Override
    public int decrementPopularity(int contentId) {
        if(contentMap.containsKey(contentId)){
            Content content = contentMap.get(contentId);
            queue.remove(content);
            content.setPopularity(content.getPopularity() - 1);
            queue.offer(content);
        }
        return queue.isEmpty() ? -1 : queue.peek().getContentId();
    }

}
