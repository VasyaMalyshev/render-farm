package ru.malyshev.renderfarm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.entity.StatusHistory;
import ru.malyshev.renderfarm.repository.StatusHistoryRepository;
import ru.malyshev.renderfarm.service.StatusHistoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;

    @Override
    public List<StatusHistory> getAll() {
        return statusHistoryRepository.findAll();
    }
}
