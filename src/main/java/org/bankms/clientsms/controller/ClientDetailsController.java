package org.bankms.clientsms.controller;

import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.config.ApplicationPropertiesConfiguration;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients/details")
@RequiredArgsConstructor
public class ClientDetailsController {

    private final ClientService clientService;

    private final ApplicationPropertiesConfiguration propertiesConfiguration;

    @GetMapping
    public ResponseEntity<?> getPaginatedClientsDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(clientService.getClientsDetails(page,size));
    }


    @PostMapping
    public ResponseEntity<?> saveClientDetails(@RequestBody ClientDetails clientDetails){
        return ResponseEntity.ok().body(clientService.saveClientDetails(clientDetails));
    }

    @GetMapping("/{clientId}")
    public ClientDetails getClientDetails(@PathVariable("clientId") Long clientId){
        return clientService.getClientDetails(clientId);
    }
}
