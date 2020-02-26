package br.com.hivecloud.transportadora.web.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "modal")
public class ModalEntity {

	@Id
	@Column(name = "id_modal")
	private Long idModal;

	@Column(nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "modais")
	private List<TransportadoraEntity> transportadoras = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdModal() {
		return idModal;
	}

	public void setIdModal(Long idModal) {
		this.idModal = idModal;
	}

	public List<TransportadoraEntity> getTransportadoras() {
		return transportadoras;
	}

	public void setTransportadoras(List<TransportadoraEntity> transportadoras) {
		this.transportadoras = transportadoras;
	}

}
