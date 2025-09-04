package com.pca.invoicefirebase.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.pca.invoicefirebase.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class InvoiceRepository {

    private static final String COLLECTION_NAME = "facturas";


    private final Firestore firestore;

    @Autowired
    public InvoiceRepository(Firestore firestore) {
        this.firestore = firestore;
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
}
