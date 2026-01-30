//repository layer, makes direct contact with database

package com.infosys.irs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.irs.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

    
}

//This repository class just extends JpaRepository which provides 
// JPA related methods such as saving, deleting, and finding entities.