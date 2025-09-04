package com.pca.invoicefirebase.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;

@Data
public class Invoice {
    @DocumentId
    private String id;
    private String facturaNumero;
    private String cliente;
    private String diaSemana;
    private String formaPago;
    private double valor;
}
