package uz.pdp.springbootclientip.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class RequestServiceImpl implements RequestService {

    private Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Override
    public String getClientIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (!StringUtils.hasLength(ipAddress) || "unknown".equals(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (!StringUtils.hasLength(ipAddress) || "unknown".equals(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (!StringUtils.hasLength(ipAddress) || "unknown".equals(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        if (StringUtils.hasLength(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }
}
