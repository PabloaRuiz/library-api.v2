package org.library.loan.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.library.book.domain.Book;
import org.library.book.persistence.BookPersistence;
import org.library.loan.domain.Loan;
import org.library.loan.persistence.LoanPersistence;
import org.library.loan.persistence.entity.LoanEntity;

import static config.JsonLoaderModel.*;
import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.library.book.persistence.converts.ConvertBook.converterToBookEntity;
import static org.library.loan.exception.ExceptionMessage.LOAN_IS_NOT;
import static org.library.loan.persistence.converts.LoanConverts.convertToLoanEntity;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanResourceTest {

    private final String path = "/api/v2/loan/";

    private final String error =  "Error in the operation, please check that the registration fields are completed";

    @Inject
    LoanPersistence loanPersistence;

    @Inject
    BookPersistence bookPersistence;

    @Inject
    ObjectMapper modelMapper;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        var listLoans = modelMapper.readValue(JSON_LOANS.load(), Loan[].class);

        for (Loan loan : listLoans) {
            var loanDto = convertToLoanEntity(loan);
            loanPersistence.persist(loanDto);
        }

    }

    @Test
    @DisplayName("return status http 200 information about the loan")
    @Order(1)
    void returnOk() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body(JSON_LOAN.load())
                .post(path)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("return status http 400 and error message confirming that there are incorrect fields")
    @Order(6)
    void returnErrorCreatedLoan() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body(JSON_LOAN_CREATED_ERROR.load())
                .post(path)
                .then()
                .statusCode(400)
                .body("message", equalTo(error));

    }


    @Test
    @DisplayName("return http 400 and error message when not finding the client")
    @Order(2)
    void returnErrorWhenNotFindingTheClient() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body(JSON_ADD_BOOKS_LOAN.load())
                .get(path + "customer/Joao")
                .then()
                .statusCode(404)
                .body("message", equalTo(LOAN_IS_NOT.getDescription()));
    }

    @Test
    @DisplayName("return http 200 and the client information that was returned")
    @Order(3)
    void returnLoan() {
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .get(path + "customer/Pablo")
                .then()
                .statusCode(200)
                .body("customer", equalTo("Pablo"))
                .body("email", equalTo("pabloa@outlook.com"))
                .body("loans", hasSize(2));
    }

    @Test
    @DisplayName("return http 200 and a list of all clients")
    @Order(4)
    void returnListLoan() {
        var loanList = given()
                .when()
                .contentType(APPLICATION_JSON)
                .get(path + "list?page=0&size=10")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", LoanEntity.class);

        assertEquals(9, loanList.size());

    }

    @Test
    @DisplayName("returns http 204 when adding a book to our loan account")
    @Order(5)
    void returnOkInAddBook() {
        initializeBooksData();

        var bookBeforeRenting1 = bookPersistence.findByIsbn("1478523698");
        var bookBeforeRenting2 = bookPersistence.findByIsbn("485588585");

        assertTrue(bookBeforeRenting1.isAvailable());
        assertTrue(bookBeforeRenting2.isAvailable());

        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body(JSON_ADD_BOOKS_LOAN.load())
                .patch(path + "loan/Pablo")
                .then()
                .statusCode(204);
    }

    @SneakyThrows
    private void initializeBooksData() {
        var listBooks = modelMapper.readValue(JSON_ADD_BOOKS_LOAN.load(), Book[].class);

        for (Book book : listBooks) {
            var bookEntity = converterToBookEntity(book);
            bookPersistence.persist(bookEntity);
        }

    }

}
