package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.dto.LegalEntityDto;
import server.exceptions.LegalEntityNotFoundException;
import server.repository.LegalEntityRepository;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LegalEntityService {

    private final LegalEntityRepository repository;

    public List<LegalEntityDto> findAllLegalEntity() {
        return repository.findAll();
    }

    public LegalEntityDto findById(long id){
        return Optional.ofNullable(repository.findById(id)).orElseThrow(LegalEntityNotFoundException::new);
    }
}
