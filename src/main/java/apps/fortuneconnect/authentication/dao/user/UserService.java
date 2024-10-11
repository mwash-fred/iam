package apps.fortuneconnect.authentication.dao.user;

import apps.fortuneconnect.authentication.dao.attempt.LoginAttemptService;
import apps.fortuneconnect.authentication.dto.UserDto;
import apps.fortuneconnect.authentication.exceptions.ResourceNotFoundException;
import apps.fortuneconnect.authentication.utils.enums.OperationEvents;
import apps.fortuneconnect.authentication.utils.enums.QueryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    public static final String NO_USER_FOUND = "No such user exists";
    public static final String NOT_ACCEPTED_ACTION = " is not an accepted action";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    User checkerUser;

    public UserDto create(UserDto request) {
        User user = userDTOToModel(request);
        user.setPhoneNo(trimPlusFromPhoneNumber(user.getPhoneNo()));
        user.setPassword(passwordEncoder.encode(request.password()));
        User record = userRepository.save(user);
        return userModelToDTO(record);
    }

    public UserDto read(String value, boolean deleted, String queryType) {
        QueryType event = QueryType.valueOf(queryType.toUpperCase());
        switch (event){
            case UID -> {
                User record  = userRepository.findByUidAndDeletedFlag(UUID.fromString(value), deleted)
                        .orElseThrow(() -> new ResourceNotFoundException(NO_USER_FOUND));
                return userModelToDTO(record);
            }
            case EMAIL -> {
                return userModelToDTO(userRepository.findByEmailAndDeletedFlag(value, deleted)
                        .orElseThrow(() -> new ResourceNotFoundException(NO_USER_FOUND)));
            }
            default -> {
                throw new IllegalArgumentException(queryType+ "is not an allowed query type here");
            }
        }
    }

    public UserDto update(String uid, String action, UserDto request) {
        OperationEvents event;
        try {
            event = OperationEvents.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(action + NOT_ACCEPTED_ACTION);
        }
        User user = userDTOToModel(request);
        this.checkerUser = userRepository.findByUidAndDeletedFlag(UUID.fromString(uid), false)
                .orElseThrow(() -> new ResourceNotFoundException(NO_USER_FOUND));
        switch (event) {
            case DELETE -> {
                delete(user);
            }
            case PASSWORD_RESET -> {
                user.setFirstLogin(false);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            default -> {
                throw new RuntimeException(action + NOT_ACCEPTED_ACTION);
            }
        }
        return userModelToDTO(userRepository.save(user));
    }

    public Page<UserDto> getAll(Pageable pageable, boolean deleted) {
        Page<User> UserPage = userRepository.findByDeletedFlag(deleted, pageable);
        List<UserDto> userDtoList = UserPage.getContent()
                .stream().map(UserService::userModelToDTO).collect(Collectors.toList());
        return new PageImpl<>(userDtoList, pageable, UserPage.getTotalElements());
    }


    public void passwordReset(UserDto requestDto){
        User user = userRepository.findByEmailAndDeletedFlag(requestDto.email(), false)
                .orElseThrow(() -> new ResourceNotFoundException(NO_USER_FOUND));
        user.setFirstLogin(true);
        userRepository.save(user);
    }

    private void delete(User user) {
        user.setDeletedFlag(true);
        user.setDeletedTime(LocalDateTime.now());
    }

    public Page<UserDto> searchUser(String query, boolean deleted, Pageable pageable){
        Page<User> UserPage = userRepository.findByEmailAndDeletedFlag(
                query.toLowerCase(), deleted, pageable);
        List<UserDto> userDtoList = UserPage.getContent()
                .stream()
                .map(UserService::userModelToDTO)
                .toList();
        return new PageImpl<>(userDtoList, pageable, UserPage.getTotalElements());
    }

    public static String trimPlusFromPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+");
        return pattern.matcher(phoneNumber).replaceAll("");
    }

    public static UserDto userModelToDTO(User users) {
        Function<User, UserDto> userDtoFunction = user -> new UserDto(user.getUid().toString(), user.getFirstName(),
                user.getMiddleName(), user.getLastName(), user.getEmail(), user.getPhoneNo(),
                user.getPostedTime(),  user.getModifiedTime(), user.getDeletedFlag(), user.getFirstLogin(),
                user.getIsEnabled(), user.getIsCredentialsExpired(), user.getIsAccountLocked(), user.getIsAccountExpired(),
                Optional.ofNullable(user.getAttempts())
                        .map(attempts -> attempts.stream().map(LoginAttemptService::loginAttemptsModelToDTO).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()), user.getPassword());
        return userDtoFunction.apply(users);
    }

    public static User userDTOToModel(UserDto userDto) {
        Function<UserDto, User> usersFunction = dto -> User.builder()
                .deletedFlag(Optional.ofNullable(dto.deletedFlag()).orElse(false))
                .firstLogin(Optional.ofNullable(dto.firstLogin()).orElse(false))
                .email(dto.email())
                .uid(UUID.fromString(dto.uid()))
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .middleName(dto.middleName())
                .isEnabled(dto.isEnabled())
                .isAccountLocked(dto.isAccountLocked())
                .phoneNo(trimPlusFromPhoneNumber(dto.phoneNo()))
                .deletedFlag(dto.deletedFlag())
                .password(dto.password())
                .build();
        return usersFunction.apply(userDto);
    }

}
