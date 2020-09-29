package ainine9.com.github.TwiHaiRanker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/check_rank")
    public String checkRank(@RequestParam("ID") String ID, Model model) {
        String tier, ranking;
        tier = ID;
        ranking = ID;
        model.addAttribute("tier", tier);
        model.addAttribute("ranking", ranking);
        return "check_rank";
    }
}
