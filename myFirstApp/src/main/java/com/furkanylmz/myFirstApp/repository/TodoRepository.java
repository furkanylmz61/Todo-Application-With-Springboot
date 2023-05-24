package com.furkanylmz.myFirstApp.repository;

import com.furkanylmz.myFirstApp.model.entıty.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {


    public List<Todo> findByUsername(String username);

}
