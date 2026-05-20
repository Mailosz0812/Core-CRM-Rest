package org.mailosz.crmrest.prices;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.crmclient.response.ShortPriceListResp;
import org.mailosz.crmrest.prices.request.BasePriceListOperationReq;
import org.mailosz.crmrest.prices.request.PriceListCreationReq;
import org.mailosz.crmrest.prices.request.PriceListUpdateReq;
import org.mailosz.crmrest.prices.response.BasePriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListShortResp;
import org.mailosz.crmrest.product.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
public class PriceListController {

    private final PriceListService priceService;
    private final PriceListPrintFacade printService;

    public PriceListController(PriceListPrintFacade printService, PriceListService priceService) {
        this.printService = printService;
        this.priceService = priceService;
    }

    @PostMapping()
    public ResponseEntity<PriceListResponse> createIndividualPriceList(@RequestBody @Valid PriceListCreationReq req){
        PriceListResponse resp = this.priceService.createPriceList(req);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(resp.getId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }

    @PatchMapping("/base")
    public BasePriceListResponse updateBasePriceList(@RequestBody @Valid BasePriceListOperationReq req){
        return this.priceService.patchBasePriceList(req);
    }

    @GetMapping("/base")
    public BasePriceListResponse getBasePriceList(){
        return this.priceService.getBasePriceList();
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
    public List<Product> getProductsByListId(@PathVariable UUID id){
        return this.priceService.getProductsByListId(id);
    }
    @GetMapping("/client/{id}")
    public ShortPriceListResp getLatestProducts(@PathVariable UUID id){
        return this.priceService.getLatestProductsByClientId(id);
    }
    @PatchMapping("/list")
    public PriceListResponse updateProducts(@RequestBody @Valid PriceListUpdateReq updateReq){
        return this.priceService.updateProductsList(updateReq);
    }
    @GetMapping("/list/{id}/print")
    ResponseEntity<byte[]> getPricesPrint(@PathVariable @NotNull UUID id){
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"Cennik" + id + ".pdf\"")
                .body(this.printService.printPriceList(id));
    }
}

