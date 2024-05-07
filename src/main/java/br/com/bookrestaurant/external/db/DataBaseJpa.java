package br.com.bookrestaurant.external.db;

import br.com.bookrestaurant.entity.reserve.Client;
import br.com.bookrestaurant.entity.reserve.ReserveEntity;
import br.com.bookrestaurant.external.model.ClientModel;
import br.com.bookrestaurant.external.model.ReserveModel;
import br.com.bookrestaurant.infraestructure.gateway.interfaces.IDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataBaseJpa implements IDataBase {

    private ClientRepository clientRepository;
    private ReserveRepository reserveRepository;

    @Autowired
    public DataBaseJpa(ClientRepository clientRepository, ReserveRepository reserveRepository) {
        this.clientRepository = clientRepository;
        this.reserveRepository = reserveRepository;

    }
    @Override
    public ReserveEntity registerReserve(ReserveEntity reserveEntity) {
        ReserveModel reserveModel = new ReserveModel(reserveEntity);
        reserveModel = reserveRepository.save(reserveModel);
        reserveEntity.setId(reserveModel.getId());

        return reserveEntity;
    }

    @Override
    public Client registerClient(Client client, UUID reserveId) {
        ClientModel clientModel = new ClientModel(client, reserveId);
        clientRepository.save(clientModel);
        return client;
    }
}
