package springcloudms.authservice.controller.out;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloudms.authservice.dto.account.request.AccountLoginRequestDTO;
import springcloudms.authservice.dto.account.response.AccountResponseDTO;
import springcloudms.authservice.service.AccountService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AccountController {

    private final AccountService accountService;

    @RequestMapping("/login")
    public ResponseEntity<AccountResponseDTO> login(@RequestBody AccountLoginRequestDTO loginRequestDTO) {

        if (accountService.findAccountByCredentials(loginRequestDTO).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(accountService.findAccountByCredentials(loginRequestDTO).get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }
}
