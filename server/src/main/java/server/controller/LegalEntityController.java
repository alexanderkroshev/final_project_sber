package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.dto.LegalEntityDto;
import server.service.LegalEntityService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/legal-entity")
public class LegalEntityController {

    private LegalEntityService service;

    @GetMapping("/all")
    public List<LegalEntityDto> findAll() {
        return service.findAllLegalEntity();
    }

    @GetMapping("/{id}")
    public LegalEntityDto findById(@PathVariable long id){
        return service.findById(id);
    }
}
