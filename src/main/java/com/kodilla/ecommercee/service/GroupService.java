package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupMapper groupMapper;

    public Group save(final Group group) {
        return groupRepository.save(group);
    }

    public GroupDto create(GroupDto groupDto) {
        Group group = groupMapper.map(groupDto);
        return groupMapper.mapToDto(save(group));
    }

    public GroupDto update(GroupDto groupDto) {
        Group group = groupRepository.findById(groupDto.getId()).orElseThrow(() -> new EntityNotFoundException(Group.class, groupDto.getId()));
        return groupMapper.mapToDto(save(group));
    }

    public List<GroupDto> getGroups() {
        return groupMapper.mapToDtoList(groupRepository.findAll());
    }

    public GroupDto getGroup(final Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return groupMapper.mapToDto(group.orElseThrow(() -> new EntityNotFoundException(Group.class, id)));
    }
}
