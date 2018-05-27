package com.inredec.ATutorBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mark")
//@IdClass(MarkId.class)
public class Mark implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Float value;

}


