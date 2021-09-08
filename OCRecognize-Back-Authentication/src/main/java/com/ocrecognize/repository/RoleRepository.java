package com.ocrecognize.repository;

import com.ocrecognize.entity.RoleEntity;
import com.ocrecognize.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
