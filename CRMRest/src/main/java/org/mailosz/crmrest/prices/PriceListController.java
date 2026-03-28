package org.mailosz.crmrest.prices;
//TODO encja cennika (jest juz w bazie), kontroler, serwis

import jakarta.validation.Valid;
import org.mailosz.crmrest.prices.request.PriceListCreationReq;
import org.mailosz.crmrest.prices.response.PriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListShortResp;
import org.mailosz.crmrest.product.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
public class PriceListController {

    private final PriceListService priceService;

    public PriceListController(PriceListService priceService) {
        this.priceService = priceService;
    }

    @PostMapping()
    public ResponseEntity<PriceListResponse> createPriceList(@RequestBody @Valid PriceListCreationReq req){
        PriceListResponse resp = this.priceService.createPriceList(req);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(resp.getId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }

    @GetMapping("/{id}")
    public PriceListResponse getPriceList(@PathVariable UUID id){
        return this.priceService.getPriceListById(id);
    }
    @GetMapping("/list/{id}")
    public List<PriceListShortResp> getPriceListByClient(@PathVariable UUID id){
        return this.priceService.getPricesListByClientId(id);
    }
    @GetMapping("/items/{id}")
    public List<ProductResponse> getProductsByListId(@PathVariable UUID id){
        return this.priceService.getProductsByListId(id);
    }
}

