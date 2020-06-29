package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CompanyDTO;
import ar.com.ada.atenea.service.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired @Qualifier("companyServices")
    private CompanyServices companyServices;

    // ?page=0&company=1
    //@GetMapping({"", "/"}) // localhost:8080/companies && localhost:8080/companies/ [GET]
    //public ResponseEntity getAllCompanies(@RequestParam Optional<String> company,
    //                                      @RequestParam Optional<Integer> page) {
    //    List<CompanyDTO> all = companyServices.findAll(company.orElse(null), page.orElse(0));
    //    return ResponseEntity.ok(all);
    //}

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"", "/"}) // localhost:8080/companies && localhost:8080/companies/ [POST]
    public ResponseEntity addNewCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        CompanyDTO companyDtoSaved = companyServices.save(companyDTO);
        return ResponseEntity
                .created(new URI("/companies/" + companyDtoSaved.getId()))
                .body(companyDtoSaved);
    }

}
