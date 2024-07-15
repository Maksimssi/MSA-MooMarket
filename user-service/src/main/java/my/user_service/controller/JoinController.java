package my.user_service.controller;

import my.user_service.dto.JoinDTO;
import my.user_service.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import my.user_service.service.JoinService;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

//    @PostMapping("/join")
//    public String joinProcess(JoinDTO joinDTO){
//
//        joinService.joinProcess(joinDTO);
//
//        return "ok";
//    }

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@RequestBody JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("user/findByUsername")
    public UserEntity findByUsername(@RequestParam String username) {
        return joinService.findByUsername(username);
    }
}
