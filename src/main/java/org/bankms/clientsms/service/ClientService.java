package org.bankms.clientsms.service;

import org.bankms.clientsms.dto.ClientDto;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);

    Page<Client> getClients(int page, int size);

    Page<ClientDetails> getClientsDetails(int page, int size);

    Optional<Client> getClient(Long clientId);

    Client updateClient(ClientDto clientDto,Long clientId);

    ClientDetails updateClientDetails(ClientDetails clientDetails,Long clientId);

    ClientDetails saveClientDetails(ClientDetails clientDetails);

    ClientDetails getClientDetails(Long clientId);

    long getNumberOfClients();

    long getNumberOfClients(LocalDateTime endDateTime);

    public List<ClientDetails> getClientsWithMatchingLoans(ClientDetails clientDetails);
}
