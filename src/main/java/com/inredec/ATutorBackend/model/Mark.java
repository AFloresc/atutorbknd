package com.inredec.ATutorBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mark")
@EntityListeners(AuditingEntityListener.class)
//@IdClass(MarkId.class)
public class Mark implements Serializable {


}


