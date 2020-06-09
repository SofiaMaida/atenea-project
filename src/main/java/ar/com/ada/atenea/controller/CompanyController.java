package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CompanyDTO;
import ar.com.ada.atenea.service.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired @Qualifier("companyServices")
    private CompanyServices companyServices;

    @GetMapping({"", "/"}) // localhost:8080/companies && localhost:8080/companies/ [GET]
    public ResponseEntity getAllCompanies() {
        List<CompanyDTO> all = companyServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/{id}", "/{id}/"}) // localhost:8080/companies/{id} && localhost:8080/companies/{id}/ [GET]
    public ResponseEntity getCompanyById(@PathVariable Long id) {
        CompanyDTO companyById = companyServices.findCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    @PostMapping({"", "/"}) // localhost:8080/companies && localhost:8080/companies/ [POST]
    public ResponseEntity addNewCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        CompanyDTO companyDtoSaved = companyServices.save(companyDTO);
        return ResponseEntity
                .created(new URI("/companies/" + companyDtoSaved.getId()))
                .body(companyDtoSaved);
    }

    @PutMapping({"/{id}", "/{id}/"}) // localhost:8080/companies/1 && localhost:8080/companies/1/ [PUT]
    public ResponseEntity updateCompanyById(@Valid @RequestBody CompanyDTO companyDTO, @PathVariable Long id) {
        CompanyDTO companyUpdated = companyServices.updateCompany(companyDTO, id);
        return ResponseEntity.ok(companyUpdated);
    }

    @DeleteMapping({"", "/"}) // localhost:8080/companies/1 && localhost:8080/companies/1/ [DELETE]
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        companyServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
