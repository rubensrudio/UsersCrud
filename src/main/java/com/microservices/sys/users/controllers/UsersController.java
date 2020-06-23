package com.microservices.sys.users.controllers;

import java.util.Optional;

import com.microservices.sys.users.models.Users;
import com.microservices.sys.users.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("Users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {
    
    private final UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<?> listAll() {
        Iterable<Users> users = usersRepository.findAll();
        if(users.spliterator().getExactSizeIfKnown()<=0){
            return new ResponseEntity<>("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        Optional<Users> user = usersRepository.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody Users user){
        if(user.getEmail() != null && user.getPassword() != null && user.getName() != null){
            return new ResponseEntity<>(usersRepository.save(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if(usersRepository.existsById(id)){
            usersRepository.deleteById(id);
            return new ResponseEntity<>("Usuário excluído", HttpStatus.OK);
        }
        return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user) {
        if(usersRepository.existsById(user.getId())){
            return new ResponseEntity<>(usersRepository.save(user), HttpStatus.OK);
        }   
        return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }
}