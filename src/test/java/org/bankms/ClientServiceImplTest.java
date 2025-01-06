package org.bankms;

import org.bankms.clientsms.dto.ClientDto;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.repository.ClientDetailsRepository;
import org.bankms.clientsms.repository.ClientRepository;
import org.bankms.clientsms.service.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientDetailsRepository clientDetailsRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    private ClientDetails clientDetails;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setClientId(1L);

        clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);
        clientDetails.setAnnualIncome(50000.0);
        clientDetails.setMonthlyInhandSalary(4000.0);
        clientDetails.setCreditHistoryAge(5L);
        clientDetails.setNumOfLoan(2L);
        clientDetails.setNumOfDelayedPayment(1L);
        clientDetails.setCreditUtilizationRatio(30.0);

        clientDto = new ClientDto();
    }

    @Test
    void testSaveClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.saveClient(client);

        assertNotNull(savedClient);
        assertEquals(1L, savedClient.getClientId());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testGetClients() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Client> clientPage = new PageImpl<>(Collections.singletonList(client));

        when(clientRepository.findAll(pageable)).thenReturn(clientPage);

        Page<Client> result = clientService.getClients(0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(clientRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClient(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFirstName());
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateClient() {
        when(clientRepository.findClientByClientId(1L)).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client updatedClient = clientService.updateClient(clientDto, 1L);

        assertNotNull(updatedClient);
        assertEquals("Jane Doe", updatedClient.getFirstName());
        verify(clientRepository, times(1)).findClientByClientId(1L);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testSaveClientDetails() {
        when(clientDetailsRepository.save(any(ClientDetails.class))).thenReturn(clientDetails);

        ClientDetails savedClientDetails = clientService.saveClientDetails(clientDetails);

        assertNotNull(savedClientDetails);
        assertEquals(1L, savedClientDetails.getClientId());
        verify(clientDetailsRepository, times(1)).save(clientDetails);
    }



    @Test
    void testGetNumberOfClients() {
        when(clientRepository.count()).thenReturn(10L);

        long count = clientService.getNumberOfClients();

        assertEquals(10L, count);
        verify(clientRepository, times(1)).count();
    }

    @Test
    void testGetNumberOfClientsWithDate() {
        LocalDateTime endDateTime = LocalDateTime.now();
        when(clientRepository.countByCreatedAtLessThanEqual(endDateTime)).thenReturn(5L);

        long count = clientService.getNumberOfClients(endDateTime);

        assertEquals(5L, count);
        verify(clientRepository, times(1)).countByCreatedAtLessThanEqual(endDateTime);
    }

    @Test
    void testGetClientsWithMatchingLoans() {
        when(clientDetailsRepository.findClientsWithMatchingLoans(
                clientDetails.getAutoLoan(),
                clientDetails.getCreditBuilderLoan(),
                clientDetails.getDebtConsolidationLoan(),
                clientDetails.getHomeEquityLoan(),
                clientDetails.getMortgageLoan(),
                clientDetails.getPersonalLoan(),
                clientDetails.getStudentLoan(),
                clientDetails.getPaydayLoan()
        )).thenReturn(Collections.singletonList(clientDetails));

        List<ClientDetails> result = clientService.getClientsWithMatchingLoans(clientDetails);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clientDetailsRepository, times(1)).findClientsWithMatchingLoans(
                clientDetails.getAutoLoan(),
                clientDetails.getCreditBuilderLoan(),
                clientDetails.getDebtConsolidationLoan(),
                clientDetails.getHomeEquityLoan(),
                clientDetails.getMortgageLoan(),
                clientDetails.getPersonalLoan(),
                clientDetails.getStudentLoan(),
                clientDetails.getPaydayLoan()
        );
    }


}