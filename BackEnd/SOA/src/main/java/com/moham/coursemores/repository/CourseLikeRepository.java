package com.moham.coursemores.repository;

import com.moham.coursemores.domain.CourseLike;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseLikeRepository extends JpaRepository<CourseLike, Long> {

    Optional<CourseLike> findByUserIdAndCourseId(Long userId, Long courseId);

    List<CourseLike> findByFlagIsTrueAndRegisterTimeAfter(LocalDateTime standardTime);

}