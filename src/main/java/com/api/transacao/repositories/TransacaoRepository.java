package com.api.transacao.repositories;

import com.api.transacao.models.TransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoModel, UUID> {
    @Query(
            value = "select * from transacao where transacao.id_cliente=?1 and data between ?2 and ?3",
            nativeQuery = true
    )
    List<TransacaoModel> findAll(UUID idCliente, Date dataInicial, Date dataFinal);
}
