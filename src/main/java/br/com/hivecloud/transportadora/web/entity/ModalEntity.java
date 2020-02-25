package br.com.hivecloud.transportadora.web.entity;

import java.util.HashSet;
import java.util.Set;

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
	private long idModal;
	@Column(nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "modais")
	private Set<TransportadoraEntity> transportadoras = new HashSet<>();


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<TransportadoraEntity> getTransportadoras() {
		return transportadoras;
	}

	public void setTransportadoras(Set<TransportadoraEntity> transportadoras) {
		this.transportadoras = transportadoras;
	}

	public long getIdModal() {
		return idModal;
	}

	public void setIdModal(long idModal) {
		this.idModal = idModal;
	}

}
