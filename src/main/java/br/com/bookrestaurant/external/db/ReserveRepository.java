package br.com.bookrestaurant.external.db;

import br.com.bookrestaurant.external.model.ReserveModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReserveRepository extends JpaRepository<ReserveModel, UUID> {

}
