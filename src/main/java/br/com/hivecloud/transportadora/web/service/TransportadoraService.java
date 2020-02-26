package br.com.hivecloud.transportadora.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hivecloud.transportadora.web.dao.ModalRepository;
import br.com.hivecloud.transportadora.web.dao.TransportadoraRepository;
import br.com.hivecloud.transportadora.web.dto.CriteriaDto;
import br.com.hivecloud.transportadora.web.dto.ModalDto;
import br.com.hivecloud.transportadora.web.dto.StatisticDto;
import br.com.hivecloud.transportadora.web.dto.TransportadoraCreateUpdateDto;
import br.com.hivecloud.transportadora.web.dto.TransportadoraDto;
import br.com.hivecloud.transportadora.web.dto.TransportadoraFlatDto;
import br.com.hivecloud.transportadora.web.entity.ModalEntity;
import br.com.hivecloud.transportadora.web.entity.Statistics;
import br.com.hivecloud.transportadora.web.entity.TransportadoraEntity;
import br.com.hivecloud.transportadora.web.exception.NotFoundException;
import br.com.hivecloud.transportadora.web.utils.BeanMapper;

@Service
@Transactional
public class TransportadoraService {

	@Autowired
	private TransportadoraRepository transportadoraDao;

	@Autowired
	private ModalRepository modalDao;

	
	
	@Autowired
	private BeanMapper mapper;

	public List<TransportadoraFlatDto> findAll() {
		List<TransportadoraEntity> list = transportadoraDao.findAll();
		return mapper.mapToList(list, TransportadoraFlatDto.class);
	}

	public TransportadoraDto findOne(Long id) {
		Optional<TransportadoraEntity> opt = transportadoraDao.findById(id);
		if(opt.isPresent()) {
			return mapper.map(opt.get(), TransportadoraDto.class);
		} else {
			throw new NotFoundException();
		}
		
	}

	public void create(TransportadoraCreateUpdateDto dto) {
		TransportadoraEntity create = mapper.map(dto, TransportadoraEntity.class);
		create.getModais().clear();
		TransportadoraEntity created = transportadoraDao.save(create);
		created.setModais(loadModaisFromDB(dto));
	}

	private List<ModalEntity> loadModaisFromDB(TransportadoraCreateUpdateDto dto) {
		List<ModalEntity> toBeOPersist = new ArrayList<>();
		for (ModalDto mDto : dto.getModais()) {		
			toBeOPersist.add(modalDao.findById(mDto.getIdModal()).get());
		}
		return toBeOPersist;
	}

	public void update(TransportadoraCreateUpdateDto dto) {
		Optional<TransportadoraEntity> opt = transportadoraDao.findById(dto.getIdTransportadora());
		if (!opt.isPresent()) {
			throw new NotFoundException();
		} else {
			TransportadoraEntity target = opt.get();
			mapper.map(dto, target);
			target.getModais().clear();
			target = transportadoraDao.save(target);
			target.setModais(loadModaisFromDB(dto));
		}

	}

	public void delete(Long id) {
		transportadoraDao.deleteById(id);

	}

	public List<TransportadoraFlatDto> findByCriteria(String empresa, String uf, String cidade, String modal) {
		List<TransportadoraEntity> list = transportadoraDao.findByCriteria(StringUtils.trimToNull(empresa), uf, cidade,
				modal);
		return mapper.mapToList(list, TransportadoraFlatDto.class);
	}

	public CriteriaDto criterias() {
		CriteriaDto dto = new CriteriaDto();
		List<Statistics> cidades = transportadoraDao.cidadeCount();
		dto.setCidades(mapper.mapToList(cidades, StatisticDto.class));
		List<Statistics> ufs = transportadoraDao.ufCount();
		dto.setUfs(mapper.mapToList(ufs, StatisticDto.class));
		List<Statistics> modais = transportadoraDao.modalCount();
		dto.setModais(mapper.mapToList(modais, StatisticDto.class));
		
		return dto;
	}

}
