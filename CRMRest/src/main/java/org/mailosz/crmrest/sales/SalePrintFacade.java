package org.mailosz.crmrest.sales;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.mailosz.crmrest.crmclient.ClientService;
import org.mailosz.crmrest.crmclient.response.ClientResponse;
import org.mailosz.crmrest.exception.types.PDFGenerationException;
import org.mailosz.crmrest.sales.response.SaleCreationResp;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Component
public class SalePrintFacade {
    private final SaleService saleService;
    private final ClientService clientService;
    private final SpringTemplateEngine templateEngine;

    public SalePrintFacade(SaleService saleService, ClientService clientService, SpringTemplateEngine templateEngine) {
        this.saleService = saleService;
        this.clientService = clientService;
        this.templateEngine = templateEngine;
    }

    public byte[] printSale(UUID saleId){
        SaleCreationResp sale = this.saleService.getSaleBySaleId(saleId);
        UUID clientID = UUID.fromString(sale.getClientId());
        ClientResponse client = this.clientService.getClient(clientID);


        Context context = new Context();
        context.setVariable("saleName",sale.getSaleName());
        context.setVariable("clientName",client.getName());
        context.setVariable("clientNip",client.getNipNumber());
        context.setVariable("address",client.getAddress());
        context.setVariable("clientPhone",client.getPhone());
        context.setVariable("warehouseNote",sale.getWarehouseNote());
        context.setVariable("saleItems",sale.getSaleItems());

        String generatedHtml = templateEngine.process("sale-print",context);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.useFont(
                    () -> {
                        try {
                            return new ClassPathResource("fonts/Roboto-Regular.ttf").getInputStream();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    "Roboto"
            );
            builder.withHtmlContent(generatedHtml,null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        }catch (Exception e){
            throw new PDFGenerationException("Error occurred during pdf generation");
        }
    }
}
