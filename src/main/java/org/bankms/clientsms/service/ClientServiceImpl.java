package org.bankms.clientsms.service;

import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.dto.ClientDto;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.repository.ClientDetailsRepository;
import org.bankms.clientsms.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import static java.text.Normalizer.normalize;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    private final ClientDetailsRepository clientDetailsRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Page<Client> getClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<ClientDetails> getClientsDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientDetailsRepository.findAll(pageable);
    }

    @Override
    public Optional<Client> getClient(Long clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public Client updateClient(ClientDto clientDto,Long clientId) {
        Client client = clientRepository.findClientByClientId(clientId);
        if (client != null)
            clientRepository.save(client);
        return client;
    }

    @Override
    public ClientDetails updateClientDetails(ClientDetails clientDetails,Long clientId) {
        ClientDetails clientDetails1 = clientDetailsRepository.findClientDetailsByClientId(clientId);
        return clientDetailsRepository.save(clientDetails);
    }

    @Override
    public ClientDetails saveClientDetails(ClientDetails clientDetails) {
        return clientDetailsRepository.save(clientDetails);
    }

    @Override
    public ClientDetails getClientDetails(Long clientId) {
        ClientDetails clientDetails = clientDetailsRepository.findClientDetailsByClientId(clientId);
        clientDetails.setScore(getClientRankPercentage(clientDetails));
        return clientDetails;
    }

    @Override
    public long getNumberOfClients() {
        return clientRepository.count();
    }

    @Override
    public long getNumberOfClients(LocalDateTime endDateTime) {
        return clientRepository.countByCreatedAtLessThanEqual(endDateTime);
    }

    @Override
    public List<ClientDetails> getClientsWithMatchingLoans(ClientDetails clientDetails) {
        return clientDetailsRepository.findClientsWithMatchingLoans(
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

    /**
     * Calculates a weighted creditworthiness score for a client based on normalized attributes.
     *
     * @param client  The client for whom the score is being calculated.
     * @param clients The list of all clients used for normalization.
     * @return The weighted score for the client.
     */
    public static double calculateScore(ClientDetails client, List<ClientDetails> clients) {
        // Normalize attributes using the provided client list
        double normalizedAge = normalize(clients, client.getAge(), ClientDetails::getAge);
        double normalizedIncome = normalize(clients, client.getAnnualIncome(), ClientDetails::getAnnualIncome);
        double normalizedSalary = normalize(clients, client.getMonthlyInhandSalary(), ClientDetails::getMonthlyInhandSalary);
        double normalizedHistory = normalize(clients, client.getCreditHistoryAge(), ClientDetails::getCreditHistoryAge);
        double normalizedLoans = normalize(clients, client.getNumOfLoan(), ClientDetails::getNumOfLoan, true);
        double normalizedDelays = normalize(clients, client.getNumOfDelayedPayment(), ClientDetails::getNumOfDelayedPayment, true);
        double normalizedUtilization = normalize(clients, client.getCreditUtilizationRatio(), ClientDetails::getCreditUtilizationRatio, true);

        // Calculate the weighted score with specified weights
        return (0.2 * normalizedAge)
                + (0.3 * normalizedIncome)
                + (0.2 * normalizedSalary)
                + (0.2 * normalizedHistory)
                - (0.1 * normalizedLoans)
                - (0.1 * normalizedDelays)
                - (0.1 * normalizedUtilization);
    }

    /**
     * Calculates the client's rank as a percentage among clients with similar loan types.
     *
     * @param targetClient The client whose rank percentage is being calculated.
     * @return The rank percentage of the target client.
     */
    public double getClientRankPercentage(ClientDetails targetClient) {
        // Filter clients with the same loan types
        List<ClientDetails> filteredClients = getClientsWithMatchingLoans(targetClient);

        // Handle edge case where no similar clients are found
        if (filteredClients.isEmpty()) {
            throw new IllegalArgumentException("No clients with the same loan types.");
        }

        // Calculate scores for all filtered clients
        List<Double> scores = filteredClients.stream()
                .map(client -> calculateScore(client, filteredClients))
                .sorted()
                .collect(Collectors.toList());

        // Calculate the target client's score
        double targetScore = calculateScore(targetClient, filteredClients);

        // Count scores below the target client's score
        long countBelow = scores.stream().filter(score -> score < targetScore).count();

        // Calculate and return the rank percentage
        return (double) countBelow / scores.size()*100;
    }

    private static double normalize(List<ClientDetails> clients, double value, ToDoubleFunction<ClientDetails> extractor) {
        return normalize(clients, value, extractor, false);
    }

    private static double normalize(List<ClientDetails> clients, double value, ToDoubleFunction<ClientDetails> extractor, boolean inverse) {
        double min = clients.stream().mapToDouble(extractor).min().orElse(0);
        double max = clients.stream().mapToDouble(extractor).max().orElse(1);
        double normalized = (value - min) / (max - min);
        return inverse ? 1 - normalized : normalized;
    }

}


