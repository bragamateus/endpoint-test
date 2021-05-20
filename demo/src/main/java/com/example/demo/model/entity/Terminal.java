package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Terminal {
	
	public Terminal(){
		
	}
	
	public Terminal(Integer logic, String serial, String model, Integer sam, String ptid,
			Integer plat, String version, Integer mxr, String verfm) {
		
		this.logic = logic;
		this.serial = serial;
		this.model = model;
		this.sam = sam;
		this.ptid = ptid;
		this.plat = plat;
		this.version = version;
		this.mxr = mxr;
		this.verfm = verfm;
		
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer logic;

    private String serial;

    private String model;

    private Integer sam;

    private String ptid;

    private Integer plat;

    private String version;

    private Integer mxr;

    private String verfm;

}
