package org.mailosz.crmrest.sales;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public SaleResponse createSale(@Valid @RequestBody SaleCreateReq saleReq){
        return this.saleService.createSale(saleReq);
    }
}
