package org.bankms.clientsms.mapper;

import org.bankms.clientsms.dto.ClientCsvRecord;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;

import static org.bankms.clientsms.mapper.ClientDetailsMapper.fromClientCsvRecordToClientDetails;


public class ClientMapper {

    public static Client fromClientCsvRecordToClient(ClientCsvRecord record) {
        // Create ClientDetails object from ClientCsvRecord
        ClientDetails details = fromClientCsvRecordToClientDetails(record);

        // Create Client object from ClientCsvRecord
        return Client.builder()
                .firstName(record.getFirstName())
                .lastName(record.getLastName())
                .email(record.getEmail())
                .phoneNumber(record.getPhoneNumber())
                .cin(record.getCin())
                .details(details) // Set the ClientDetails
                .build();
    }
}
