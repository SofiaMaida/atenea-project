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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("companyServices")
public class CompanyServices implements Services<CompanyDTO> {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    private CompanyCycleMapper companyCycleMapper = CompanyCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;


    @Override
    public List<CompanyDTO> findAll() {
        List<Company> companyEntityList = companyRepository.findAll();
        List<CompanyDTO> companyDTOList = companyCycleMapper.toDto(companyEntityList, context);
        return companyDTOList;
    }

    public CompanyDTO findCompanyById(Long id) {
        Optional<Company> byIdOptional = companyRepository.findById(id);
        CompanyDTO companyDTO = null;

        if (byIdOptional.isPresent()) {
            Company companyById = byIdOptional.get();
            companyDTO = companyCycleMapper.toDto(companyById, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Company", id);
        }
        return companyDTO;
    }

    @Override
    public CompanyDTO save(CompanyDTO dto) {
        Company companyToSave = companyCycleMapper.toEntity(dto, context);
        Company companySaved = companyRepository.save(companyToSave);
        CompanyDTO companyDtoSaved = companyCycleMapper.toDto(companySaved, context);
        return companyDtoSaved;
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO, Long id) {
        Optional<Company> byIdOptional = companyRepository.findById(id);
        CompanyDTO companyDtoUpdated = null;

        if (byIdOptional.isPresent()) {
            Company companyById = byIdOptional.get();
            companyDTO.setId(companyById.getId());
            Company companyToUpdate = companyCycleMapper.toEntity(companyDTO, context);
            Company companyUpdated = companyRepository.save(companyToUpdate);
            companyDtoUpdated = companyCycleMapper.toDto(companyUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Company", id);
        }
        return companyDtoUpdated;
    }


    @Override
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
