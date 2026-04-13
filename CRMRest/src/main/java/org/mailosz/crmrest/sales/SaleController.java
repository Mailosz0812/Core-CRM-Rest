package org.mailosz.crmrest.sales;

import jakarta.validation.Valid;
import org.mailosz.crmrest.sales.request.SaleCreateReq;
import org.mailosz.crmrest.sales.request.SaleUpdateReq;
import org.mailosz.crmrest.sales.request.StageOperationReq;
import org.mailosz.crmrest.sales.response.SaleCreationResp;
import org.mailosz.crmrest.sales.response.ShortSaleResp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
    public SaleCreationResp getSale(@PathVariable("id") UUID saleId){
        return this.saleService.getSaleBySaleId(saleId);
    }
    @GetMapping
    public List<ShortSaleResp> getAllSales(@RequestParam(required = false) Stage stage,
                                           @RequestParam(required = false) String term,
                                           @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return this.saleService.getAllSales(pageable,stage,term);
    }

    @GetMapping("/client/{clientId}")
    public List<ShortSaleResp> getSalesByClient(@PathVariable("clientId") UUID clientId ){
        return this.saleService.getSalesByClientId(clientId, Pageable.unpaged());
    }

    @PostMapping("/stage")
    public SaleCreationResp createStage(@RequestBody @Valid StageOperationReq req){
        return this.saleService.modifySaleStage(req);
    }
    @PutMapping
    public SaleCreationResp updateSale(@RequestBody @Valid SaleUpdateReq req){
        return this.saleService.updateSale(req);
    }
}
