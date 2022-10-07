package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import su.goodcat.spring.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    userRepository

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.getUserByLogin(username);
    }
}
