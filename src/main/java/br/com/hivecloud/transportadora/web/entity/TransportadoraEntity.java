package br.com.hivecloud.transportadora.web.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "transportadoras")
public class TransportadoraEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_transportadora")
	private long idTransportadora;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String empresa;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private String rua;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private String bairro;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = false)
	private String uf;
	@Column(nullable = false)
	private String cnpj;
	@Column(nullable = false)
	private String logo;
	@Column(nullable = false)
	private Boolean termo;
	@Column
	private String whatsapp;
	@Column
	private String cep;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "transportadoras_modais", joinColumns = {
			@JoinColumn(name = "idTransportadora") }, inverseJoinColumns = { @JoinColumn(name = "idModal") })
	

	Set<ModalEntity> modais = new HashSet<>();

	
	public void addModal(ModalEntity modal) {
		modais.add(modal);
		modal.getTransportadoras().add(this);
	}

	public void removeModal(ModalEntity modal) {
		modais.remove(modal);
		modal.getTransportadoras().remove(this);
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Boolean getTermo() {
		return termo;
	}

	public void setTermo(Boolean termo) {
		this.termo = termo;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@OrderBy("name asc")
	public Set<ModalEntity> getModais() {
		return modais;
	}

	public void setModais(Set<ModalEntity> modais) {
		this.modais = modais;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public long getIdTransportadora() {
		return idTransportadora;
	}

	public void setIdTransportadora(long idTransportadora) {
		this.idTransportadora = idTransportadora;
	}
}
