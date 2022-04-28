package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, String>, JpaSpecificationExecutor<Ville> {
    List<Ville> findAllByCodePostal(String codePostal);
    Ville findByCodePostal(String codePostal);
    List<Ville> findAllByCodeCommuneINSEE(String codeCommuneINSEE);
    Ville findByCodeCommuneINSEE(String codeCommuneINSEE);
    List<Ville> findAllBy();

    Ville findByNomCommune(String nomCommune);
    Ville findBy();

}
