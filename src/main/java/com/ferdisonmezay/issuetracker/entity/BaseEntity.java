package com.ferdisonmezay.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Integer id;

  public Integer getId() { return id; }
  public void setId(Integer id) {	this.id = id; }
}
