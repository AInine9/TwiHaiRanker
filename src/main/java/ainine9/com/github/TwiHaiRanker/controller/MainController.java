package ainine9.com.github.TwiHaiRanker.controller;

import ainine9.com.github.TwiHaiRanker.TwitterUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/check_rank")
    public String checkRank(@RequestParam("ID") String ID, Model model) throws Exception {
        String tier, ranking;

        TwitterUtil twitterUtil = new TwitterUtil(ID);
        String count = twitterUtil.getCount();

        tier = count;
        ranking = count;

        model.addAttribute("tier", tier);
        model.addAttribute("ranking", ranking);
        return "check_rank";
    }
}
