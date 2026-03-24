package org.mailosz.crmrest.sales;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.mailosz.crmrest.crmuser.auth.CrmUserDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleCreationResp> createSale(@Valid @RequestBody SaleCreateReq saleReq,
                                                       @AuthenticationPrincipal String username){
        SaleCreationResp saleResponse = this.saleService.createSale(saleReq,username);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saleResponse.getSaleId())
                .toUri();
        return ResponseEntity.created(location).body(saleResponse);
    }

    @GetMapping("/{id}")
    public SaleResponse getSale(@PathVariable("id") @UUID String saleId){
        return this.saleService.getSaleBySaleId(saleId);
    }

    @GetMapping("/client/{clientId}")
    public List<ShortSaleResp> getSalesByClient(@PathVariable("clientId") @UUID String clientId ){
        return this.saleService.getSalesByClientId(clientId, Pageable.unpaged());
    }
}
