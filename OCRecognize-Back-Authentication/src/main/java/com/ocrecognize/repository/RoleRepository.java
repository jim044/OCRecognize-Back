package com.ocrecognize.repository;

import com.ocrecognize.entity.RoleEntity;
import com.ocrecognize.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
