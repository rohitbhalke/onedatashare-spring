package org.onedatashare.server.controller;

import org.onedatashare.server.model.useraction.UserAction;
import org.onedatashare.server.model.error.AuthenticationRequired;
import org.onedatashare.server.service.DbxService;
import org.onedatashare.server.service.VfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stork/ls")
public class ListController {

  @Autowired
  private DbxService dbxService;

  @Autowired
  private VfsService vfsService;

//  @PostMapping
//  public Object list(@RequestHeader HttpHeaders headers, @RequestBody UserAction userAction) {
//    String cookie = headers.getFirst("cookie");
//    if(userAction.credential == null) {
//      return new ResponseEntity<>(new AuthenticationRequired("oauth"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    else return dbxService.list(cookie, userAction);
//  }

  @PostMapping
  public Object list(@RequestHeader HttpHeaders headers, @RequestBody UserAction userAction) {
    String cookie = headers.getFirst("cookie");
    if(userAction.uri.contains("dropbox://")) {
      if(userAction.credential == null) {
        return new ResponseEntity<>(new AuthenticationRequired("oauth"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
      else return dbxService.list(cookie, userAction);
    }
    else return vfsService.list(cookie, userAction);
  }

//  @ExceptionHandler
//  public ResponseEntity<AuthenticationRequired> handle(AuthenticationRequired authenticationRequired) {
//    return new ResponseEntity<>(authenticationRequired, authenticationRequired.status);
//  }
}
