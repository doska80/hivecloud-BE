package br.com.hivecloud.transportadora.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hivecloud.transportadora.web.entity.ModalEntity;

public interface ModalRepository extends JpaRepository<ModalEntity, Long> {

}
