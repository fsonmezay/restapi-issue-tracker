package com.ferdisonmezay.issuetracker.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ferdisonmezay.issuetracker.dao.DeveloperDao;
import com.ferdisonmezay.issuetracker.entity.DeveloperEntity;

@Service
public class DeveloperService{

  @Autowired
  private DeveloperDao developerDao;

  public DeveloperEntity add(String name) {
    DeveloperEntity developer = new DeveloperEntity(name);
    return developerDao.save(developer);
  }

  public List<DeveloperEntity> getDevelopers(){ return developerDao.findAll(); }
  public DeveloperEntity getById(Integer id) { return developerDao.findOne(id); }
  public DeveloperEntity update(DeveloperEntity developer) { return developerDao.save(developer); }
  public void delete(Integer developerId) { developerDao.delete(developerId); }
  public Long getDeveloperCount() { return developerDao.count(); }
}
