package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.GameListDTO;
import com.devsuperior.demo.entities.GameList;
import com.devsuperior.demo.projections.GameMinProjection;
import com.devsuperior.demo.repositories.GameListRepository;
import com.devsuperior.demo.repositories.GameRepository;



@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
		
	}
	@Transactional
	public void move(Long listId, int sourcecIndex, int destinationIndex) {
		
		List<GameMinProjection>list = gameRepository.searchByList(listId);
	
		GameMinProjection obj = list.remove(sourcecIndex);
		list.add(destinationIndex,obj);
		
		int min = sourcecIndex < destinationIndex ? sourcecIndex : destinationIndex;
		int max = sourcecIndex < destinationIndex ? destinationIndex : destinationIndex;
	
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
	
	
}
