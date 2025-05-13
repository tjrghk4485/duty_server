package com.duty_b.duty_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KakaoAuthService {


    private final RestTemplate restTemplate;

    private final NurseService nurseService;

    private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final String CLIENT_ID = "b656293336f5e166383d543eb8f22357";
    private final String REDIRECT_URI = "http://localhost:3000/auth/kakao";

    @Autowired
    public KakaoAuthService(RestTemplate restTemplate, NurseService nurseService) {
        this.restTemplate = restTemplate;
        this.nurseService = nurseService;
    }






    // ✅ 1. 카카오 사용자 정보 가져오기
    public Map<String, Object> getKakaoUserInfo(String code) {
        String accessToken = getAccessToken(code);
        Map<String, Object> returnMap = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(KAKAO_USER_INFO_URL, HttpMethod.GET, entity, Map.class);
        System.out.println("response=" + response.toString());
        Map<String, Object> userInfo = response.getBody();
        Long kakaoId = ((Number) userInfo.get("id")).longValue();
        Map<String, Object> propertiesMap = (Map<String, Object>) userInfo.get("properties");
        String nickname = (String) propertiesMap.get("nickname");
        String profile_image = (String) propertiesMap.get("profile_image");
        returnMap.put("kakaoId", kakaoId);
        String nurseUserId = getOrCreateMember(kakaoId,returnMap);
        returnMap.put("userId", nurseUserId);
        returnMap.put("nickname", nickname);
        returnMap.put("profile_image", profile_image);
        return returnMap;
    }

    // ✅ 2. 내 DB에 회원 코드 찾기 (없으면 새로 만들기)
    public String getOrCreateMember(Long id,Map<String, Object> map) {
        List<Map<String, Object>> userChk = nurseService.select("nurseDto.kakao_user_chk",id);
        System.out.println("userChk=" + userChk);
        if(!userChk.isEmpty()){
            System.out.println("userChk2=" + userChk);
            return (String) userChk.get(0).get("id");
        }else{
            nurseService.modify("nurseDto.kakao_user_mod", map);
             userChk = nurseService.select("nurseDto.kakao_user_chk",id);
            System.out.println("userChk3=" + userChk.get(0));
            System.out.println("userChk4=" + userChk);
             return (String) userChk.get(0).get("id");
        }

    }

    // ✅ 카카오 API에서 `access_token` 가져오기
    private String getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);
        //params.add("client_secret", CLIENT_SECRET);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(KAKAO_TOKEN_URL, request, Map.class);

        return (String) response.getBody().get("access_token");
    }
}

