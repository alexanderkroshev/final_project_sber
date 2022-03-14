package server.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.entity.Card;
import server.exception.CardNotFoundException;
import server.repository.CardRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Card findById(Long id) throws CardNotFoundException {
        return cardRepository.findById(id).orElseThrow(CardNotFoundException::new);
    }
}
