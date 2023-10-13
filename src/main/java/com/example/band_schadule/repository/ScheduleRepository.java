package com.example.band_schadule.repository;

import com.example.band_schadule.domain.entity.Schedule;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select s " +
            "from Schedule s " +
            "where s.id = :id")
    Optional<Schedule> findAllByIdWithOptimisiticLock(@Param("id") Long id);


    @Query("select s " +
            "from Schedule s " +
            "where  Date(s.scheduleTime) = Date(:currentTime) " +
            "and s.scheduleTime > :currentTime " +
            "and s.interest in :interests " +
            "and s.isValid = true " +
            "order by s.scheduleTime asc ")
    List<Schedule> getScheduleByMain(List<String> interests, @Param("currentTime") LocalDateTime currentTime);

    List<Schedule> findByCommunityId(Long communityId);
    @Modifying
    @Query("update Schedule s " +
            "set s.memberName = :memberName " +
            "where s.memberId = :memberId")
    void updateAlbumdMemberName(@Param("memberName") String memberName, @Param("memberId") Long memberId);
    @Modifying
    @Query("update Schedule s " +
            "set s.memberImage = :memberImage " +
            "where s.memberId = :memberId")
    void updateAlbumMemberImage(@Param("memberImage") String memberImage, @Param("memberId") Long memberId);
    @Modifying
    @Query("update Schedule s " +
            "set s.memberImage = :memberImage," +
            "s.memberName = :memberName " +
            "where s.memberId = :memberId")
    void updateAlbumMemberImageAndMemberName(@Param("memberName")String memberName, @Param("memberImage") String memberImage, @Param("memberId") Long memberId);


    List<Schedule> findAllBycommunityId(Long communityId);

    Optional<Schedule> findByMemberId(Long userId);

    @Modifying
    @Query("update Schedule s " +
            "set s.isValid = false " +
            "where s.memberId = :memberId")
    void memberDelete(@Param("memberId") Long userId);

}

