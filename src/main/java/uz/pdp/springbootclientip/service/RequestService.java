package uz.pdp.springbootclientip.service;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {
    String getClientIPAddress(HttpServletRequest request);
}
