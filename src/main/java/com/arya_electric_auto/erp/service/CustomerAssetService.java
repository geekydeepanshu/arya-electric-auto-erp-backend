package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.CustomerAssetRequest;
import com.arya_electric_auto.erp.dto.CustomerAssetResponse;
import com.arya_electric_auto.erp.entity.AssetType;
import com.arya_electric_auto.erp.entity.CustomerAsset;
import com.arya_electric_auto.erp.entity.Person;
import com.arya_electric_auto.erp.repository.CustomerAssetRepository;
import com.arya_electric_auto.erp.repository.PersonRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAssetService {

    private final CustomerAssetRepository assetRepo;
    private final PersonRepository personRepo;

    public CustomerAssetService(CustomerAssetRepository assetRepo, PersonRepository personRepo) {
        this.assetRepo = assetRepo;
        this.personRepo = personRepo;
    }

    // 🔹 CREATE ASSET
    public CustomerAssetResponse create(CustomerAssetRequest req) {

        Person person = personRepo.findById(req.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        // prevent duplicate identifier (if provided)
        if (req.getIdentifier() != null && !req.getIdentifier().isBlank()) {
            Optional<CustomerAsset> existing = assetRepo
                    .findByIdentifierAndDeletedAtIsNull(req.getIdentifier());

            if (existing.isPresent()) {
                return toResponse(existing.get()); // safe return
            }
        }

        CustomerAsset asset = new CustomerAsset();
        asset.setPerson(person);
        asset.setType(AssetType.valueOf(req.getType()));
        asset.setDisplayName(req.getDisplayName());
        asset.setIdentifier(req.getIdentifier());
        asset.setNotes(req.getNotes());

        return toResponse(assetRepo.save(asset));
    }

    // 🔹 GET ASSETS BY PERSON
    public List<CustomerAssetResponse> getByPerson(Long personId) {

        List<CustomerAsset> assets = assetRepo.findByPersonIdAndDeletedAtIsNull(personId);

        List<CustomerAssetResponse> list = new ArrayList<>();

        for (CustomerAsset a : assets) {
            list.add(toResponse(a));
        }

        return list;
    }

    // 🔹 MAPPER (internal)
    private CustomerAssetResponse toResponse(CustomerAsset a) {

        CustomerAssetResponse res = new CustomerAssetResponse();

        res.setId(a.getId());
        res.setPersonId(a.getPerson().getId());
        res.setType(a.getType().name());
        res.setDisplayName(a.getDisplayName());
        res.setIdentifier(a.getIdentifier());
        res.setNotes(a.getNotes());

        return res;
    }
}