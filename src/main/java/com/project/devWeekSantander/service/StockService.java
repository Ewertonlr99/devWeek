package com.project.devWeekSantander.service;

import com.project.devWeekSantander.exceptions.BusinessException;
import com.project.devWeekSantander.exceptions.NotFoundException;
import com.project.devWeekSantander.mapper.StockMapper;
import com.project.devWeekSantander.model.Stock;
import com.project.devWeekSantander.model.dto.StockDTO;
import com.project.devWeekSantander.repository.StockRepository;
import com.project.devWeekSantander.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;
    private Object Stock;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);

        return mapper.toDto(stock);

    }

    @Transactional
    public StockDTO update(StockDTO dto) {

        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);

        return mapper.toDto(stock);
    }

    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        repository.deleteById(id);

        return dto;

    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);

    }
}
