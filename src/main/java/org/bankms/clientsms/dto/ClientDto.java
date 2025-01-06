package org.bankms.clientsms.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientDto {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String cin;
}
