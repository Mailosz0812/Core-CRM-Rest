package org.mailosz.crmrest.crmclient;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.mailosz.crmrest.crmclient.request.ClientRequest;
import org.mailosz.crmrest.crmclient.response.ClientResponse;
import org.mailosz.crmrest.crmclient.response.ClientShortResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody @Valid ClientRequest req){
        ClientResponse resp = this.clientService.createClient(req);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(resp.getId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }
    @GetMapping("/{id}")
    public ClientResponse getClientById(@PathVariable @UUID String id){
        return this.clientService.getClient(id);
    }

    @GetMapping("/list")
    public List<ClientShortResponse> getClients(){
        return this.clientService.getAllClients();
    }
}
