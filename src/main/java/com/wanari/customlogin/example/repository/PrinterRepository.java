package com.wanari.customlogin.example.repository;

import com.wanari.customlogin.example.domain.Printer;
import org.springframework.boot.actuate.metrics.util.SimpleInMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrinterRepository extends SimpleInMemoryRepository<Printer> {

    // Thanks SimpleInMemoryRepository...
    public List<Printer> findAll() {
        return (List<Printer>) super.findAll();
    }
}
