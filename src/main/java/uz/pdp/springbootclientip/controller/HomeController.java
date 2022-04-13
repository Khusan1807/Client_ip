package uz.pdp.springbootclientip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.springbootclientip.service.RequestService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private final RequestService requestService;

    public HomeController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        String clientIPAddress = requestService.getClientIPAddress(request);
        model.addAttribute("clientIPAddress", clientIPAddress);
        return "index";
    }
}
