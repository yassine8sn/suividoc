package ma.uit.doctorants.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TemplatesController {

    @RequestMapping("/view1")
    public String welcome() {
        return "home";
    }

    @RequestMapping(value = "/tmpls/{page}", method = RequestMethod.GET)
    public String templates(@PathVariable(value = "page") String page) {
        switch (page) {
            case "month":
                return "month";
            case "day":
                return "day";
            case "month-day":
                return "month-day";
            case "week":
                return "week";
            case "week-days":
                return "week-days";
            case "year":
                return "year";
            case "year-month":
                return "year-month";
            case "modal":
                return "modal";
            case "events-list":
                return "events-list";

        }
        return null;
    }
}
