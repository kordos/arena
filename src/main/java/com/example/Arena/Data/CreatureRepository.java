package com.example.Arena.Data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CreatureRepository extends CrudRepository<CreatureEntity, Integer> {
}
