package com._hackaton._back.config.oauth;


import com._hackaton._back.config.auth.PrincipalDetails;
import com._hackaton._back.domain.User;
import com._hackaton._back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    
    //@Autowired
    //public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    //구글로부터 받은 userRequest 데이터에 대한 후처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //로그인버튼 -> 로그인 완료 -> code리턴 ->
        //프로필을 통한 회원가입 진행

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId(); //google
        String privderId = oAuth2User.getAttribute("sub");
        String userName = provider+"_"+privderId;
        String passWord = "rpt";//bCryptPasswordEncoder.encode("겟인데어");
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(userName);
        if(userEntity == null){
            userEntity = User.builder()
                    .username(userName)
                    .password(passWord)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(privderId)
                    .build();
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
