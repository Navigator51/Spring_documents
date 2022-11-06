package su.goodcat.spring_documents.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import su.goodcat.commonlib.feign.UserFeignClient;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    public void setUserFeignClient(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    private  UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String name) {
        return userFeignClient.searchUserByName(name).getBody();
    }
}
