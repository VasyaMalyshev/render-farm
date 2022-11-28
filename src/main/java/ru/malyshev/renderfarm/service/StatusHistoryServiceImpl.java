package ru.malyshev.renderfarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.model.StatusHistory;
import ru.malyshev.renderfarm.repository.StatusHistoryRepository;

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
