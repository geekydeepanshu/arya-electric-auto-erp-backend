package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.service.ServiceRecordRequest;
import com.arya_electric_auto.erp.dto.service.ServiceRecordResponse;
import com.arya_electric_auto.erp.entity.ServiceRecord;
import com.arya_electric_auto.erp.entity.ServiceItem;
import com.arya_electric_auto.erp.mapper.ServiceRecordMapper;
import com.arya_electric_auto.erp.repository.ServiceItemRepository;
import com.arya_electric_auto.erp.service.ServiceRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/records")
public class ServiceRecordController {

    private final ServiceRecordService service;
    private final ServiceItemRepository itemRepo;

    public ServiceRecordController(ServiceRecordService service,
                                   ServiceItemRepository itemRepo) {
        this.service = service;
        this.itemRepo = itemRepo;
    }

    @PostMapping
    public ServiceRecordResponse create(@RequestBody ServiceRecordRequest request) {

        ServiceRecord record = service.create(request);

        List<ServiceItem> items =
                itemRepo.findByServiceRecordId(record.getId());

        return ServiceRecordMapper.toResponse(record, items);
    }
}