package com.upsmart.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.converter.ClientGroupConverter;
import com.upsmart.message.converter.GroupConverter;
import com.upsmart.message.domain.Group;
import com.upsmart.message.domain.ObjectGroup;
import com.upsmart.message.repository.GroupRepository;
import com.upsmart.message.repository.ObjectGroupRepository;
import com.upsmart.message.repository.SendObjectRepository;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author buby
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日
 */

@Service
public class GroupServiceImp implements GroupService {

    private static Logger logger = LoggerFactory.getLogger(GroupServiceImp.class);

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ObjectGroupRepository objectGroupRepository;

    GroupConverter groupConverter;

    ClientGroupConverter clientGroupConverter;

    @Autowired
    private SendObjectRepository sendObjectRepository;

    public List<Map<String, Object>> queryGroup() {
        List<Group> groups = this.groupRepository.findAll();
        List<Map<String, Object>> returnResult = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            int gid = groups.get(i).getGid();
            List<ObjectGroup> ogs = this.objectGroupRepository.findByGid(gid);
            for (int j = 0; j < ogs.size(); j++) {
                Map<String, Object> groupsMap = new HashMap<>();
                groupsMap.put("gid", gid);
                groupsMap.put("gname", groups.get(i).getGname());
                groupsMap.put("oname", this.sendObjectRepository.findByOid(ogs.get(j).getOid()).getOname());
                returnResult.add(groupsMap);
            }
        }
        return returnResult;
    }

    public List<Group> findAllgroup() {
        List<Group> group = new ArrayList<Group>();
        try {
            group = this.groupRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("return group fail");
        }
        return group;
    }

    public List<ObjectGroup> findAllgroupobject() {
        List<ObjectGroup> objectgroup = new ArrayList<ObjectGroup>();
        try {
            objectgroup = this.objectGroupRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("return groupobject fail");
        }
        return objectgroup;
    }

    public void addGroup(String gname, List<Integer> oid) {
        Group group = new Group();
        group.setGname(gname);
        Date date = new Date();
        group.setCreatetime(date);
        group.setModtime(date);
        try {
            this.groupRepository.save(group);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("add group fail");
        }
        int gid = this.groupRepository.findByGname(group.getGname()).getGid();
        try {
            for (int i = 0; i < oid.size(); i++) {
                ObjectGroup objectGroup = new ObjectGroup();
                objectGroup.setGid(gid);
                objectGroup.setOid(oid.get(i));
                this.objectGroupRepository.save(objectGroup);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("add object in group fail");
        }
    }

    public void deleteGroup(int gid) {
        List<ObjectGroup> objgrp = this.objectGroupRepository.findByGid(gid);
        try {
            for (int i = 0; i < objgrp.size(); i++) {
                ObjectGroup obj = new ObjectGroup();
                obj = objgrp.get(i);
                this.objectGroupRepository.delete(obj);
            }
            this.groupRepository.delete(this.groupRepository.findByGid(gid));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete group error");
        }
    }

    public void modGroup(int gid, String gname, List<Integer> oid) {
        Group group = this.groupRepository.findByGid(gid);
        group.setGname(gname);
        Date date = new Date();
        group.setModtime(date);
        try {
            this.groupRepository.save(group);
            List<ObjectGroup> objgrp = this.objectGroupRepository.findByGid(gid);
            for (int i = 0; i < objgrp.size(); i++) {
                ObjectGroup obj = new ObjectGroup();
                obj = objgrp.get(i);
                this.objectGroupRepository.delete(obj);
            }
            for (int i = 0; i < oid.size(); i++) {
                ObjectGroup objectGroup = new ObjectGroup();
                objectGroup.setGid(gid);
                objectGroup.setOid(oid.get(i));
                this.objectGroupRepository.save(objectGroup);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mod group fail");
        }
    }

    public boolean check(String gname) {
        try {
            if (null == this.groupRepository.findByGname(gname)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}