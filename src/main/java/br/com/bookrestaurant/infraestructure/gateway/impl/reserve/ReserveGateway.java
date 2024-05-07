package br.com.bookrestaurant.infraestructure.gateway.impl.reserve;

import br.com.bookrestaurant.entity.reserve.ReserveEntity;
import br.com.bookrestaurant.infraestructure.gateway.interfaces.IDataBase;
import br.com.bookrestaurant.infraestructure.gateway.interfaces.reserve.IReserveGateway;

public class ReserveGateway implements IReserveGateway {
    private final IDataBase dataBase;

    public ReserveGateway (IDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public ReserveEntity registerReserve(ReserveEntity reserveEntity) {
        return this.dataBase.registerReserve(reserveEntity);
    }
}
