package config;

import io.restassured.internal.util.IOUtils;
import lombok.RequiredArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;

@RequiredArgsConstructor
public enum JsonLoaderModel {

    JSON_BOOK("src/test/java/config/bookJson/jsonBook.json"),
    JSON_BOOKS("src/test/java/config/bookJson/jsonBooks.json"),
    JSON_LOAN("src/test/java/config/json/loanJson/jsonLoan.json"),
    JSON_LOANS("src/test/java/config/json/loanJson/jsonLoans.json"),
    JSON_ADD_BOOKS_LOAN("src/test/java/config/json/loanJson/jsonAddBook.json");

    private final String jsonFile;

    public byte[] load() {
        try {
            var resource = new FileInputStream(jsonFile);
            return IOUtils.toByteArray(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
