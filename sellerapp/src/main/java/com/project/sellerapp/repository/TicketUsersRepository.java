package com.project.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sellerapp.model.TicketUser;

@Repository
public interface TicketUsersRepository extends JpaRepository<TicketUser, Long>{

}
