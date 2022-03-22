package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.dto.BalanceDTO;
import server.repository.CardRepository;


@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public BalanceDTO findBalanceById(Long id)  {
        return cardRepository.findBalanceById(id);
    }
}
