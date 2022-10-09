package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import su.goodcat.spring_documents.controllers.UserFeignClient;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


   private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String name) {
        return userFeignClient.searchUserByName(name).getBody();
    }
}
