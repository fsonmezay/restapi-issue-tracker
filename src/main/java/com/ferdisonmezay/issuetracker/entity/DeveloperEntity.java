package com.ferdisonmezay.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="DEVELOPERS")
public class DeveloperEntity extends BaseEntity {

  @Column(name="name")
  private String name;

  public DeveloperEntity() {}
  public DeveloperEntity(String name) { this.name = name; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
