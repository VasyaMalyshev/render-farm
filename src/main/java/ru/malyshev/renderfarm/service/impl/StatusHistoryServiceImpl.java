package ru.malyshev.renderfarm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.renderfarm.dto.StatusHistoryDto;
import ru.malyshev.renderfarm.entity.StatusHistory;
import ru.malyshev.renderfarm.repository.StatusHistoryRepository;
import ru.malyshev.renderfarm.service.StatusHistoryService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;

    @Override
    public List<StatusHistoryDto> getAll() {
        List<StatusHistoryDto> statusHistoryDtos = new ArrayList<>();
        List<StatusHistory> statusHistories = statusHistoryRepository.findAll();
        for (StatusHistory statusHistory : statusHistories) {
            StatusHistoryDto historyDto = new StatusHistoryDto(statusHistory.getTask().getTitle(), statusHistory.getTask().getDescription(), statusHistory.getTaskStatus());
            statusHistoryDtos.add(historyDto);
        }
        return statusHistoryDtos;
    }
}
