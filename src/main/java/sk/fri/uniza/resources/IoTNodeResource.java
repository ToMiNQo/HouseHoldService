package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jboss.logging.Param;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.IotNode;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@Path("/iotnode")
@Produces(MediaType.APPLICATION_JSON)

public class IoTNodeResource {

    private IotNodeDAO iotNodeDAO;

    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    @POST
    @UnitOfWork //Otvorí novú hibernate session
    @Path("/add")
    @ApiOperation(value = "Pridá nový typ Node")
    public IotNode createIotNode(@QueryParam("name") String name) {
        IotNode iotNode = new IotNode(name);
        return iotNodeDAO.create(iotNode);
    }

    @POST
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Pridá nový typ Node")
    public IotNode createIotNode(@Valid IotNode iotNode) {
        return iotNodeDAO.create(iotNode);
    }

    @PUT
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Upraví existujúci Node")
    public IotNode updateIotNode(@Valid IotNode iotNode){ return iotNodeDAO.update(iotNode);}

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @Path("{id}")
    @ApiOperation(value = "Zobrazí typ Node")
    public IotNode findIotNode(@PathParam("id") Long id) { return iotNodeDAO.findById(id);}

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zobrazí všetky typy Node")
    public List<IotNode> allIotNodes() { return iotNodeDAO.allIotNodes();
    }

}
