package com.pca.invoicefirebase.controller;

import com.pca.invoicefirebase.model.Invoice;
import com.pca.invoicefirebase.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class InvoiceController {


    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public String viewInvoices(@RequestParam(name = "dia_semana", required = false) String diaSemana, @RequestParam(name = "forma_pago", required = false) String formaPago, Model model) throws ExecutionException, InterruptedException {
        List<Invoice> invoices = invoiceService.getInvoices(diaSemana, formaPago);
        model.addAttribute("invoices", invoices);
        return "invoices";
    }
}
