package com.example.springdata_01.Repository;

import com.example.springdata_01.Domain.ProductUser;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUserRepository extends CrudRepository<ProductUser, Long> {

    @Query("select * from product_user where userId = :id")
    public List<ProductUser> findByUserId(@Param("id") Long id);
}
