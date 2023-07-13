package com.devsuperior.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.demo.entities.GameList;


public interface GameListRepository extends JpaRepository<GameList, Long> {

}
