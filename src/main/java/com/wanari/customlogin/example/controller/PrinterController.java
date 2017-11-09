package com.wanari.customlogin.example.controller;

import com.wanari.customlogin.example.config.security.constant.Roles;
import com.wanari.customlogin.example.controller.dto.CreatePrinterRequest;
import com.wanari.customlogin.example.service.PrinterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/printers")
public class PrinterController extends BaseController {

    private final PrinterService printerService;

    public PrinterController(PrinterService printerService) {
        this.printerService = printerService;
    }

    @RequestMapping(
        method = RequestMethod.GET)
    @PreAuthorize(Roles.USER)
    public ResponseEntity<?> getAll() {
        return printerService.findAll().fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    @RequestMapping(
        method = RequestMethod.POST)
    @PreAuthorize(Roles.USER)
    public ResponseEntity<?> create(@RequestBody CreatePrinterRequest request) {
        return printerService.create(request).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    @RequestMapping(
        value = "/detonate/{id}",
        method = RequestMethod.POST)
    @PreAuthorize(Roles.DETONATOR)
    public ResponseEntity<?> detonate(@PathVariable("id") String id) {
        return printerService.detonate(id).fold(
            this::errorToResponse,
            this::toResponse
        );
    }
}
