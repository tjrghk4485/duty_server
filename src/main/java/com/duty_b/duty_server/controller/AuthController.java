package com.duty_b.duty_server.controller;

import com.duty_b.duty_server.service.JwtService;
import com.duty_b.duty_server.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private  KakaoAuthService kakaoAuthService;
    @Autowired
    private  JwtService jwtService;
    @CrossOrigin(origins = "http://localhost:3000") // CORS 허용
    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        System.out.println("code=" + code);
        Map<String, Object> map = kakaoAuthService.getKakaoUserInfo(code); // 🔥 카카오 사용자 정보 가져오기
        /*Long memberCode = kakaoAuthService.getOrCreateMember(map); // 🔥 회원 코드 매핑

        String token = jwtService.generateToken(memberCode); // 🔥 JWT 발급
*/
        Map<String, Object> response = new HashMap<>();
        response.put("token", map);


        return ResponseEntity.ok(response);
    }
}

