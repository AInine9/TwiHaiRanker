package ainine9.com.github.TwiHaiRanker;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

import java.util.Date;

public class TwitterUtil {
    private int count = 0;
    private Date date = null;

    public TwitterUtil(String ID) {
        Twitter twitter = new TwitterFactory().getInstance();
        User user = null;

        try {
            user = twitter.showUser(ID);
        } catch (TwitterException e) {
            return;
        }

        if (user != null) {
            count = user.getStatusesCount();
            date = user.getCreatedAt();
        }
    }

    public int getCount() {
        return count;
    }

    public Date getDate() {
        return date;
    }
}
