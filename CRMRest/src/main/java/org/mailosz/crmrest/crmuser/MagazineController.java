package org.mailosz.crmrest.crmuser;

import jakarta.validation.Valid;
import org.mailosz.crmrest.crmuser.request.UserCreateReq;
import org.mailosz.crmrest.crmuser.response.UserCreateResp;
import org.mailosz.crmrest.crmuser.roles.Role;
import org.mailosz.crmrest.sales.SaleService;
import org.mailosz.crmrest.sales.request.PackOperation;
import org.mailosz.crmrest.sales.response.SaleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/users/magazine")
public class MagazineController {
    private final SaleService saleService;

    public MagazineController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/daily")
    public List<SaleResponse> getDailySales(@RequestParam(required = false) OffsetDateTime marginDate){
        return this.saleService.getDailySales(marginDate);
    }


    @PostMapping("/packed")
    public ResponseEntity<Void> markAsPacked(@RequestBody @Valid PackOperation pack){
        this.saleService.markSaleAsPacked(pack);
        return ResponseEntity.noContent().build();
    }

}
