package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.service.ServiceHistoryItem;
import com.arya_electric_auto.erp.dto.service.ServiceHistoryResponse;
import com.arya_electric_auto.erp.entity.CustomerVehicle;
import com.arya_electric_auto.erp.entity.JobCard;
import com.arya_electric_auto.erp.entity.ServiceRecord;
import com.arya_electric_auto.erp.mapper.ServiceHistoryMapper;
import com.arya_electric_auto.erp.repository.CustomerVehicleRepository;
import com.arya_electric_auto.erp.repository.JobCardRepository;
import com.arya_electric_auto.erp.repository.PersonRepository;
import com.arya_electric_auto.erp.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ServiceHistoryService {

    private final CustomerVehicleRepository vehicleRepo;
    private final PersonRepository personRepo;
    private final JobCardRepository jobCardRepo;
    private final ServiceRecordRepository serviceRecordRepo;

    public ServiceHistoryService(CustomerVehicleRepository vehicleRepo,
                                 PersonRepository personRepo,
                                 JobCardRepository jobCardRepo,
                                 ServiceRecordRepository serviceRecordRepo) {
        this.vehicleRepo = vehicleRepo;
        this.personRepo = personRepo;
        this.jobCardRepo = jobCardRepo;
        this.serviceRecordRepo = serviceRecordRepo;
    }

    public ServiceHistoryResponse getVehicleHistory(Long vehicleId) {

        vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        List<ServiceHistoryItem> items = jobCardRepo.findByVehicleId(vehicleId).stream()
                .sorted(Comparator.comparing(JobCard::getCreatedAt,
                        Comparator.nullsLast(LocalDateTime::compareTo)).reversed())
                .map(this::toHistoryItem)
                .filter(item -> item != null)
                .toList();

        return ServiceHistoryMapper.toResponse(items);
    }

    public ServiceHistoryResponse getPersonHistory(Long personId) {

        personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        List<CustomerVehicle> vehicles = vehicleRepo.findByPersonId(personId);
        List<ServiceHistoryItem> items = new ArrayList<>();

        for (CustomerVehicle vehicle : vehicles) {
            List<ServiceHistoryItem> vehicleItems = jobCardRepo.findByVehicleId(vehicle.getId()).stream()
                    .map(this::toHistoryItem)
                    .filter(item -> item != null)
                    .toList();

            items.addAll(vehicleItems);
        }

        items.sort(Comparator.comparing(ServiceHistoryItem::getDate,
                Comparator.nullsLast(String::compareTo)).reversed());

        return ServiceHistoryMapper.toResponse(items);
    }

    private ServiceHistoryItem toHistoryItem(JobCard jobCard) {

        if (!"DELIVERED".equals(jobCard.getStatus())) {
            return null;
        }

        ServiceRecord record = serviceRecordRepo.findByJobCardId(jobCard.getId())
                .orElse(null);

        if (record == null) {
            return null;
        }

        return ServiceHistoryMapper.toItem(jobCard, record);
    }
}
