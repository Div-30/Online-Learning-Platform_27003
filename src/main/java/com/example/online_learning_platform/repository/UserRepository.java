package com.example.online_learning_platform.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.online_learning_platform.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Boolean existsByEmail(String email);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT u FROM User u WHERE u.location.type = 'PROVINCE' AND (u.location.code = :provinceCode OR u.location.name = :provinceName) OR u.location.parent.type = 'PROVINCE' AND (u.location.parent.code = :provinceCode OR u.location.parent.name = :provinceName) OR u.location.parent.parent.type = 'PROVINCE' AND (u.location.parent.parent.code = :provinceCode OR u.location.parent.parent.name = :provinceName) OR u.location.parent.parent.parent.type = 'PROVINCE' AND (u.location.parent.parent.parent.code = :provinceCode OR u.location.parent.parent.parent.name = :provinceName) OR u.location.parent.parent.parent.parent.type = 'PROVINCE' AND (u.location.parent.parent.parent.parent.code = :provinceCode OR u.location.parent.parent.parent.parent.name = :provinceName)")
    List<User> findByProvinceCodeOrProvinceName(@Param("provinceCode") String provinceCode,
            @Param("provinceName") String provinceName);

    @Query("SELECT u FROM User u WHERE u.location.type = 'DISTRICT' AND (u.location.code = :districtCode OR u.location.name = :districtName) OR u.location.parent.type = 'DISTRICT' AND (u.location.parent.code = :districtCode OR u.location.parent.name = :districtName) OR u.location.parent.parent.type = 'DISTRICT' AND (u.location.parent.parent.code = :districtCode OR u.location.parent.parent.name = :districtName) OR u.location.parent.parent.parent.type = 'DISTRICT' AND (u.location.parent.parent.parent.code = :districtCode OR u.location.parent.parent.parent.name = :districtName)")
    List<User> findByDistrictCodeOrDistrictName(@Param("districtCode") String districtCode,
            @Param("districtName") String districtName);

    @Query("SELECT u FROM User u WHERE u.location.type = 'SECTOR' AND (u.location.code = :sectorCode OR u.location.name = :sectorName) OR u.location.parent.type = 'SECTOR' AND (u.location.parent.code = :sectorCode OR u.location.parent.name = :sectorName) OR u.location.parent.parent.type = 'SECTOR' AND (u.location.parent.parent.code = :sectorCode OR u.location.parent.parent.name = :sectorName)")
    List<User> findBySectorCodeOrSectorName(@Param("sectorCode") String sectorCode,
            @Param("sectorName") String sectorName);

    @Query("SELECT u FROM User u WHERE u.location.type = 'CELL' AND (u.location.code = :cellCode OR u.location.name = :cellName) OR u.location.parent.type = 'CELL' AND (u.location.parent.code = :cellCode OR u.location.parent.name = :cellName)")
    List<User> findByCellCodeOrCellName(@Param("cellCode") String cellCode,
            @Param("cellName") String cellName);

    @Query("SELECT u FROM User u WHERE u.location.type = 'VILLAGE' AND (u.location.code = :villageCode OR u.location.name = :villageName)")
    List<User> findByVillageCodeOrVillageName(@Param("villageCode") String villageCode,
            @Param("villageName") String villageName);
}