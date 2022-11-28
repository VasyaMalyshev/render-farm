package ru.malyshev.renderfarm.service;

import ru.malyshev.renderfarm.model.StatusHistory;

import java.util.List;

public interface StatusHistoryService {
    List<StatusHistory> getAll();
}
