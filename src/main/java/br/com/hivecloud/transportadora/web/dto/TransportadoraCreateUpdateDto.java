package br.com.hivecloud.transportadora.web.dto;

import java.util.ArrayList;
import java.util.List;

public class TransportadoraCreateUpdateDto extends TransportadoraFlatDto {

	private List<ModalDto> modais = new ArrayList<>();

	public List<ModalDto> getModais() {
		return modais;
	}

	public void setModais(List<ModalDto> modais) {
		this.modais = modais;
	}
}
