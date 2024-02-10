package com.sociedade.catalogoback.domain.company;

import com.sociedade.catalogoback.domain.GenericEntity;
import com.sociedade.catalogoback.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@EqualsAndHashCode(callSuper = true)
@Table()
@Entity()
@Data
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name="notDeleted", parameters=@ParamDef( name="isDeleted", type=Boolean.class ) )
@Filter(name="notDeleted", condition="deleted = :isDeleted")
public class Company extends GenericEntity {

    @Column
    @Getter
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "company")
    private List<User> users = new ArrayList<>();

}
