package com.pca.invoicefirebase.repository;

import com.pca.invoicefirebase.model.Invoice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    void testSaveInvoice() throws ExecutionException, InterruptedException {
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        String[] formasPago = {"Tarjeta de Crédito", "Efectivo", "Transferencia Bancaria", "PSE"};
        Random random = new Random();

        for (int i = 1; i <= 30; i++) {
            Invoice invoice = new Invoice();
            invoice.setFacturaNumero(String.format("F-2024-%03d", i));
            invoice.setCliente("Cliente " + i);
            invoice.setDiaSemana(diasSemana[random.nextInt(diasSemana.length)]);
            invoice.setFormaPago(formasPago[random.nextInt(formasPago.length)]);
            invoice.setValor(100 + (1000 - 100) * random.nextDouble());

            Invoice savedInvoice = invoiceRepository.save(invoice);

            assertNotNull(savedInvoice.getId(), "El ID de la factura no debería ser nulo después de guardarla.");
            System.out.println("Factura guardada con ID: " + savedInvoice.getId());
        }
    }
}
