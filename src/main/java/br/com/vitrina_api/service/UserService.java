package br.com.vitrina_api.service;

import br.com.vitrina_api.model.User;
import br.com.vitrina_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        return userRepository.save(user);
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não localizado"));
        userRepository.delete(user);
    }
}
