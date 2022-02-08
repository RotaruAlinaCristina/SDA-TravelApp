package com.travelAppSpringBoot.service;

import com.travelAppSpringBoot.model.Tour;
import com.travelAppSpringBoot.model.User;
import com.travelAppSpringBoot.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;
//    @Autowired
//    private ConfirmationTokenService confirmationTokenService;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private EmailSender emailSender;
//    @Autowired
//    private AuthenticationManager authenticationManager;



//    public User login(User requestUser) {
//
//        User userFound = userRepo.findUserByEmail(requestUser.getEmail());
//        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
//                UsernamePasswordAuthenticationToken(requestUser.getUsername(), requestUser.getPassword());
//        try {
//            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
//            SecurityContext securityContext = SecurityContextHolder.getContext();
//            ((SecurityContext) securityContext).setAuthentication(authentication);
//
//            User user = (User) authentication.getPrincipal();
//            log.info("Logged in user: {}", user);
//            return userFound;
//
//        } catch (BadCredentialsException ex) {
//            return userFound;
//        }
//    }
//
//    public String logout() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            new SecurityContextLogoutHandler().logout(
//                    httpServletRequest,
//                    httpServletResponse,
//                    authentication
//            );
//        }
//        return "HttpStatus.OK";
//    }

//    public String register(User user) {
//        boolean userExists = userRepo
//                .findByEmail(user.getEmail())
//                .isPresent();
//        if (userExists) {
//            throw new IllegalStateException("email already taken");
//        }
//        String encodedPassword = bCryptPasswordEncoder
//                .encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        user.setAppUserRole(AppUserRole.USER);
//        userRepo.save(user);
//
//        String token = UUID.randomUUID().toString();
//
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                user
//        );
//        confirmationTokenService.saveConfirmationToken(confirmationToken);
//        userRepo.save(user);
//
//        //TODO: Send email
//        String link = "http://localhost:8080/users/confirm?token=" + token;
//        emailSender.send(
//                user.getEmail(),
//                buildEmail(user.getFirstName(), link));
//        return token;
//    }
//
//    @Transactional
//    public String confirmToken(String token) {
//        ConfirmationToken confirmationToken = confirmationTokenService
//                .getToken(token);
//        log.info("Token found");
//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("Email already confirmed");
//        }
//
//        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
//
//        if (expiredAt.isBefore(LocalDateTime.now())) {
//            throw new IllegalStateException("Token expired");
//        }
//        confirmationTokenService.setConfirmedAt(token);
//        enableUser(
//                confirmationToken.getUser().getEmail());
//        return "confirmed";
//    }


    public User createNewUser(User tour) {
        return userRepo.save(tour);
    }

    public Optional<User> findUserById(Long id) {
        return userRepo.findUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public User getUser(String email) {
        log.info("Fetching user {}", email);
        return userRepo.findUserByEmail(email);
    }

    public String updateUser(User user) {
        String response = "";
        Optional<User> updateUser = userRepo.findUserById(user.getId());
        if (updateUser.isPresent()) {
            userRepo.save(user);
            response = " The user was updated in DB";
        } else {
            response = " The user with id " + user.getId() + " was not found";
        }
        return response;
    }

    public String deleteUserById(Long id) {
        String response = "";
        Optional<User> userDeleted = userRepo.findUserById(id);
        if (userDeleted.isPresent()) {
            userRepo.deleteUserById(id);
            response = " The user was deleted";
        } else {
            response = " The user with id " + id + " don't exist!";
        }
        return response;
    }



//    public String signUpUser(User user) {
//        boolean userExists = userRepo
//                .findByEmail(user.getEmail())
//                .isPresent();
//        if (userExists) {
//            throw new IllegalStateException("email already taken");
//        }
//        String encodedPassword = bCryptPasswordEncoder
//                .encode(user.getPassword());
//
//        user.setPassword(encodedPassword);
//
//        userRepo.save(user);
//
//        String token = UUID.randomUUID().toString();
//
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                user
//        );
//
//        confirmationTokenService.saveConfirmationToken(
//                confirmationToken);
//
////        TODO: SEND EMAIL
//
//        return token;
//    }


    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
