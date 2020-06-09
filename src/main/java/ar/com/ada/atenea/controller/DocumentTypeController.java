package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.DocumentTypeDTO;
import ar.com.ada.atenea.service.DocumentTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentTypeController {

    @Autowired @Qualifier("documentTypeServices")
    private DocumentTypeServices documentTypeServices;


    @GetMapping({"", "/"})
    public ResponseEntity getAllDocuments() {
        List<DocumentTypeDTO> all = documentTypeServices.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addNewDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) throws URISyntaxException {
        DocumentTypeDTO save = documentTypeServices.save(documentTypeDTO);
        return ResponseEntity.created(new URI("/documents/" + documentTypeDTO.getId())).body(save);
    }
}
