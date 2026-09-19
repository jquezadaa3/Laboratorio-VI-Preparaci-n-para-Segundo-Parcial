package com.umg.labvi.hotel.service;

import com.umg.labvi.hotel.model.Reserva;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservaService {

    private final List<Reserva> reservas = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Reserva crear(Reserva reserva) {
        reserva.setId(contadorId.getAndIncrement());
        reservas.add(reserva);
        return reserva;
    }

    public List<Reserva> listarTodas() {
        return reservas;
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservas.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public Optional<Reserva> actualizar(Long id, Reserva datosActualizados) {
        return buscarPorId(id).map(reserva -> {
            reserva.setNombreCliente(datosActualizados.getNombreCliente());
            reserva.setHabitacion(datosActualizados.getHabitacion());
            reserva.setFechaEntrada(datosActualizados.getFechaEntrada());
            reserva.setFechaSalida(datosActualizados.getFechaSalida());
            reserva.setEstado(datosActualizados.getEstado());
            return reserva;
        });
    }

    public boolean cancelar(Long id) {
        return reservas.removeIf(r -> r.getId().equals(id));
    }
}
