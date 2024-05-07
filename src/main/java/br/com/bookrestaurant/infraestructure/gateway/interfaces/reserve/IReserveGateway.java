package br.com.bookrestaurant.infraestructure.gateway.interfaces.reserve;

import br.com.bookrestaurant.entity.reserve.ReserveEntity;

public interface IReserveGateway {
    ReserveEntity registerReserve (ReserveEntity reserveEntity);
}
