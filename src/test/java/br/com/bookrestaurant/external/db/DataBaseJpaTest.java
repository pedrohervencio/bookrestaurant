package br.com.bookrestaurant.external.db;

import br.com.bookrestaurant.entity.reserve.ReserveEntity;
import br.com.bookrestaurant.external.model.ClientModel;
import br.com.bookrestaurant.external.model.ReserveModel;

import br.com.bookrestaurant.utilsbytests.Util;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


public class DataBaseJpaTest {

    @InjectMocks
    private DataBaseJpa dataBase;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ReserveRepository reserveRepository;

    private AutoCloseable mocks;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        mocks.close();
    }


    @Nested
    class RegisterReserve {
        @Test
        @Severity(SeverityLevel.BLOCKER)
        void testShouldRegisterReserve() {
            ReserveModel reserveModel = new ReserveModel(Util.buildReserveEntitySaved());
            reserveModel.setId(Util.getUUID());
            when(reserveRepository.save(Mockito.any(ReserveModel.class)))
                    .thenReturn(reserveModel);
            ReserveEntity reserveEntity = dataBase
                    .registerReserve(Util.buildReserveEntity());
            verify(reserveRepository, times(1))
                    .save(Mockito.any(ReserveModel.class));
            assertThat(reserveEntity).isNotNull().isInstanceOf(ReserveEntity.class);
            assertThat(reserveEntity.getId()).isNotNull().isEqualTo(Util.getUUID());
        }

        @Test
        @Severity(SeverityLevel.BLOCKER)
        void shouldRegisterClient() {
            dataBase.registerClient(Util.buildClient(), Util.getUUID());
            verify(clientRepository, times(1))
                    .save(Mockito.any(ClientModel.class));
        }
    }

}
