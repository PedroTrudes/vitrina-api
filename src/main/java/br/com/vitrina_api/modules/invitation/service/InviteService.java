package br.com.vitrina_api.modules.invitation.service;

import br.com.vitrina_api.modules.auth.dto.RegisterDTO;
import br.com.vitrina_api.modules.invitation.model.Invite;
import br.com.vitrina_api.modules.invitation.repository.InviteRepository;
import br.com.vitrina_api.modules.store.model.Store;
import br.com.vitrina_api.modules.store.repository.StoreRepository;
import br.com.vitrina_api.modules.user.model.User;
import br.com.vitrina_api.modules.user.model.UserRole;
import br.com.vitrina_api.modules.user.repository.UserRepository;
import br.com.vitrina_api.shared.exception.BusinessException;
import br.com.vitrina_api.shared.exception.ErrorCode;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InviteService {

    private final InviteRepository inviteRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InviteService(InviteRepository inviteRepository, StoreRepository storeRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.inviteRepository = inviteRepository;
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User getAuthenticatedUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("Usuario não existe", ErrorCode.INVITE_NOT_FOUND));
    }

    private String generateUniqueToken(){
        String token;
        do {
            token = "INV-" + UUID.randomUUID().toString().substring(0,6).toUpperCase();
        } while (inviteRepository.existsByToken(token));
        return token;
    }

    @Transactional
    public Invite create(UUID storePublicId){
        Store store = storeRepository.findByPublicId(storePublicId).orElseThrow(() -> new BusinessException("Loja não encontrada", ErrorCode.INVITE_NOT_FOUND));

        User user = getAuthenticatedUser();

        Invite invite = Invite.builder().store(store).createdBy(user).token(generateUniqueToken()).build();

        return inviteRepository.save(invite);
    }


    public Invite validateInvite(String token){
        Invite invite = inviteRepository.findByToken(token).orElseThrow(() -> new BusinessException("Convite não encontrado", ErrorCode.INVITE_NOT_FOUND));

        if(!invite.getActive()){
            throw new BusinessException("Convite inativo", ErrorCode.INVITE_EXPIRED);
        }

        if(invite.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new BusinessException("Convite expirado", ErrorCode.INVITE_EXPIRED);
        }

        return invite;
    }

    @Transactional
    public User registerWithInvite(RegisterDTO dto, String token){
        Invite invite = validateInvite(token);

        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(UserRole.MEMBER)
                .store(invite.getStore())
                .build();

        return userRepository.save(user);
    }

}
