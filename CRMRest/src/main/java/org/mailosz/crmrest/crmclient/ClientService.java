package org.mailosz.crmrest.crmclient;

import org.mailosz.crmrest.crmclient.request.ClientRequest;
import org.mailosz.crmrest.crmclient.request.ClientUpdateReq;
import org.mailosz.crmrest.crmclient.response.ClientResponse;
import org.mailosz.crmrest.crmclient.response.ClientShortResponse;
import org.mailosz.crmrest.crmclient.response.ClientWidgetResponse;
import org.mailosz.crmrest.exception.types.CrmClientAlreadyExistsException;
import org.mailosz.crmrest.exception.types.CrmClientNotFoundException;
import org.mailosz.crmrest.exception.types.CrmUserNotFoundException;
import org.mailosz.crmrest.helpers.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepo;
    private final Mapper<CrmClientEntity, ClientRequest> clientReqMapper;
    private final Mapper<CrmClientEntity, ClientResponse> clientRespMapper;
    private final Mapper<CrmClientEntity,ClientWidgetResponse> clientWidgetInfoMapper;

    public ClientService(ClientRepository clientRepo, Mapper<CrmClientEntity,
            ClientRequest> clientReqMapper, Mapper<CrmClientEntity,
            ClientResponse> clientRespMapper, Mapper<CrmClientEntity, ClientWidgetResponse> clientWidgetInfoMapper) {
        this.clientRepo = clientRepo;
        this.clientReqMapper = clientReqMapper;
        this.clientRespMapper = clientRespMapper;
        this.clientWidgetInfoMapper = clientWidgetInfoMapper;
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
    public ClientResponse getClient(UUID id){
        CrmClientEntity clientEntity = this.clientRepo.findCrmClientEntityById(id)
                .orElseThrow(() -> new CrmClientNotFoundException(id.toString(),"CLIENT_NOT_FOUND"));
        return this.clientRespMapper.mapFrom(clientEntity);
    }
    public ClientWidgetResponse getClientWidgetInfo(UUID id){
        CrmClientEntity clientEntity = this.clientRepo.findCrmClientEntityById(id)
                .orElseThrow(() -> new CrmClientNotFoundException(id.toString(),"CLIENT_NOT_FOUND"));
        return this.clientWidgetInfoMapper.mapFrom(clientEntity);
    }
    public List<ClientShortResponse> getAllClients(){
        return this.clientRepo.findAll().stream()
                .map(client -> new ClientShortResponse(client.getName(),client.getId().toString())
                ).toList();
    }

    @Transactional
    public ClientResponse updateClient(ClientUpdateReq req){
        UUID clientId = UUID.fromString(req.getClientId());
        CrmClientEntity client = this.clientRepo.findCrmClientEntityById(clientId)
                .orElseThrow(() -> new CrmClientNotFoundException(req.getClientId(),"CLIENT_NOT_FOUND"));
        client.setName(req.getName());
        client.setAddress(req.getAddress());
        client.setPhone(req.getPhone());
        client.setMail(req.getMail());
        client.setDecisionPerson(req.getDecisionPerson());

        return this.clientRespMapper.mapFrom(client);
    }
}
