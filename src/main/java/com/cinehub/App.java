package com.cinehub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class App {

   @GetMapping("/")
   public String home() {
       return "forward:/WEB-INF/welcom.jsp";
   }

}
