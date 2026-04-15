package com.mdk.springsecurity6study.service.impl.auth;

import com.mdk.springsecurity6study.common.entity.user.MdkUmcUser;
import com.mdk.springsecurity6study.service.api.auth.AuthService;
import com.mdk.springsecurity6study.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Object principal = authentication.getPrincipal();
        if (ObjectUtils.isNotEmpty(principal)) {
            if (principal instanceof MdkUmcUser mdkUmcUser) {
                log.info("用户登录成功: {}", mdkUmcUser);
                return jwtUtils.generateToken(mdkUmcUser.getUsername());
            }
        }
        return null;
    }
}
