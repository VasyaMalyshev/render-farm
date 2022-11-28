package ru.malyshev.renderfarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malyshev.renderfarm.dto.StatusHistoryDto;
import ru.malyshev.renderfarm.service.StatusHistoryService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/status-history")
@RequiredArgsConstructor
public class StatusHistoryController {

    private final StatusHistoryService statusHistoryService;

    @GetMapping
    public ResponseEntity<List<StatusHistoryDto>> getAllHistoryStatus() {
        return new ResponseEntity<>(statusHistoryService.getAll(), HttpStatus.OK);
    }
}
