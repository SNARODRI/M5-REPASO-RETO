package com.bancolombia.cuentabancaria.service.cliente;

import com.bancolombia.cuentabancaria.repository.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }
}
