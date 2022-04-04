package com.example.springdata_01.Repository;

import com.example.springdata_01.Domain.User;
import com.example.springdata_01.Dto.HistoryUserDto;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

//    @Query("SELECT p.*, pu.*, u.* FROM product p " +
//            "  JOIN product_user pu ON p.id = pu.productId" +
//            "  JOIN user u on u.id = pu.userId" +
//            " WHERE u.id = :id ")
//    public List<Object> findHistory(@Param("id") Integer id);

}
