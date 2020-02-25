package br.com.hivecloud.transportadora.web.dto;

import java.util.List;

public class CriteriaDto {

	private List<StatisticDto> cidades;
	private List<StatisticDto> ufs;
	private List<StatisticDto> modais;

	public List<StatisticDto> getCidades() {
		return cidades;
	}

	public void setCidades(List<StatisticDto> cidades) {
		this.cidades = cidades;
	}

	public List<StatisticDto> getUfs() {
		return ufs;
	}

	public void setUfs(List<StatisticDto> ufs) {
		this.ufs = ufs;
	}

	public List<StatisticDto> getModais() {
		return modais;
	}

	public void setModais(List<StatisticDto> modais) {
		this.modais = modais;
	}

}
