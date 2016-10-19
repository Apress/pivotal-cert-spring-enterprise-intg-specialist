package net.lkrnac.book.eiws.chapter03.ws.error.server;

import localhost._10306._0306_ws_error_service.UserDetailsResponse;
import localhost._10306._0306_ws_error_service.UserRequest;

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
    } else if ("generic.client.error".equals(userRequest.getEmail())) {
      throw new IllegalStateException("Generic client error");
    } else if ("specific.client.error".equals(userRequest.getEmail())) {
      throw new CustomErrorException("Specific client error");
    } else {
      throw new NullPointerException("Server error");
    }
    return userDetails;
  }
}
