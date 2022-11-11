package su.goodcat.spring_documents.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import su.goodcat.commonlib.feign.UserFeignClient;

@Service
@Slf4j

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    public void setUserFeignClient(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    private  UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String name) {
        log.debug("Обращение userDetails для получения пользователя");
        return userFeignClient.searchUserByName(name).getBody();
    }
}
