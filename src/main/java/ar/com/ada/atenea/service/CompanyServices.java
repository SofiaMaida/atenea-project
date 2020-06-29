package ar.com.ada.atenea.service;

import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.CompanyDTO;
import ar.com.ada.atenea.model.entity.Company;
import ar.com.ada.atenea.model.mapper.CompanyCycleMapper;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.repository.CompanyRepository;
import ar.com.ada.atenea.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("companyServices")
public class CompanyServices {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    private CompanyCycleMapper companyCycleMapper = CompanyCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;


    public CompanyDTO save(CompanyDTO dto) {
        Company companyToSave = companyCycleMapper.toEntity(dto, context);
        Company companySaved = companyRepository.save(companyToSave);
        CompanyDTO companyDtoSaved = companyCycleMapper.toDto(companySaved, context);
        return companyDtoSaved;
    }

    public void delete(Long id) {
        Optional<Company> companyByIdOptional = companyRepository.findById(id);

        if (companyByIdOptional.isPresent()) {
            Company companyToDelete = companyByIdOptional.get();
            companyRepository.delete(companyToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Company", id);
        }
    }



}
