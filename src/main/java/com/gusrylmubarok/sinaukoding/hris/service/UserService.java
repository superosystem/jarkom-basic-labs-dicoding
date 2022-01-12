package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.common.StatusCode;
import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.UserDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO dao;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected BaseDAO<User> getDAO() {
        return dao;
    }

    @Autowired
    private BankService bankService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DivisionService divisionService;

    @Transactional
    public User register(User param, User.Role role) {
        User reference = dao.findByUsername(param);

        if (reference.getUsername() != null) {
            return null;
        } else {
            param.setRole(role);
            param.setPassword(BCrypt.hashpw(param.getPassword(), BCrypt.gensalt()));

            param.setBank(bankService.findByName(param.getBank()));
            param.setCompany(companyService.findByName(param.getCompany()));
            param.setPosition(positionService.findByName(param.getPosition()));
            param.setDivision(divisionService.findByName(param.getDivision()));

            dao.save(param);

            return param;
        }
    }

    @Transactional
    public RestResult login(User param) {
        RestResult result = new RestResult(StatusCode.PASSWORD_OR_USER_NOT_REGISTERED);

        User currentUser = dao.findByUsername(param);

        if (currentUser == null) {
            return result;
        } else if (currentUser.getPassword() != null &&
                BCrypt.checkpw(param.getPassword(), currentUser.getPassword())) {

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(currentUser.getRole().name()));
            UserDetails userDetails = new org.springframework.security.core.userdetails.
                    User(currentUser.getUsername(), currentUser.getPassword(), authorities);

            currentUser.setToken(jwtTokenService.generateToken(userDetails));

            result.setData(currentUser);
            result.setStatus(StatusCode.LOGIN_SUCCESS);
        } else {
            result.setStatus(StatusCode.LOGIN_FAILED);
        }

        return result;
    }

    @Transactional
    public User update(User entity) {
        if (entity.getId() != null) {
            User reference = getDAO().findReference(entity.getId());

            reference.setNoRekening(entity.getNoRekening() != null ? entity.getNoRekening() : reference.getNoRekening());
            reference.setDateOfBirth(entity.getDateOfBirth() != null ? entity.getDateOfBirth() : reference.getDateOfBirth());
            reference.setDomicileAddress(entity.getDomicileAddress() != null ? entity.getDomicileAddress() : reference.getDomicileAddress());
            reference.setPendidikanTerakhir(entity.getPendidikanTerakhir() != null ? entity.getPendidikanTerakhir() : reference.getPendidikanTerakhir());
            reference.setMaritalStatus(entity.getMaritalStatus() != null ? entity.getMaritalStatus() : reference.getMaritalStatus());
            reference.setNickName(entity.getNickName() != null ? entity.getNickName() : reference.getNickName());
            reference.setNoBpjsKetenagakerjaan(entity.getNoBpjsKetenagakerjaan() != null ? entity.getNoBpjsKetenagakerjaan() : reference.getNoBpjsKetenagakerjaan());
            reference.setNoBpjsKesehatan(entity.getNoBpjsKesehatan() != null ? entity.getNoBpjsKesehatan() : reference.getNoBpjsKesehatan());
            reference.setNoKtp(entity.getNoKtp() != null ? entity.getNoKtp() : reference.getNoKtp());
            reference.setNpwp(entity.getNpwp() != null ? entity.getNpwp() : reference.getNpwp());
            reference.setPhone(entity.getPhone() != null ? entity.getPhone() : reference.getPhone());
            reference.setPlaceOfBirth(entity.getPlaceOfBirth() != null ? entity.getPlaceOfBirth() : reference.getPlaceOfBirth());
            reference.setProfileName(entity.getProfileName() != null ? entity.getProfileName() : reference.getProfileName());
            reference.setReligion(entity.getReligion() != null ? entity.getReligion() : reference.getReligion());
            reference.setResidenceAddress(entity.getResidenceAddress() != null ? entity.getResidenceAddress() : reference.getResidenceAddress());

            return entity;
        }
        return null;
    }

    public User findByUsername(User param) {
        return dao.findByUsername(param);
    }
}
