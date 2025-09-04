package com.pca.invoicefirebase.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public CredentialsProvider googleCredentials() throws IOException {
        // Carga el archivo de credenciales directamente desde el classpath (src/main/resources)
        try (InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json")) {
            if (serviceAccount == null) {
                throw new IOException("El archivo 'serviceAccountKey.json' no se encontró en el classpath. Asegúrate de que esté en 'src/main/resources'.");
            }

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            return FixedCredentialsProvider.create(credentials);
        }
    }
}
