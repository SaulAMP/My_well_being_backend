package com.example.economy.service;

import com.example.economy.entity.EconomyEntity;
import com.example.economy.exceptions.EconomyNotFoundException;
import com.example.economy.dto.EconomyDto;
import com.example.economy.repository.EconomyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EconomyService {
    @Autowired
    EconomyRepository economyRepository;


    public List<EconomyEntity> showAnswers(){
        return  economyRepository.findAll();
    }

    public Optional<EconomyEntity> GetResponseById(Long id) {
        return Optional.ofNullable(economyRepository.findById(id))
                .orElseThrow( () -> new EconomyNotFoundException(id) );
    }

    public EconomyEntity create(EconomyDto economyDto){
        EconomyEntity economyEntity = new EconomyEntity();
        economyEntity.setFirstAnswer(economyDto.getFirstAnswer());
        economyEntity.setSecondAnswer(economyDto.getSecondAnswer());
        economyEntity.setThirdAnswer(economyDto.getThirdAnswer());
        economyEntity.setIdUser(economyDto.getIdUser());
        economyRepository.save(economyEntity);
        return economyRepository.save(economyEntity);
    }
    public Optional<EconomyEntity> update(Long id ,EconomyDto economyDto) {
        return Optional.ofNullable(economyRepository.findById(id)
                .map(economyEntity -> {
                 economyEntity.setFirstAnswer(economyDto.getFirstAnswer());
                 economyEntity.setSecondAnswer(economyDto.getSecondAnswer());
                 economyEntity.setThirdAnswer(economyDto.getThirdAnswer());
                 economyEntity.setIdUser(economyDto.getIdUser());
                 return economyRepository.save(economyEntity);
                })
                .orElseThrow(() -> new EconomyNotFoundException(id)));
    }

    public Boolean DeleteAnswer(Long id) {
        if (economyRepository.findById(id).isPresent()){
            economyRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
