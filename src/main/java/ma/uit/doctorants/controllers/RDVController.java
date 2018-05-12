package ma.uit.doctorants.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class RDVController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/RDV/add", method = RequestMethod.POST)
    public void addRDV(@PathVariable(name = "time") String time) {
        System.out.println(time);
    }
}
