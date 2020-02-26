package br.com.hivecloud.transportadora.web.dto;

import javax.validation.constraints.NotBlank;

public class TransportadoraFlatDto {

	private Long idTransportadora;
	@NotBlank(message = "email is mandatory")
	private String email;
	@NotBlank(message = "Name is mandatory")
	private String nome;
	@NotBlank(message = "empresa is mandatory")
	private String empresa;
	@NotBlank(message = "telefone is mandatory")
	private String telefone;
	@NotBlank(message = "rua is mandatory")
	private String rua;
	@NotBlank(message = "numero is mandatory")
	private String numero;
	@NotBlank(message = "bairro is mandatory")
	private String bairro;
	@NotBlank(message = "cidade is mandatory")
	private String cidade;
	@NotBlank(message = "uf is mandatory")
	private String uf;
	@NotBlank(message = "termo is mandatory")
	private Boolean termo;
	private String whatsapp;
	@NotBlank(message = "Name is mandatory")
	private String cep;
	private String cnpj;
	private String logo;
	private String celular;

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

	public Long getIdTransportadora() {
		return idTransportadora;
	}

	public void setIdTransportadora(Long idTransportadora) {
		this.idTransportadora = idTransportadora;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}
