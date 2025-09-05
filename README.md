# Proyecto Spring Boot con Firebase Firestore

Este es un proyecto de ejemplo que demuestra cómo integrar Spring Boot con Google Firebase Firestore como base de datos NoSQL.

## Descripción

El proyecto consiste en una API simple para gestionar facturas (`Invoice`). Permite realizar operaciones básicas como crear y consultar facturas almacenadas en una colección de Firestore.

## Estructura del Proyecto

A continuación se describe la estructura de los directorios y archivos más importantes del proyecto:

```
firebase-spring/
├── .mvn/
├── app/
│   └── config.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/pca/invoicefirebase/
│   │   │       ├── model/
│   │   │       │   └── Invoice.java
│   │   │       └── repository/
│   │   │           └── InvoiceRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/pca/invoicefirebase/
│               └── repository/
│                   └── InvoiceRepositoryTest.java
├── pom.xml
└── README.md
```

## Componentes Clave

*   `pom.xml`: El archivo de configuración de Maven. Contiene todas las dependencias del proyecto, incluyendo Spring Boot y la librería cliente de Firebase.
*   `application.properties`: Archivo de configuración de Spring. Aquí se configuran las credenciales para la conexión con Firebase.
*   `Invoice.java`: El modelo de datos (POJO) que representa una factura. Los campos de esta clase se corresponden con los atributos de los documentos en la colección de Firestore.
*   `InvoiceRepository.java`: El repositorio encargado de toda la comunicación con Firestore. Contiene la lógica para guardar y consultar facturas.
*   `InvoiceRepositoryTest.java`: Una clase de prueba de JUnit 5 que demuestra cómo usar el `InvoiceRepository` para generar datos de prueba y guardarlos en Firestore.

## Prerrequisitos

*   Java 21 o superior
*   Apache Maven
*   Una cuenta de Google Cloud con un proyecto de Firebase activo.

## Configuración

1.  **Obtener credenciales de Firebase:**
    *   Ve a la Consola de Firebase, selecciona tu proyecto.
    *   Ve a "Configuración del proyecto" > "Cuentas de servicio".
    *   Haz clic en "Generar nueva clave privada" y descarga el archivo JSON con tus credenciales.

2.  **Configurar la aplicación:**
    *   Guarda el archivo JSON en un lugar seguro de tu sistema.
    *   Añade la siguiente propiedad en el archivo `src/main/resources/application.properties`, reemplazando la ruta con la ubicación de tu archivo JSON:

    ```properties
    google.firebase.credentials.file.path=/ruta/a/tu/archivo-de-credenciales.json
    ```

## Cómo Ejecutar el Proyecto

1.  Clona este repositorio.
2.  Abre una terminal en el directorio raíz del proyecto.
3.  Ejecuta el siguiente comando Maven para construir y arrancar la aplicación:

    ```bash
    mvn spring-boot:run
    ```

## Cómo Ejecutar los Tests

Para generar los datos de prueba en tu base de datos de Firestore, puedes ejecutar los tests de JUnit.

```bash
mvn test
```

Esto ejecutará el método `testSaveInvoice` en `InvoiceRepositoryTest` y creará 30 facturas de ejemplo en tu colección "invoice".
