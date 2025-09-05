package com.pca.invoicefirebase.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.base.Strings;
import com.pca.invoicefirebase.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class InvoiceRepository {

    private static final String COLLECTION_NAME = "invoice";


    private final Firestore firestore;

    @Autowired
    public InvoiceRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Invoice save(Invoice invoice) throws ExecutionException, InterruptedException {
        DocumentReference docRef = this.firestore.collection(COLLECTION_NAME).document();
        invoice.setId(docRef.getId());
        ApiFuture<WriteResult> result = docRef.set(invoice);
        result.get(); // wait for write to complete
        return invoice;
    }

    public List<Invoice> findAll() throws ExecutionException, InterruptedException {
        List<Invoice> invoices = new ArrayList<>();
        ApiFuture<QuerySnapshot> query = this.firestore.collection(COLLECTION_NAME).get();
        QuerySnapshot querySnapshot = query.get();
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            invoices.add(document.toObject(Invoice.class));
        }
        return invoices;
    }

    public List<Invoice> findByDiaSemanaAndFormaPago(String diaSemana, String formaPago) throws ExecutionException, InterruptedException {
        List<Invoice> invoices = new ArrayList<>();
        Query query = this.firestore.collection(COLLECTION_NAME);


        if(!Strings.isNullOrEmpty(diaSemana) && !diaSemana.equals("Todos"))
            query.whereEqualTo("diaSemana", diaSemana);

        if(!Strings.isNullOrEmpty(formaPago) && !formaPago.equals("Todos"))
            query.whereEqualTo("formaPago", formaPago);


        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            invoices.add(document.toObject(Invoice.class));
        }
        return invoices;
    }
}
