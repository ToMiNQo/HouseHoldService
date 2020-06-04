package sk.fri.uniza.db;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.hibernate.AbstractDAO;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import sk.fri.uniza.model.IotNode;

import org.hibernate.HibernateException;

import java.util.List;

public class IotNodeDAO extends AbstractDAO<IotNodeDAO> {

   public IotNodeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

     public IotNode create(IotNode iotNode) {
        currentSession().save(iotNode);
        return iotNode;
    }

    public IotNode findById(Long id) {
        Query query = super.currentSession().getSession().createQuery("from IotNode where id = :pa_id ");
        query.setParameter("pa_id",id);
        List<?> list = query.list();
        return (IotNode) list.get(0);
        //return get(id);
    }

    public IotNode update(IotNode iotNode) {
       return  (IotNode) currentSession().merge(iotNode);

    }

    public List<IotNode> findByHouseHold(Long houseHoldId) {
       return list(namedQuery("IotNode_findByhouseHoldId")
                .setParameter("name", houseHoldId));

    }

    public List<IotNode> allIotNodes() {
      return list(namedQuery("Node_All"));

    }
}
