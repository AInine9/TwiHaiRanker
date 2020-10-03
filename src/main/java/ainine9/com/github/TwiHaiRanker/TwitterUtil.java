package ainine9.com.github.TwiHaiRanker;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterUtil {
    private int count = 0;

    public TwitterUtil(String ID) {
        Twitter twitter = new TwitterFactory().getInstance();

        try {
            count = twitter.showUser(ID).getStatusesCount();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return count;
    }
}
