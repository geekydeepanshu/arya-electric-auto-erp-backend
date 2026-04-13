package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.InquiryCreateRequest;
import com.arya_electric_auto.erp.dto.InquiryResponse;
import com.arya_electric_auto.erp.entity.*;
import com.arya_electric_auto.erp.mapper.InquiryMapper;
import com.arya_electric_auto.erp.repository.InquiryRepository;
import com.arya_electric_auto.erp.repository.VehicleModelRepository;
import com.arya_electric_auto.erp.repository.EmployeeRepository;
import com.arya_electric_auto.erp.repository.InquiryModelRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final PersonService personService;
    private final EmployeeRepository employeeRepository;
    private final InquiryModelRepository inquiryModelRepository;
    private final VehicleModelRepository vehicleModelRepository;

    public InquiryService(InquiryRepository inquiryRepository,
                          PersonService personService,
                          EmployeeRepository employeeRepository,
                          InquiryModelRepository inquiryModelRepository,
                          VehicleModelRepository vehicleModelRepository) {
        this.inquiryRepository = inquiryRepository;
        this.personService = personService;
        this.employeeRepository = employeeRepository;
        this.inquiryModelRepository = inquiryModelRepository;
        this.vehicleModelRepository = vehicleModelRepository;
    }

    // ✅ Create Inquiry
    public InquiryResponse create(InquiryCreateRequest request) {

    	Person person = personService.createOrGetEntity(
                request.getName(),
                request.getPhone(),
                request.getCity(),
                request.getAddress()
        );

    	Inquiry inquiry = new Inquiry();
    	inquiry.setPerson(person);
    	inquiry.setSource(request.getSource());
    	inquiry.setNotes(request.getNotes());
    	inquiry.setStatus(InquiryStatus.NEW);
    	inquiry.setInquiryDate(LocalDateTime.now());
    	inquiry.setNotes(request.getNotes());
    	inquiry.setCreatedAt(LocalDateTime.now());

	// ✅ Save inquiry first
    	Inquiry savedInquiry = inquiryRepository.save(inquiry);

	// ✅ NEW: Save models (IMPORTANT)
    	for (Long modelId : request.getModelIds()) {

    		VehicleModel model = vehicleModelRepository.findById(modelId)
    				.orElseThrow(() -> new RuntimeException("Model not found"));

    		InquiryModel im = new InquiryModel();
    		im.setInquiry(savedInquiry);
    		im.setModel(model);
    		im.setCreatedAt(LocalDateTime.now());

    		inquiryModelRepository.save(im);
    	}

    	return toResponse(savedInquiry);
    }

    // ✅ Get all
    public List<Inquiry> getAll() {
        return inquiryRepository.findByDeletedAtIsNull();
    }

    // ✅ Get by ID
    public InquiryResponse getById(Long id) {
       Inquiry inquiry =  inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
       return toResponse(inquiry);
        
    }

    //
    public Inquiry getEntityById(Long id) {
        return inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
    }
    
    
    // ✅ Update status
    public InquiryResponse updateStatus(Long id, InquiryStatus status) {
        Inquiry inquiry = getEntityById(id);
        inquiry.setStatus(status);
        inquiry.setUpdatedAt(LocalDateTime.now());
        return toResponse(inquiryRepository.save(inquiry));
    }

    // ✅ Assign employee
    public InquiryResponse assignEmployee(Long inquiryId, Long employeeId) {

        Inquiry inquiry = getEntityById(inquiryId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        inquiry.setHandledBy(employee);

        return toResponse(inquiryRepository.save(inquiry));
    }

    // ✅ Soft delete
    public void delete(Long id) {
        Inquiry inquiry = getEntityById(id);
        inquiry.setDeletedAt(LocalDateTime.now());
        inquiryRepository.save(inquiry);
    }
    
    
    public List<String> getModelsForInquiry(Long inquiryId) {

        return inquiryModelRepository.findByInquiryId(inquiryId)
                .stream()
                .map(im -> im.getModel().getName())
                .toList();
    }
    
    public InquiryResponse toResponse(Inquiry inquiry) {

        List<String> models = getModelsForInquiry(inquiry.getId());
        return InquiryMapper.toResponse(inquiry, models);
    }
}
