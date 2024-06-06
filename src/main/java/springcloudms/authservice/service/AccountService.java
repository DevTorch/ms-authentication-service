package springcloudms.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcloudms.authservice.dto.account.request.AccountLoginRequestDTO;
import springcloudms.authservice.dto.account.response.AccountResponseDTO;
import springcloudms.authservice.model.RoleNameEnum;
import springcloudms.authservice.repository.AccountRepository;
import springcloudms.authservice.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Boolean isAccountExists(Long accountId) {
        return accountRepository.findById(accountId).isPresent() ? Boolean.TRUE : Boolean.FALSE;
    }

    public Optional<AccountResponseDTO> getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .map(account -> AccountResponseDTO.builder()
                        .id(account.getId())
                        .email(account.getEmail())
                        .roles(account.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                        .isActive(account.getActive())
                        .build());
    }

    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> AccountResponseDTO.builder()
                        .id(account.getId())
                        .email(account.getEmail())
                        .roles(account.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                        .isActive(account.getActive())
                        .build())
                .toList();
    }

    public Optional<AccountResponseDTO> findAccountByCredentials(AccountLoginRequestDTO loginRequestDTO) {

        //TODO: Переделать: сначала проверяем почту, потом по паролю (а далее encoder.match)

        return accountRepository.findByEmailAndPassword(loginRequestDTO.email(), loginRequestDTO.password())
                .map(account -> {
                    Set<RoleNameEnum> roles = account.getRoles().stream()
                            .map(Role::getName)
                            .collect(Collectors.toSet());
                    return AccountResponseDTO.builder()
                            .id(account.getId())
                            .email(account.getEmail())
                            .roles(roles)
                            .isActive(account.getActive())
                            .build();
                });
    }
}
