package server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.model.Card;

@SpringBootTest
class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @Test
    void findCardById() {
        Card card = cardRepository.findCardByCardNumber("12345242");
        long id = card.getId();
        Assertions.assertEquals(id, 2);
    }
}