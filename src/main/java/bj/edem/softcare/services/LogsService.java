package bj.edem.softcare.services;

import bj.edem.softcare.entities.Logs;
import bj.edem.softcare.entities.Users;
import bj.edem.softcare.repositories.LogsRepository;
import bj.edem.softcare.repositories.UserRepository;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsService {

    private static final int PAGE_ELEMENT_SIZE_ADMIN = 12;

    @Autowired
    LogsRepository logsRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Logs findById(Long id) {
        return logsRepository.getOne(id);
    }

    @Transactional
    public void save(String action, HttpServletRequest request) throws UnknownHostException {
        System.out.println("Saving logs .................");
        String ipAdresse = this.getIpAddr(request);
        Users user = null;
        try {
            user = userRepository.findByUsername(request.getUserPrincipal().getName());
        } catch (Exception e) {
            System.out.println("user null .................");
        }

        logsRepository.save(new Logs(action, new Date(), new Date(), ipAdresse,
                ipAdresse, user));
        System.out.println("Logs saved .................");
    }

    @Transactional
    public List<Logs> findByUsers(Users user) {
        return logsRepository.findByUser(user);
        //return logs;
    }

    @Transactional
    public List<Logs> findAll() {
        return logsRepository.findAll();
    }

    private String getIpAddr(HttpServletRequest request) throws UnknownHostException {

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            // System.err.println("ETAPE N°1 IP ADRESS  = " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            // System.err.println("ETAPE N°2 IP ADRESS  = " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            //System.err.println("ETAPE N°3 IP ADRESS  = " + ip);
        }

        if (ip.contains(":")) {
            ip = "localhost";
        }
        return ip;
    }
}
