package org.mailosz.crmrest.crmclient;

import jakarta.validation.Valid;
import org.mailosz.crmrest.crmclient.request.ClientRequest;
import org.mailosz.crmrest.crmclient.request.ClientUpdateReq;
import org.mailosz.crmrest.crmclient.response.ClientResponse;
import org.mailosz.crmrest.crmclient.response.ClientShortResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
    public ClientResponse getClientById(@PathVariable UUID id){
        return this.clientService.getClient(id);
    }

    @GetMapping("/list")
    public List<ClientShortResponse> getClients(){
        return this.clientService.getAllClients();
    }
    @PutMapping
    public ClientResponse updateClient(@RequestBody @Valid ClientUpdateReq req){
        return this.clientService.updateClient(req);
    }
}
