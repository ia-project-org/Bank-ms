package org.bankms.batch_processing.clientsconfig;



import org.bankms.clientsms.dto.ClientCsvRecord;
import org.bankms.clientsms.model.Client1;
import org.bankms.clientsms.model.ClientDetails;
import org.springframework.batch.item.ItemProcessor;

import static org.bankms.clientsms.mapper.ClientDetailsMapper.fromClientCsvRecordToClientDetails;
import static org.bankms.clientsms.mapper.ClientMapper.fromClientCsvRecordToClient;

public class ClientProcessor implements ItemProcessor<ClientCsvRecord, Client1> {

    @Override
    public Client1 process(ClientCsvRecord clientCsvRecord) throws Exception {
        ClientDetails clientDetails = fromClientCsvRecordToClientDetails(clientCsvRecord);
        Client1 client = fromClientCsvRecordToClient(clientCsvRecord);
        client.setDetails(clientDetails);
        clientDetails.setClient(client);
        return client;
    }

}
