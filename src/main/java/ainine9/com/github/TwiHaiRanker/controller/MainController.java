package ainine9.com.github.TwiHaiRanker.controller;

import ainine9.com.github.TwiHaiRanker.TwitterUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class MainController {
    public static final long oneDateTime = 1000 * 60 * 60 * 24;
    private String tier, ranking, tweetsPerDayString;

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/check_rank")
    public String checkRank(@RequestParam("ID") String ID, Model model) {
        TwitterUtil twitterUtil = new TwitterUtil(ID);

        if (twitterUtil.getCount() == 0 || twitterUtil.getDate() == null) {
            model.addAttribute("tier", "ユーザーが見つからないかツイートをしていません。");
            model.addAttribute("ranking", "ユーザーが見つからないかツイートをしていません。");
            return "check_rank";
        }

        getRank(twitterUtil);

        model.addAttribute("tier", tier);
        model.addAttribute("ranking", ranking);
        model.addAttribute("tweets_per_day", tweetsPerDayString);
        return "check_rank";
    }

    public void getRank(TwitterUtil twitterUtil) {

        BigDecimal count = new BigDecimal(twitterUtil.getCount());
        Date createdDate = twitterUtil.getDate();
        Date todaysDate = new Date();

        long createdTime = createdDate.getTime();
        long todaysTime = todaysDate.getTime();
        BigDecimal diffDays = new BigDecimal((todaysTime - createdTime) / oneDateTime);

        BigDecimal tweetsPerDay = count.divide(diffDays, 2, BigDecimal.ROUND_HALF_UP);

        tweetsPerDayString = tweetsPerDay.toPlainString();
        tier = tweetsPerDay.toPlainString();
        ranking = tweetsPerDay.toPlainString();
    }
}
