package br.com.bookrestaurant.infraestructure.controller;

import br.com.bookrestaurant.entity.reserve.Client;
import br.com.bookrestaurant.entity.reserve.ReserveEntity;
import br.com.bookrestaurant.infraestructure.gateway.impl.reserve.ClientGateway;
import br.com.bookrestaurant.infraestructure.gateway.impl.reserve.ReserveGateway;
import br.com.bookrestaurant.infraestructure.gateway.interfaces.reserve.IClientGateway;
import br.com.bookrestaurant.infraestructure.gateway.interfaces.IDataBase;
import br.com.bookrestaurant.infraestructure.gateway.interfaces.reserve.IReserveGateway;
import br.com.bookrestaurant.infraestructure.presenter.reserve.ClientRecord;
import br.com.bookrestaurant.infraestructure.presenter.reserve.ReserveRecord;
import br.com.bookrestaurant.usecase.reserve.ReserveUseCase;

public class ReserveController {
    public ReserveEntity register(ReserveRecord reserveRecord,
                                  ClientRecord clientRecord,
                                  IDataBase database) {
        IReserveGateway reserveGateway = new ReserveGateway(database);
        IClientGateway clientGateway = new ClientGateway(database);

        Client client = new Client(clientRecord.name(), clientRecord.phone());
        ReserveEntity reserveEntity = ReserveUseCase.registerReserve(reserveRecord.restaurantId(),
                reserveRecord.date(), reserveRecord.seats(), client);

        reserveEntity = reserveGateway.registerReserve(reserveEntity);
        clientGateway.registerClient(client, reserveEntity.getId());

        return reserveEntity;
    }
}
