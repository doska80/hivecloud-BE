package br.com.hivecloud.transportadora.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hivecloud.transportadora.web.dto.CriteriaDto;
import br.com.hivecloud.transportadora.web.dto.TransportadoraCreateUpdateDto;
import br.com.hivecloud.transportadora.web.dto.TransportadoraDto;
import br.com.hivecloud.transportadora.web.dto.TransportadoraFlatDto;
import br.com.hivecloud.transportadora.web.service.TransportadoraService;

@RestController
@RequestMapping("/api/transportadoras")
public class TransportadoraRestController {

	@Autowired
	private TransportadoraService service;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<TransportadoraFlatDto> findAll() {
		return service.findAll();
	}

	@RequestMapping(path = "/filtre", method = RequestMethod.GET)
	public List<TransportadoraFlatDto> findByCriteria(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "uf", required = false) String uf,
			@RequestParam(name = "cidade", required = false) String cidade,
			@RequestParam(name = "modal", required = false) String modal) {
		return service.findByCriteria(nome, uf, cidade, modal);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)  
	public ResponseEntity<TransportadoraFlatDto> findOne(@PathVariable Long id) {
		return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<TransportadoraFlatDto> create(@RequestBody TransportadoraCreateUpdateDto dto) {
		service.create(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT)
	public ResponseEntity<TransportadoraFlatDto> update(@RequestBody TransportadoraCreateUpdateDto dto) {
		service.update(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/criterias", method = RequestMethod.GET)
	public ResponseEntity<CriteriaDto> criterias() {
		return new ResponseEntity<>(service.criterias(), HttpStatus.OK);
	}
}
