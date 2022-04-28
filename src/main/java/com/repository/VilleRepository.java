package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, String>, JpaSpecificationExecutor<Ville> {
    List<Ville> findAllByCodePostal(String CodePostal);
    Ville findByCodePostal(String CodePostal);
    List<Ville> findAllByCodeCommuneINSEE(String CodeCommuneInsee);
    Ville findByCodeCommuneINSEE(String CodeCommuneInsee);
    List<Ville> findAll();

    Ville findOne();

}
