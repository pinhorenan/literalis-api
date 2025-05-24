package com.literalis.literalis_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class GoogleBooksService {

    @Value("${google.books.api.key:}")       // vazio se não definido
    private String apiKey;

    private static final String BASE_URL =
            "https://www.googleapis.com/books/v1/volumes";

    public List<Map<String, String>> searchBooks(String query) {

        RestTemplate rest = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("q", query);

        if (!apiKey.isBlank()) {             // só envia se houver
            builder.queryParam("key", apiKey);
        }

        String url = builder.toUriString();

        try {
            Map<?, ?> resp = rest.getForObject(url, Map.class);
            List<?> items = (List<?>) resp.get("items");
            if (items == null) return List.of();

            List<Map<String, String>> result = new ArrayList<>();

            for (Object obj : items) {
                Map<?, ?> item = (Map<?, ?>) obj;
                Map<?, ?> info = (Map<?, ?>) item.get("volumeInfo");

                @SuppressWarnings("unchecked")
                Map<String, Object> images =
                        (Map<String, Object>) info.get("imageLinks");

                @SuppressWarnings("unchecked")
                List<String> authors =
                        (List<String>) info.get("authors");

                result.add(Map.of(
                        "title",     Objects.toString(info.get("title"), "–"),
                        "author",    (authors == null || authors.isEmpty())
                                ? "Autor desconhecido"
                                : authors.get(0),
                        "thumbnail", images != null
                                ? Objects.toString(images.get("thumbnail"), "")
                                : ""
                ));
            }
            return result;

        } catch (HttpClientErrorException e) {
            // log simples; retorna lista vazia para evitar 500
            System.err.println("GoogleBooks API error: " + e.getStatusCode());
            return List.of();
        }
    }
}
