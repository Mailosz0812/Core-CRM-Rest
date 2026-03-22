package org.mailosz.crmrest.crmclient;

import org.mailosz.crmrest.crmclient.request.ClientRequest;
import org.mailosz.crmrest.crmclient.response.ClientResponse;
import org.mailosz.crmrest.crmclient.response.ClientShortResponse;
import org.mailosz.crmrest.exception.types.CrmClientAlreadyExistsException;
import org.mailosz.crmrest.exception.types.CrmClientNotFoundException;
import org.mailosz.crmrest.helpers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepo;
    private final Mapper<CrmClientEntity, ClientRequest> clientReqMapper;
    private final Mapper<CrmClientEntity, ClientResponse> clientRespMapper;

    public ClientService(ClientRepository clientRepo, Mapper<CrmClientEntity, ClientRequest> clientReqMapper,
                         Mapper<CrmClientEntity, ClientResponse> clientRespMapper) {
        this.clientRepo = clientRepo;
        this.clientReqMapper = clientReqMapper;
        this.clientRespMapper = clientRespMapper;
    }

    public ClientResponse createClient(ClientRequest req){
        clientRepo.findCrmClientEntityByNipNumber(req.getNipNumber()).ifPresent(
                crmClientEntity -> { throw new CrmClientAlreadyExistsException(
                        String.format("Client with NIP: %s already exists",req.getNipNumber()));
                });
        CrmClientEntity clientEntity = this.clientReqMapper.mapTo(req);
        CrmClientEntity savedEntity = this.clientRepo.save(clientEntity);
        return this.clientRespMapper.mapFrom(savedEntity);
    }
    public ClientResponse getClient(String id){
        UUID clientId = UUID.fromString(id);
        CrmClientEntity clientEntity = this.clientRepo.findCrmClientEntityById(clientId)
                .orElseThrow(() -> new CrmClientNotFoundException(id,"CLIENT_NOT_FOUND"));
        return this.clientRespMapper.mapFrom(clientEntity);
    }

    public List<ClientShortResponse> getAllClients(){
        return this.clientRepo.findAll().stream()
                .map(client -> new ClientShortResponse(client.getName(),client.getId().toString())
                ).toList();
    }
}
