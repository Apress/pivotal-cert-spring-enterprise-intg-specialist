package net.lkrnac.book.eiws.chapter03.ws.interceptor.server;

import localhost._10305._0305_ws_interceptor_service.UserDetailsResponse;
import localhost._10305._0305_ws_interceptor_service.UserRequest;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
  @PayloadRoot(namespace = ServerConfiguration.NAMESPACE, localPart = "UserRequest")
  @ResponsePayload
  public UserDetailsResponse getUserDetails(
      @RequestPayload UserRequest userRequest) {
    UserDetailsResponse userDetails = null;
    if ("lubos.krnac@gmail.com".equals(userRequest.getEmail())) {
      userDetails = new UserDetailsResponse();
      userDetails.setFirstName("Lubos");
      userDetails.setLastName("Krnac");
    } else {
      throw new IllegalStateException("Simulate error");
    }
    return userDetails;
  }
}
