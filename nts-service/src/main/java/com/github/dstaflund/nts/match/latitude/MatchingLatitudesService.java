package com.github.dstaflund.nts.match.latitude;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/matching/latitudes")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingLatitudesService {

    @GET
    public List<Float> getMatchingLatitudes(@Valid @BeanParam MatchingLatitudesParams ctx){
        return MatchingLatitudesProvider.findMatchingLatitudes(ctx);
    }
}
