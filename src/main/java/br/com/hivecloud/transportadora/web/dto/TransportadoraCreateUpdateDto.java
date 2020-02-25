package br.com.hivecloud.transportadora.web.dto;

import java.util.ArrayList;
import java.util.List;

public class TransportadoraCreateUpdateDto extends TransportadoraFlatDto {

	private List<Long> modais = new ArrayList<>();

	public List<Long> getModais() {
		return modais;
	}

	public void setModais(List<Long> modais) {
		this.modais = modais;
	}
}
