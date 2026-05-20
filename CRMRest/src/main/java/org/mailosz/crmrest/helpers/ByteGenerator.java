package org.mailosz.crmrest.helpers;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.mailosz.crmrest.exception.types.PDFGenerationException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ByteGenerator {

    public byte[] getBytes(String generatedHtml){
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
