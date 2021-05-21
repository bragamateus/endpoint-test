package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {

	@Id
    private Integer logic;

	@NotNull
    private String serial;

	@NotNull
    private String model;

    private Integer sam;

    private String ptid;

    private Integer plat;

    @NotNull
    private String version;

    private Integer mxr;

    private Integer mxf;

    private String verfm;

}
