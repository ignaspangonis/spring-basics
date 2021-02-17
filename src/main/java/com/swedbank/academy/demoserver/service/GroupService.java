package com.swedbank.academy.demoserver.service;

import com.swedbank.academy.demoserver.exception.GroupExistsException;
import com.swedbank.academy.demoserver.exception.GroupNotFoundException;
import com.swedbank.academy.demoserver.person.Group;

import java.util.List;

public interface GroupService {
    public List<Group> getAll();
    public Group findById(Long id) throws GroupNotFoundException;
    Group save(Group group) throws GroupExistsException;
    void update(Group group);
    void delete(long id) throws GroupNotFoundException;
    void deleteAll();
    void saveAndFlush(Group group);
}
