package org.bankms.clientsms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ClientDetailsDto {
    private String situationProfessionnelle;
    private String situationFamiliale;
    private int nbEnfants;
    private double revenuAnnuel;
    private double salary;
}
