package com.example.demo.socket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socket")
public class SocketController {

	  @RequestMapping("/home")  
      public String index(){  
             return "sockethome";  
      }  
}
