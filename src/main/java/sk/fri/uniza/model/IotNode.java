package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Objects;

@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "IotNode_findById",
                query = "from IotNode where id = :id"),
        @org.hibernate.annotations.NamedQuery(name = "Node_All",
                query = "from IotNode"),
})

@Entity
public class IotNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY)//Swagger nebude zobrazovať atribút
    private Long id;

    @NotEmpty
    @ApiModelProperty(example = "Tomas") // Príklad pre swagger dok.
    private String Name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "household_id", nullable = true)
    private HouseHold houseHold;

    public IotNode(String pa_name){
        Name = pa_name;
    }
    public IotNode(){
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public HouseHold getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(HouseHold houseHold) {
        this.houseHold = houseHold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IotNode iotNode = (IotNode) o;
        return Objects.equals(id, iotNode.id) &&
                Objects.equals(Name, iotNode.Name) &&
                Objects.equals(houseHold, iotNode.houseHold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, houseHold);
    }
}
