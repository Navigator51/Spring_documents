package su.goodcat.spring_documents.controllers;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.goodcat.spring_documents.domain.User;
import su.goodcat.spring_documents.domain.dto.UserDTO;
import su.goodcat.spring_documents.domain.dto.UserSearchDTO;

import java.util.List;

@FeignClient(name = "spr-doc", url = "http://localhost:8081")
public interface UserFeignClient {

    @PostMapping("/api/v1/registry/controller")
    ResponseEntity<Void> saveNewUser(@RequestBody UserDTO userDTO);

    @GetMapping(path = "/api/v1/search/user")
    ResponseEntity<List<UserSearchDTO>> searchUserByQuery(@RequestParam String query);

    @GetMapping(path = "/api/v1/search/user/{name}")
    ResponseEntity<User> searchUserByName(@PathVariable String name);

}
