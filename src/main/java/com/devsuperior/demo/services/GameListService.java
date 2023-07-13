package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.GameListDTO;
import com.devsuperior.demo.entities.GameList;
import com.devsuperior.demo.repositories.GameListRepository;



@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
		
	}

}
