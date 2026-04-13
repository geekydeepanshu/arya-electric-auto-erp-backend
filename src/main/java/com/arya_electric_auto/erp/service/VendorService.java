package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.VendorRequest;
import com.arya_electric_auto.erp.dto.VendorResponse;
import com.arya_electric_auto.erp.entity.Vendor;
import com.arya_electric_auto.erp.mapper.VendorMapper;
import com.arya_electric_auto.erp.repository.VendorRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // 🔥 CREATE
    public VendorResponse create(VendorRequest request) {

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new RuntimeException("Vendor name is required");
        }

        Vendor vendor = new Vendor();
        vendor.setName(request.getName());
        vendor.setPhone(request.getPhone());
        vendor.setAddress(request.getAddress());
        vendor.setCreatedAt(LocalDateTime.now());

        return VendorMapper.toResponse(vendorRepository.save(vendor));
    }

    // 🔥 GET ALL
    public List<VendorResponse> getAll() {
        return vendorRepository.findAll().stream()
                .map(VendorMapper::toResponse)
                .toList();
    }

    // 🔥 GET BY ID
    public VendorResponse getById(Long id) {
        return VendorMapper.toResponse(getEntityById(id));
    }

    public Vendor getEntityById(Long id) {
        return vendorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    // 🔥 DELETE
    public void delete(Long id) {
        Vendor vendor = getEntityById(id);
        vendorRepository.delete(vendor);
    }
}
