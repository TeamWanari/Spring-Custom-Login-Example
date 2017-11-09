package com.wanari.customlogin.example.service;

import com.wanari.customlogin.example.controller.dto.CreatePrinterRequest;
import com.wanari.customlogin.example.domain.Printer;
import com.wanari.customlogin.example.repository.PrinterRepository;
import com.wanari.customlogin.example.service.error.ErrorResponse;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterService {

    private final PrinterRepository printerRepository;

    public PrinterService(PrinterRepository printerRepository) {
        this.printerRepository = printerRepository;
    }

    public Either<ErrorResponse, List<Printer>> findAll() {
        return Either.right(printerRepository.findAll());
    }

    public Either<ErrorResponse, Printer> create(CreatePrinterRequest request) {
        Printer printer = new Printer();
        printer.name = request.name;
        printerRepository.set(printer.id, printer);
        return Either.right(printer);
    }

    public Either<ErrorResponse, Printer> detonate(String id) {
        return printerRepository.findAll().stream()
            .filter(printer -> printer.id.equals(id))
            .findAny()
            .map(this::detonate)
            .orElseGet(() -> notFound(id));
    }

    public Either<ErrorResponse, Printer> detonate(Printer printer) {
        if(!printer.isDetonated) {
            printer.isDetonated = true;
            return Either.right(printer);
        } else {
            return alreadyDetonated(printer.id);
        }
    }

    private Either<ErrorResponse, Printer> notFound(String id) {
        return Either.left(ErrorResponse.notFound(String.format("Printer not found with id: %s", id)));
    }

    private Either<ErrorResponse, Printer> alreadyDetonated(String id) {
        return Either.left(ErrorResponse.badRequest(String.format("Printer is already detonated with id: %s", id)));
    }
}
