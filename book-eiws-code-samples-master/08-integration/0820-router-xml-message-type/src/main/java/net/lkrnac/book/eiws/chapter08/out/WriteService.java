package net.lkrnac.book.eiws.chapter08.out;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WriteService {
  private WriteRepository writeRepository;

  @Autowired
  public WriteService(WriteRepository writeRepository) {
    super();
    this.writeRepository = writeRepository;
  }

  public boolean writeRoute1(String message) {
    log.info("Route 1 hit with message: " + message);
    return writeRepository.persist(message) == 1;
  }

  public boolean writeRoute2(int message) {
    log.info("Route 2 hit with message: " + message);
    return writeRepository.persist("message" + message) == 1;
  }
}
