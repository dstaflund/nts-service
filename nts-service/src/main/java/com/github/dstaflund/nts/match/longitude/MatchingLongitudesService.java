package com.github.dstaflund.nts.match.longitude;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/matching/longitudes")
@Produces(MediaType.APPLICATION_JSON)
public class MatchingLongitudesService {

    @GET
    public List<Float> getMatchingLongitudes(@Valid @BeanParam MatchingLongitudesParams ctx){
        return MatchingLongitudesProvider.findMatchingLongitudes(ctx);
    }
}
