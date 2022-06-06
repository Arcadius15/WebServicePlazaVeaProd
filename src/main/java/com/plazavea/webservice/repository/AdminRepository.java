package com.plazavea.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {
    
}
