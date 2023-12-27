package org.library.book.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.book.domain.Book;
import org.library.book.persistence.BookPersistence;
import org.library.book.persistence.entity.BookEntity;

import static config.JsonLoaderModel.*;
import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.library.book.exception.ExceptionMessage.BOOK_NOT_EXIST;
import static org.library.book.persistence.converts.ConvertBook.converterToBookEntity;

@QuarkusTest
public class BookResourceTest {

    private final String path = "/api/v2/book/";

    private final String error =  "Error in the operation, please check that the registration fields are completed";

    @Inject
    BookPersistence persistence;

    @Inject
    ObjectMapper modelMapper;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        var listBook = modelMapper.readValue(JSON_BOOKS.load(), Book[].class);

        for (Book book : listBook) {
            var bookDto = converterToBookEntity(book);
            persistence.persist(bookDto);
        }

    }

    @Test
    @DisplayName("returns error when not finding the book by isbn")
    void returnError() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .get(path + "isbn/35252")
                .then()
                .statusCode(404)
                .body("message", equalTo(BOOK_NOT_EXIST.getDescription()));

        persistence.deleteAll();

    }

    @Test
    @DisplayName("return status http 200 information about the book that was registered")
    void returnOk() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body(JSON_BOOK.load())
                .post(path)
                .then()
                .statusCode(200)
                .body("title", equalTo("Java"))
                .body("author", equalTo("Pablo A. Ruiz"))
                .body("isbn", equalTo("1478523698"))
                .body("page", equalTo(180));

        persistence.deleteAll();

    }

    @Test
    @DisplayName("return status http 400 and error message confirming that there are incorrect fields")
    void returnErrorCreatedBook() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body(JSON_BOOK_CREATED_ERROR.load())
                .post(path)
                .then()
                .statusCode(400)
                .body("message", equalTo(error));


        persistence.deleteAll();

    }



    @Test
    @DisplayName("return status http 200 with a list of books")
    void returnPageBooks() {

        var bookList = given()
                .when()
                .contentType(APPLICATION_JSON)
                .get(path+"list?page=0&size=10")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", BookEntity.class);

        assertEquals(10, bookList.size());

        persistence.deleteAll();

    }

    @Test
    @DisplayName("return book that matches your isbn")
    void returnBook() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .get(path + "isbn/1478523698")
                .then()
                .statusCode(200)
                .body("title", equalTo("Java"))
                .body("author", equalTo("Pablo A. Ruiz"))
                .body("isbn", equalTo("1478523698"))
                .body("page", equalTo(180));

        persistence.deleteAll();

    }

    @Test
    @DisplayName("returns list of books that are available")
    void returnBookStatusTrue() {

        var bookList = given()
                .when()
                .contentType(APPLICATION_JSON)
                .get(path+ "status?available=true")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", BookEntity.class);

        assertEquals(8, bookList.size());

        persistence.deleteAll();
    }

    @Test
    @DisplayName("returns list of books that are unavailable")
    void returnBookStatusFalse() {

        var bookList = given()
                .when()
                .contentType(APPLICATION_JSON)
                .get(path+ "status?available=false")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", BookEntity.class);

        assertEquals(2, bookList.size());
    }

}