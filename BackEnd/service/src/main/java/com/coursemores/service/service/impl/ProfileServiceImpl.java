package com.coursemores.service.service.impl;

import com.coursemores.service.domain.User;
import com.coursemores.service.dto.profile.UserInfoResDto;
import com.coursemores.service.dto.profile.UserInfoUpdateReqDto;
import com.coursemores.service.repository.UserRepository;
import com.coursemores.service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    @Override
    public UserInfoResDto getMyProfile(Long userId) {
        // userId의 User 가져오기
        User user = userRepository.findByIdAndDeleteTimeIsNull(userId)
                .orElseThrow(() -> new NullPointerException("해당 유저를 찾을 수 없습니다."));

        return UserInfoResDto.builder()
                .nickname(user.getNickname())
                .age(user.getAge())
                .gender(user.getGender())
                .profileImage(user.getProfileImage())
                .build();
    }

    @Override
    public void alramSetting(Long userId) {
        // 알람 셋팅
    }

    @Override
    @Transactional
    public void updateUserInfo(Long userId, UserInfoUpdateReqDto userInfoUpdateReqDto) {
        // userId의 User 가져오기
        User user = userRepository.findByIdAndDeleteTimeIsNull(userId)
                .orElseThrow(() -> new NullPointerException("해당 유저를 찾을 수 없습니다."));

        //userInfoUpdateReqDto 기반으로 user정보 수정
        user.update(userInfoUpdateReqDto);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        // userId의 User 가져오기
        User user = userRepository.findByIdAndDeleteTimeIsNull(userId)
                .orElseThrow(() -> new NullPointerException("해당 유저를 찾을 수 없습니다."));

        // deleteTime 추가
        user.delete();
    }
}