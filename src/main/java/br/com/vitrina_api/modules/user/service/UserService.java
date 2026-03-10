package br.com.vitrina_api.modules.user.service;

import br.com.vitrina_api.modules.user.model.User;
import br.com.vitrina_api.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User create(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public void delete(UUID publicId){
        User user = userRepository.findByPublicId(publicId).orElseThrow(() -> new RuntimeException("Usuario não localizado"));
        userRepository.delete(user);
    }
}
