package ru.malyshev.renderfarm.service;

import ru.malyshev.renderfarm.dto.StatusHistoryDto;
import ru.malyshev.renderfarm.entity.StatusHistory;

import java.util.List;

public interface StatusHistoryService {
    List<StatusHistoryDto> getAll();
}
