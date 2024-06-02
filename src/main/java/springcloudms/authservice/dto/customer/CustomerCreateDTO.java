package springcloudms.authservice.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerCreateDTO(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String fullName,
        @NotBlank String nickname,
        @NotBlank String about,
        @NotBlank String city
) {
}
