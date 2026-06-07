package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.InquiryCreateRequest;
import com.arya_electric_auto.erp.dto.InquiryResponse;
import com.arya_electric_auto.erp.entity.*;
import com.arya_electric_auto.erp.mapper.InquiryMapper;
import com.arya_electric_auto.erp.repository.InquiryRepository;

import com.arya_electric_auto.erp.repository.EmployeeRepository;
import com.arya_electric_auto.erp.repository.InquiryModelRepository;
import com.arya_electric_auto.erp.repository.ProductRepository;
import com.arya_electric_auto.erp.entity.Product;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final PersonService personService;
    private final EmployeeRepository employeeRepository;
    private final InquiryModelRepository inquiryModelRepository;
    private final ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(InquiryService.class);

    public InquiryService(InquiryRepository inquiryRepository,
                          PersonService personService,
                          EmployeeRepository employeeRepository,
                          InquiryModelRepository inquiryModelRepository,
                          ProductRepository productRepository) {
        this.inquiryRepository = inquiryRepository;
        this.personService = personService;
        this.employeeRepository = employeeRepository;
        this.inquiryModelRepository = inquiryModelRepository;
        this.productRepository = productRepository;
    }

    // ✅ Create Inquiry
    public InquiryResponse create(InquiryCreateRequest request) {

    	Person person = personService.createOrGetEntity(
                request.getName(),
                request.getPhone(),
                request.getCity(),
                request.getAddress()
        );
    	
    	log.info("Creating inquiry | name={} | phone={} | source={} ",
    			request.getName(),request.getPhone(), request.getSource());

    	Inquiry inquiry = new Inquiry();
    	inquiry.setPerson(person);
    	inquiry.setSource(request.getSource());
    	inquiry.setNotes(request.getNotes());
    	inquiry.setStatus(InquiryStatus.NEW);
    	inquiry.setInquiryDate(Instant.now());
    	inquiry.setNotes(request.getNotes());
    	inquiry.setCreatedAt(Instant.now());
    	
    	log.info("Inquiry created | inquiryId={}", inquiry.getId());

	// ✅ Save inquiry first
    	Inquiry savedInquiry = inquiryRepository.save(inquiry);

	// ✅ NEW: Save models (IMPORTANT)
    	for (Long productId : request.getModelIds()) {

    	    Product product = productRepository.findById(productId)
    	            .orElseThrow(() -> new RuntimeException("Product not found for ID: " + productId));

    	    InquiryModel im = new InquiryModel();
    	    im.setInquiry(savedInquiry);
    	    im.setProduct(product);
    	    im.setCreatedAt(Instant.now());

    	    inquiryModelRepository.save(im);
    	}

    	return toResponse(savedInquiry);
    }

    // ✅ Get all
    public List<Inquiry> getAll() {
    	 log.debug("Fetching all inquiries");
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
        inquiry.setUpdatedAt(Instant.now());
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
        inquiry.setDeletedAt(Instant.now());
        inquiryRepository.save(inquiry);
    }
    
    
    public List<String> getModelsForInquiry(Long inquiryId) {

        return inquiryModelRepository.findByInquiryId(inquiryId)
                .stream()
                .map(im -> im.getProduct().getName())
                .toList();
    }
    
    public InquiryResponse toResponse(Inquiry inquiry) {

        List<String> models = getModelsForInquiry(inquiry.getId());
        return InquiryMapper.toResponse(inquiry, models);
    }
}
