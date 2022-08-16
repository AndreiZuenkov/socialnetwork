package com.sadstatue.socialnetwork.persistence.repository;

import com.sadstatue.socialnetwork.persistence.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface IMessageRepo extends CrudRepository<Message, Long> {
}
