package br.com.bookrestaurant.usecase.reserve;

import br.com.bookrestaurant.entity.reserve.Client;
import br.com.bookrestaurant.entity.reserve.ReserveEntity;
import br.com.bookrestaurant.entity.reserve.ReserveEntityBuilder;
import br.com.bookrestaurant.entity.reserve.exception.ReserveInvalidException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ReserveUseCase {
    public static ReserveEntity registerReserve (UUID restaurantId, LocalDateTime date, Integer seats, Client client) {
        validClient(client);
        return new ReserveEntityBuilder()
                .addInfos(restaurantId, date, seats)
                .addClient(client)
                .build();
    }
    public static void validClient(Client client) {
        Optional.ofNullable(client)
                .orElseThrow(() -> new ReserveInvalidException("Cliente é obrigatório"));
    }
}
