package com.pca.invoicefirebase.service;

import com.pca.invoicefirebase.model.Invoice;
import com.pca.invoicefirebase.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class InvoiceService {


    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoices(String diaSemana, String formaPago) throws ExecutionException, InterruptedException {
        List<Invoice> invoices = invoiceRepository.findAll();

        if (diaSemana != null && !diaSemana.equals("Todos")) {
            invoices = invoices.stream()
                    .filter(invoice -> invoice.getDiaSemana().equalsIgnoreCase(diaSemana))
                    .collect(Collectors.toList());
        }

        if (formaPago != null && !formaPago.equals("Todas")) {
            invoices = invoices.stream()
                    .filter(invoice -> invoice.getFormaPago().equalsIgnoreCase(formaPago))
                    .collect(Collectors.toList());
        }

        return invoices;
    }
}
