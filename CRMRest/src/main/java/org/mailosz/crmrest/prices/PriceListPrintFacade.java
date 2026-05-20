package org.mailosz.crmrest.prices;

import org.mailosz.crmrest.helpers.ByteGenerator;
import org.mailosz.crmrest.product.Product;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.UUID;

@Component
public class PriceListPrintFacade {

    private final PriceListService priceListService;
    private final SpringTemplateEngine templateEngine;
    private final ByteGenerator byteHelper;

    public PriceListPrintFacade(PriceListService priceListService,
                                SpringTemplateEngine templateEngine, ByteGenerator byteHelper) {
        this.priceListService = priceListService;
        this.templateEngine = templateEngine;
        this.byteHelper = byteHelper;
    }

    public byte[] printPriceList(UUID priceListID){
        List<Product> products = this.priceListService.getProductsToPrint(priceListID);


        Context context = new Context();
        context.setVariable("products",products);

        String generatedHtml =  templateEngine.process("price-list",context);
        return this.byteHelper.getBytes(generatedHtml);
    }
}
